/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class SessionLocation {
    
    private int sessionlocationId;
    private int sessionId;
    private String tag;
    private int buildingId;
    
    public SessionLocation(int SessionlocationId, int SessionId, String Tag, int buildingId){
       
        this.sessionlocationId = SessionlocationId;
        this.sessionId = SessionId;
        this.tag = Tag;
        this.buildingId = buildingId;
    }

    /**
     * @return the sessionlocationId
     */
    public int getSessionlocationId() {
        return sessionlocationId;
    }

    /**
     * @return the sessionId
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return the buildingId
     */
    public int getBuildingId() {
        return buildingId;
    }
 
    
}
