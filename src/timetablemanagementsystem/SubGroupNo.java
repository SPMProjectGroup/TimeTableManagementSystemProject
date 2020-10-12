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
public class SubGroupNo {

 private int Id;
 private int SubGroupNo;
 
 
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSubGroupNo() {
        return SubGroupNo;
    }

    public void setSubGroupNo(int SubGroupNo) {
        this.SubGroupNo = SubGroupNo;
    }

    public SubGroupNo(int Id, int SubGroupNo) {
        this.Id = Id;
        this.SubGroupNo = SubGroupNo;
    }
}
