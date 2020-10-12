
package FireAlarmModels;

import java.io.Serializable;

/**
 *
 * @author DELL
 */

/*** Model class for all sensor details ***/
public class AllSensorDetails implements Serializable{
    
    private int Id;
    private String floorNo;
    private String roomNo;
    private int co2Level;
    private int smokeLevel;
    private boolean sensorStatus;


    /*** default constructor ***/
    public AllSensorDetails() {
    }
    
    /*** override constructor with parameters ***/
    public AllSensorDetails(int Id, String floorNo, String roomNo, int co2Level, int smokeLevels) {
        this.Id = Id;
        this.floorNo = floorNo;
        this.roomNo = roomNo;
        this.co2Level = co2Level;
        this.smokeLevel = smokeLevel;
        //this.sensorStatus = sensorStatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getCo2Level() {
        return co2Level;
    }

    public void setCo2Level(int co2Level) {
        this.co2Level = co2Level;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    public boolean isSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(boolean sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    @Override
    public String toString() {
        return "AllSensorDetails{" + "Id=" + Id + ", floorNo=" + floorNo + ", roomNo=" + roomNo + ", co2Level=" + co2Level + ", smokeLevel=" + smokeLevel + ", sensorStatus=" + sensorStatus + '}';
    }

    /************************* getters and setters *****************************/
    

//    public static boolean isSensorStatus() {
//        return sensorStatus;
//    }
//
//    public void setSensorStatus(boolean sensorStatus) {
//        this.sensorStatus = sensorStatus;
//    }  

    
    
}
