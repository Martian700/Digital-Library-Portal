package Includes;

public class RequestData {
	public int ISBN;
	public int UserID;
	public MyDate RequestDate = new MyDate();

	public String toString() {
        return "ISBN: " + ISBN + "\nUserID: " + UserID + "\n";
    }
}
