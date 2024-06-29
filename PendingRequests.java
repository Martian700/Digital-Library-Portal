import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front=new Node<>();
    private Node<RequestData> back=new Node<>();

    public PendingRequests(){
        front.next=back;
        back.previous=front;
    }

    public boolean insert(Node<RequestData> insnode) {
        Node<RequestData> temp = insnode;
        length++;
        if(length==1){
            front.next=temp;
            temp.previous=front;
            temp.next=back;
            back.previous=temp;
            return true;
        }
        back.previous.next=temp;
        temp.previous=back.previous;
        temp.next=back;
        back.previous=temp;
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
        Node<RequestData> temp = front.next;
        while(delnode.data!=temp.next.data){
            temp=temp.next;
        }
        temp.next.next.previous=temp;
        temp.next=temp.next.next;
        return true;
    }

    public Node<RequestData> find(int ISBN) {
        /*
         * Your code here.
         */
        Node<RequestData> nrd = front.next;
        while(nrd.data.ISBN!=ISBN){
            nrd=nrd.next;
            if(nrd==back){
                break;
            }
        }
        if(nrd.data==null){
            Node<RequestData> res = new Node<>();
            res.data=new RequestData();
            res.data.ISBN=-1;
            res.data.UserID=-1;
            return res;
        }else{
            return nrd;
        }
    }

    public String toString(){
        Node<RequestData> temp = front.next;
        String s = "Length: " + length + "\n";
        while(temp!=back){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
