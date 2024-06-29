import java.time.LocalDate;

import Includes.*;

public class RequestQueue {
    private Node<RequestData> front = new Node<RequestData>();
    private Node<RequestData> back = new Node<RequestData>();
    private int length = 0;

    public RequestQueue(){
        front.next=back;
        back.previous=front;
    }

    public RequestData getFront() {
        return this.front.next.data;//first node is front.next
    }

    public int getLength() {
        return this.length;
    }

    public void push(int ISBN, int UserID) {
        Node<RequestData> temp = new Node<RequestData>();
        length++;
        LocalDate today = LocalDate.now();
        temp.data = new RequestData();
        temp.data.ISBN=ISBN;
        temp.data.UserID=UserID;
        temp.data.RequestDate.month=today.getMonthValue();
        temp.data.RequestDate.year=today.getYear();
        if(length==1){
            front.next=temp;
            temp.previous=front;
            temp.next=back;
            back.previous=temp;
            return;
        }
        back.previous.next=temp;
        temp.previous=back.previous;
        temp.next=back;
        back.previous=temp;
        return;
    }

    public void pop() {      // processing needs to be done before popping, 
        if(length==0){
            System.out.println("No requests in queue");
        }
        length--;
        front.next=front.next.next;
        front.next.previous=front;
    }

    public String toString(){
        Node<RequestData> temp = front.next;
        String s = "Length: " + length + "\n";
        while(temp != back){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
