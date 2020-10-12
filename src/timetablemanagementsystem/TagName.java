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
public class TagName {

    private int id;
    private String Tagname;
    
    public TagName(int id, String Tagname) {
        this.id = id;
        this.Tagname = Tagname;
    }   
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagname() {
        return Tagname;
    }

    public void setTagname(String Tagname) {
        this.Tagname = Tagname;
    }
}
