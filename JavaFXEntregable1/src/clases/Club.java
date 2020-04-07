/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author aaren
 */
public class Club {
    
    
    private String name;
    private final int bookingDuration=90;
    private final int bookingSlots = 9;
    
    ArrayList<Court> courts;
    ArrayList<Member> members;
    ArrayList<Booking> bookings;
    
    public Club(String n, ArrayList<Court> c, ArrayList<Member> m, ArrayList<Booking> b) {
        
        name = n;
        courts = c;
        members = m;
        bookings = b;
    
    }
    
    
    public void setName(String n) {name =n;}
    public String getName() {return name;}

    public void setCourts(ArrayList<Court> c) { courts = c;}
    public ArrayList<Court> getCourts() {return courts;}

    public void setMembers(ArrayList<Member> m) {members = m;}
    public ArrayList<Member> getMembers() {return members;} 
    
    public void setBooking(ArrayList<Booking> b) {bookings = b;}
    public ArrayList<Booking> getBookings() {return bookings;}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
