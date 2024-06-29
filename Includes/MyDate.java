package Includes;

import java.time.LocalDate;

public class MyDate {
    public int month;
    public int year;

    public void setDate(){
        LocalDate today = LocalDate.now();
        this.month=today.getMonthValue();
        this.year=today.getYear();
    }
}
