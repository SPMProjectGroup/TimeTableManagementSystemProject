/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class ConSessionLocation {
    
    private int conSessionLocId;
    private int conSessionId;
    private String conTag;
    private int buildingId;
    
    public ConSessionLocation(int ConSessionLocId, int ConSessionId, String ConTag, int BuildingId){
        
        this.conSessionLocId = ConSessionLocId;
        this.conSessionId = ConSessionId;
        this.conTag = ConTag;
        this.buildingId = BuildingId;
    }

    
    public int getConSessionLocId() {
        return conSessionLocId;
    }

   
    public int getConSessionId() {
        return conSessionId;
    }

    
    public String getConTag() {
        return conTag;
    }

  
    public int getBuildingId() {
        return buildingId;
    }
    
}
