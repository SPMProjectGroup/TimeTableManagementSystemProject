/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

/**
 *
 * @author Madhuransi
 */
public class TimeSlot {
    private String date;
    private boolean oneHour;
    private boolean halfHour;

    public TimeSlot(String date, boolean oneHour, boolean halfHour) {
        this.date = date;
        this.oneHour = oneHour;
        this.halfHour = halfHour;
    }

    public TimeSlot() {
    }

    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isOneHour() {
        return oneHour;
    }

    public void setOneHour(boolean oneHour) {
        this.oneHour = oneHour;
    }

    public boolean isHalfHour() {
        return halfHour;
    }

    public void setHalfHour(boolean halfHour) {
        this.halfHour = halfHour;
    }
    
    
    
}
