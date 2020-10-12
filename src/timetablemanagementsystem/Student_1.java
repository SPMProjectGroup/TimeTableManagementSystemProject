/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;


public class Student_1 {
    
    private int studentId;
    private String userType;
    private int noOfWorkingDays;
    private int wdMonday;
    private int wdTuesday;
    private int wdWednesday;
    private int wdThursday;
    private int wdFriday;
    private int wdSaturday;
    private int wdSunday;
    private int workingHours;
       
     
    public Student_1(int TimetableID, String UserType, int NoOfWorkingDays, int WDMonday, int WDTuesday, int WDWednesday, int WDThursday, int WDFriday, int WDSaturday, int WDSunday, int WorkingHours ){
        
        this.studentId = TimetableID;
        this.userType = UserType;
        this.noOfWorkingDays = NoOfWorkingDays;
        this.wdMonday = WDMonday;
        this.wdTuesday = WDTuesday;
        this.wdWednesday = WDWednesday;
        this.wdThursday = WDThursday;
        this.wdFriday = WDFriday;
        this.wdSaturday = WDSaturday;
        this.wdSunday = WDSunday;
        this.workingHours = WorkingHours;
        
    }

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @return the noOfWorkingDays
     */
    public int getNoOfWorkingDays() {
        return noOfWorkingDays;
    }

    /**
     * @return the wdMonday
     */
    public int getWdMonday() {
        return wdMonday;
    }

    /**
     * @return the wdTuesday
     */
    public int getWdTuesday() {
        return wdTuesday;
    }

    /**
     * @return the wdWednesday
     */
    public int getWdWednesday() {
        return wdWednesday;
    }

    /**
     * @return the wdThursday
     */
    public int getWdThursday() {
        return wdThursday;
    }

    /**
     * @return the wdFriday
     */
    public int getWdFriday() {
        return wdFriday;
    }

    /**
     * @return the wdSaturday
     */
    public int getWdSaturday() {
        return wdSaturday;
    }

    /**
     * @return the wdSunday
     */
    public int getWdSunday() {
        return wdSunday;
    }

    /**
     * @return the workingHours
     */
    public int getWorkingHours() {
        return workingHours;
    }
    
            
            

    
}
