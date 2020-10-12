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
public class SubjectLocation {
    
    private int subjectLocationId;
    private int subjectId;
    private int buildingId;
    private String roomType;
    private String subjectTag;
    
    
    public SubjectLocation(int SubjectLocationId, int SubjectID, int BuildingId, String RoomType, String SubjectTag){
        
        this.subjectLocationId =SubjectLocationId;
        this.subjectId = SubjectID;
        this.buildingId = BuildingId;
        this.roomType = RoomType;
        this.subjectTag = SubjectTag;
    }

     
    public int getSubjectLocationId() {
        return subjectLocationId;
    }
    
    public int getSubjectId() {
        return subjectId;
    }

   
    public int getBuildingId() {
        return buildingId;
    }
    
    public String getRoomType() {
        return roomType;
    }

    
    public String getSubjectTag() {
        return subjectTag;
    }      
}
