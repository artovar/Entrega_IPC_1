/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author aaren
 */
public class Booking {
    
    private LocalDateTime bookingDate;
    private LocalDate madeForDay;
    private LocalTime fromTime;
    private Boolean paid;
    private Court court;
    private Member mermber;
    
    public Booking(LocalDateTime b,LocalDate m,LocalTime f,Boolean p,Court c, Member mem){
        
        bookingDate = b;
        madeForDay = m;
        fromTime = f;
        paid = p;
        court = c;
        mermber =mem;
    }
    
    
    public void setBookingDate(LocalDateTime b) {bookingDate = b;}
    public LocalDateTime getBookingDate() {return bookingDate;}
    
    public void setMadeForDay(LocalDate m) {madeForDay=m;}
    public LocalDate getMadeForDay() {return madeForDay;}
    
    public void setFromTime(LocalTime f) {fromTime = f;}
    public LocalTime getFromTime() {return fromTime;}
    
    public void setPaid(Boolean p) {paid = p;}
    public Boolean getPaid() {return paid;}
    
    public void setCourt(Court c) {court= c;}
    public Court getCourt() {return court;}
    
    public void setMember(Member m) {mermber=m;}
    public Member getMember() {return mermber;}
    
    
    
    
    
    
}
