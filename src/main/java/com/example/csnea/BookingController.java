package com.example.csnea;
//imports:

import java.sql.Date;
import java.sql.Time;

public class BookingController {
    private String Username;
    private String booking_type;
    private Date booking_date;
    private Time booking_time;

    public BookingController(String Username, String booking_type, Date booking_date, Time booking_time) {
        this.Username = Username;
        this.booking_type = booking_type;
        this.booking_date = booking_date;
        this.booking_time = booking_time;
    }

    //getter and setter methods for the values above so that they can be added to the observable list

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {Username = username;}

    public String getBooking_type() {return booking_type;}

    public void setBooking_type(String booking_type) {this.booking_type = booking_type;}

    public Date getBooking_date() {return booking_date;}

    public void setBooking_date(Date booking_date) {this.booking_date = booking_date;}

    public Time getBooking_time() {return booking_time;}

    public void setBooking_time(Time booking_time) {this.booking_time = booking_time;}
}


