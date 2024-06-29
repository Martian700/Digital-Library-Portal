import java.util.*;

import Includes.*;

public class LibraryStorage {
    // HashMap
    // process return
    private HashMap<Integer, BookData> storage;
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i=100001; i<100011; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 0;
            dateor.year = 0;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = -1;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }

    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
        return;
    }

    public boolean processQueue() {
        Node<RequestData> temp = new Node<>();
        temp.data=rqQueue.getFront();
        rqQueue.pop();
        if(temp.data==null){
            return false;
        }
        BookData book = storage.get(temp.data.ISBN);
        if(book==null){
            System.out.println("Book does not exist");
            return false;
        }
        if(book.BorrowedStatus){
            prLinkedList.insert(temp);
            return false;
        }else{
            book.BorrowedStatus=true;
            book.BorrowedByUserID=temp.data.UserID;
            book.DateOfReturn.setDate();
            if(book.DateOfReturn.month==12){
                book.DateOfReturn.year++;
                book.DateOfReturn.month=1;
            }else{
                book.DateOfReturn.month++;
            }
            return true;
        }
    }

    public boolean processReturn(BookData book) {          // I have assumed this takes BookData object as argument, can also work with ISBN perhaps
        book.BorrowedStatus=false;
        book.BorrowedByUserID=-1;
        book.DateOfReturn.setDate();
        Node<RequestData> temp = prLinkedList.find(book.ISBN);
        if(temp.data.UserID==-1&&temp.data.ISBN==-1){
            storage.put(book.ISBN, book);
            return false;
        }else{
            prLinkedList.delete(temp);
            book.BorrowedStatus=true;
            book.BorrowedByUserID=temp.data.UserID;
            if(book.DateOfReturn.month==12){
                book.DateOfReturn.year++;
                book.DateOfReturn.month=1;
            }else{
                book.DateOfReturn.month++;
            }
            return true;
        }
    }

    public String rqQueueToString(){
        return rqQueue.toString();
    }

    public String prLinkedListToString(){
        return prLinkedList.toString();
    }
    
}
