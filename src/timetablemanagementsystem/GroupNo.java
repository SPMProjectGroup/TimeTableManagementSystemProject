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
public class GroupNo {
    
    private int Id;
    private int GroupNo;
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getGroupNo() {
        return GroupNo;
    }

    public void setGroupNo(int GroupNo) {
        this.GroupNo = GroupNo;
    }

    public GroupNo(int Id, int GroupNo) {
        this.Id = Id;
        this.GroupNo = GroupNo;
    }
}
