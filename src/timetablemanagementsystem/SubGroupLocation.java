/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;


public class SubGroupLocation {
    
    private int subGroupLocationId;
    private int subGroupId; 
    private String buildingName;
    
    public SubGroupLocation(int SubGroupLocationId, int SubGroupId, String BuildingName){
        this.subGroupLocationId = SubGroupLocationId;
        this.subGroupId = SubGroupId;
        this.buildingName = BuildingName;
    }

    /**
     * @return the subGroupLocationId
     */
    public int getSubGroupLocationId() {
        return subGroupLocationId;
    }

    /**
     * @return the subGroupId
     */
    public int getSubGroupId() {
        return subGroupId;
    }

    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }
            
            			
    
}
