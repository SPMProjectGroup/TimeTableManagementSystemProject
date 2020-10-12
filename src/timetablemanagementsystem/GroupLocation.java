/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class GroupLocation {
    
    private int groupLocationId;
    private int groupId;
    private String buildingName;
    
    public GroupLocation(int GroupLocationId, int GroupId, String BuildingName){
        
        this.groupLocationId = GroupLocationId;
        this.groupId = GroupId;
        this.buildingName = BuildingName;    
    }

    
    public int getGroupLocationId() {
        return groupLocationId;
    }

    
    public int getGroupId() {
        return groupId;
    }

   
    public String getBuildingName() {
        return buildingName;
    }
  
}
