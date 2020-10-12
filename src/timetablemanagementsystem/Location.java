/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class Location {
    
    private int id;
    private String buildingName;
    private String roomName;
    private String roomType;
    
    public Location(int ID, String BuildingName, String RoomName, String RoomType){
        
        this.id= ID;
        this.buildingName = BuildingName;
        this.roomName = RoomName;
        this.roomType = RoomType;
        
    }
    
    public int getId() {
        return id;
    }

    
    public String getBuildingName() {
        return buildingName;
    }

    
    public String getRoomName() {
        return roomName;
    }

   
    public String getRoomType() {
        return roomType;
    }
}

