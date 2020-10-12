/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

/**
 *
 * @author acer
 */
public class Session {
    
    private int sessionID;
    private String tag;
    private String stGrp;
    private String subject;
    private int noOfStudents;
    private int sessionDuration;
    private Lecturer lecNames;
    private String lecName;

    public Session(int SessionID, String tag, String stGrp, String subject, int noOfStudents, int sessionDuration){
        
        this.sessionID = SessionID;
        this.tag = tag;
        this.stGrp = stGrp;
        this.subject = subject;
        this.noOfStudents = noOfStudents;
        this.sessionDuration = sessionDuration;
        this.lecNames = lecNames;
    }
    
     public Session(int SessionID, String tag, String stGrp, String subject, int noOfStudents, int sessionDuration, String lecName){
        
        this.sessionID = SessionID;
        this.tag = tag;
        this.stGrp = stGrp;
        this.subject = subject;
        this.noOfStudents = noOfStudents;
        this.sessionDuration = sessionDuration;
        this.lecName = lecName;
    }
    
    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
    
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStGrp() {
        return stGrp;
    }

    public void setStGrp(String stGrp) {
        this.stGrp = stGrp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public int getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(int sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public Lecturer getLecNames() {
        return lecNames;
    }

    public void setLecNames(Lecturer lecNames) {
        this.lecNames = lecNames;
    }

    public String getLecName() {
        return lecName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

  
}
