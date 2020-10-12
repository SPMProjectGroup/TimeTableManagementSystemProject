/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class NotAvailableRoom {
    
    private int naRoomId;
    private String naRoomName;
    private String naRoomType;
    private String naDay;
    private String naTimeFrom;
    private String naTimeTo;
    
    public NotAvailableRoom(int NARoomId, String NARoomName, String NARoomType, String NADay, String NATimeFrom, String NATimeTo){
        
        this.naRoomId = NARoomId;
        this.naRoomName = NARoomName;
        this.naRoomType = NARoomType;
        this.naDay = NADay;
        this.naTimeFrom = NATimeFrom;
        this.naTimeTo = NATimeTo;
    }

    /**
     * @return the naRoomId
     */
    public int getNaRoomId() {
        return naRoomId;
    }

    /**
     * @return the naRoomName
     */
    public String getNaRoomName() {
        return naRoomName;
    }

    /**
     * @return the naRoomType
     */
    public String getNaRoomType() {
        return naRoomType;
    }

    /**
     * @return the naDay
     */
    public String getNaDay() {
        return naDay;
    }

    /**
     * @return the naTimeFrom
     */
    public String getNaTimeFrom() {
        return naTimeFrom;
    }

    /**
     * @return the naTimeTo
     */
    public String getNaTimeTo() {
        return naTimeTo;
    }
    
    					
    
}
