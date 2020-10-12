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
public class Room {
    
    private int id;
    private String buildingName;
    private String roomName;
    private String roomType;
    private int floorNo;
    private int roomNo;
    
    public Room(int ID, String BuildingName, String RoomName, String RoomType, int FloorNo, int RoomNo){
        
        this.id= ID;
        this.buildingName = BuildingName;
        this.roomName = RoomName;
        this.roomType = RoomType;
        this.floorNo = FloorNo;
        this.roomNo = RoomNo;
        
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

    
    public int getFloorNo() {
        return floorNo;
    }

   
    public int getRoomNo() {
        return roomNo;
    }
    
    
}
