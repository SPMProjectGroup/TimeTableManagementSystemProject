
package timetablemanagementsystem;


public class Subject {

    

    private int subID;
    private String subCode;
    private String subName;
    private String offeredYear;
    private String offeredSem;
    private int noOfLecH;
    private int noOfTuteH;
    private int noOfLabH;
    private  int noOfEvaluationH;
    
    public Subject(int subID,String subCode, String subName, String offeredYear, String offeredSem,  int noOfLecH, int noOfTuteH, int noOfLabH, int noOfEvaluationH ){
        
        this.subID = subID;
        this.subCode = subCode;
        this.subName = subName;
        this.offeredYear = offeredYear;
        this.offeredSem = offeredSem;
        this.noOfLecH = noOfLecH;
        this.noOfTuteH = noOfTuteH;
        this.noOfLabH = noOfLabH;
        this.noOfEvaluationH = noOfEvaluationH;
    }
 
    public int getSubID() {
        return subID;
    }

    public void setSubID(int subID) {
        this.subID = subID;
    }
    
    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getOfferedYear() {
        return offeredYear;
    }

    public void setOfferedYear(String offeredYear) {
        this.offeredYear = offeredYear;
    }

    public String getOfferedSem() {
        return offeredSem;
    }

    public void setOfferedSem(String offeredSem) {
        this.offeredSem = offeredSem;
    }

    public int getNoOfLecH() {
        return noOfLecH;
    }

    public void setNoOfLecH(int noOfLecH) {
        this.noOfLecH = noOfLecH;
    }

    public int getNoOfTuteH() {
        return noOfTuteH;
    }
    
    public void setNoOfTuteH(int noOfTuteH) {
        this.noOfTuteH = noOfTuteH;
    }

    public int getNoOfLabH() {
        return noOfLabH;
    }

    public void setNoOfLabH(int noOfLabH) {
        this.noOfLabH = noOfLabH;
    }

    public int getNoOfEvaluationH() {
        return noOfEvaluationH;
    }

    public void setNoOfEvaluationH(int noOfEvaluationH) {
        this.noOfEvaluationH = noOfEvaluationH;
    }
    
    
}
