/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

/**
 *
 * @author DELL
 */
public class NATimes {
    
    private int NAId;
    private String NAtype;
    private String NAFor;
    private String NADay;
    private String NAFrom;
    private String NATo;

    public NATimes(int NAId,String NAtype, String NAFor, String NADay, String NAFrom, String NATo) {
        this.NAId = NAId;
        this.NAtype = NAtype;
        this.NAFor = NAFor;
        this.NADay = NADay;
        this.NAFrom = NAFrom;
        this.NATo = NATo;
    }
    
    
    public String getNAtype() {
        return NAtype;
    }

    public void setNAtype(String NAtype) {
        this.NAtype = NAtype;
    }
    
    public int getNAId() {
        return NAId;
    }

    public void setNAId(int NAId) {
        this.NAId = NAId;
    }

    public String getNAFor() {
        return NAFor;
    }

    public void setNAFor(String NAFor) {
        this.NAFor = NAFor;
    }

    public String getNADay() {
        return NADay;
    }

    public void setNADay(String NADay) {
        this.NADay = NADay;
    }

    public String getNAFrom() {
        return NAFrom;
    }

    public void setNAFrom(String NAFrom) {
        this.NAFrom = NAFrom;
    }

    public String getNATo() {
        return NATo;
    }

    public void setNATo(String NATo) {
        this.NATo = NATo;
    }
    
}
