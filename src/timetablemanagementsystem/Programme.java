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
public class Programme {
    
  private int Id;
  private String Programme;

  public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getProgramme() {
        return Programme;
    }

    public void setProgramme(String Programme) {
        this.Programme = Programme;
    }

    public Programme(int Id, String Programme) {
        this.Id = Id;
        this.Programme = Programme;
    }  
}
