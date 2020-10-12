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
public class TagLocation {
    
    private int tagLocationId;
    private String tagName;
    private int buildingId;
    private String roomType;
    
    public TagLocation(int TagLocationId, String TagName, int BuildingId,  String RoomType ){
        
        this.tagLocationId =TagLocationId;
        this.tagName=TagName;
        this.buildingId=BuildingId;
        this.roomType=RoomType;
        
    }

    
    public int getTagLocationId() {
        return tagLocationId;
    }

    
    public String getTagName() {
        return tagName;
    }

  
    public String getRoomType() {
        return roomType;
    }

    /**
     * @return the buildingId
     */
    public int getBuildingId() {
        return buildingId;
    }
    
    
    
    
}
