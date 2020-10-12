/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class LecturerLocation {
  
    private int lecLocationId;
    private int lecID;
    private String buildingName;
    
    public LecturerLocation(int LecLocationId, int lecturerID, String BuildingName){
        
        this.lecLocationId = LecLocationId;
        this.lecID = lecturerID;
        this.buildingName = BuildingName;
    }

   
    public int getLecLocationId() {
        return lecLocationId;
    }

    
    public int getLecID() {
        return lecID;
    }

    
    public String getBuildingName() {
        return buildingName;
    }
    
    
}
