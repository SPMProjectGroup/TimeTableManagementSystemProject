package FireAlarmModels;

import java.io.Serializable;

/**
 *
 * @author DELL
 */

/**************Model class for sensor details without room number and floor number*************************/
public class Sensors implements Serializable{

    private int sensorId;
    private int smokeValue;
    private int co2Value;
    private boolean status;

    /*** default constructor ***/
    public Sensors() {
    }

    /*** override constructor with parameters ***/
    public Sensors(int sensorId, int smokeValue, int co2Value,boolean status) {
        this.sensorId = sensorId;
        this.smokeValue = smokeValue;
        this.co2Value = co2Value;
        this.status = status;
    }

    /************************* getters and setters *****************************/
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getSmokeValue() {
        return smokeValue;
    }

    public void setSmokeValue(int smokeValue) {
        this.smokeValue = smokeValue;
    }

    public int getCo2Value() {
        return co2Value;
    }

    public void setCo2Value(int co2Value) {
        this.co2Value = co2Value;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
