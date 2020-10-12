/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class Subject_1 {
    
    private int subjectId;
    private String subjectName;
    private String subjectCode;
    private String offeredYear;
    private String offeredSem;
    private int noOfLecHours;
    private int noOfTuteHours;
    private int noOfLabHours;
    private int noOfEvaluationHours;
    
    
    public Subject_1(int SubjectID, String SubjectName, String SubjectCode, String OfferedYear, String OfferedSem, int NoOfLecHours, int NoOfTuteHours, int NoOfLabHours, int NoOfEvaluationHours){
        
        this.subjectId = SubjectID;
        this.subjectName = SubjectName;
        this.subjectCode = SubjectCode;
        this.offeredYear = OfferedYear;
        this.offeredSem = OfferedSem;
        this.noOfLecHours = NoOfLecHours;
        this.noOfTuteHours = NoOfTuteHours;
        this.noOfLabHours = NoOfLabHours;
        this.noOfEvaluationHours = NoOfEvaluationHours;
    }

    /**
     * @return the subjectId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @return the subjectCode
     */
    public String getSubjectCode() {
        return subjectCode;
    }

    /**
     * @return the offeredYear
     */
    public String getOfferedYear() {
        return offeredYear;
    }

    /**
     * @return the offeredSem
     */
    public String getOfferedSem() {
        return offeredSem;
    }

    /**
     * @return the noOfLecHours
     */
    public int getNoOfLecHours() {
        return noOfLecHours;
    }

    /**
     * @return the noOfTuteHours
     */
    public int getNoOfTuteHours() {
        return noOfTuteHours;
    }

    /**
     * @return the noOfLabHours
     */
    public int getNoOfLabHours() {
        return noOfLabHours;
    }

    /**
     * @return the noOfEvaluationHours
     */
    public int getNoOfEvaluationHours() {
        return noOfEvaluationHours;
    }
    
    
    
}











