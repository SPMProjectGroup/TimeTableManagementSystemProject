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
public class parallel_Sessions {
    
    private int pSessionId;

    public parallel_Sessions(int pSessionId) {
        this.pSessionId = pSessionId;
    }
    private String startTime;
    private String Day;
    private int duration;
//    private String endTime;

    public parallel_Sessions(int pSessionId, String startTime, String Day, int duration) {
        this.pSessionId = pSessionId;
        this.startTime = startTime;
        this.Day = Day;
        this.duration = duration;
//        this.endTime = endTime;
    }

    public int getpSessionId() {
        return pSessionId;
    }

    public void setpSessionId(int pSessionId) {
        this.pSessionId = pSessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
