/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class ConSessionModel {
    
    private int conSessionId;
    private String tag;
    private String studentGroups;
    private String subject;
    private int noOfStudents;
    private int sessionDuration;
    private int session1Id;
    private int session2Id;
    
    
    public ConSessionModel(int ConSessionId, String Tag, String StudentGroups, String Subject,  int NoOfStudents, int SessionDuration, int Session1Id, int Session2Id){
      
        this.conSessionId = ConSessionId;
        this.tag = Tag;
        this.studentGroups = StudentGroups;
        this.subject = Subject;
        this.noOfStudents = NoOfStudents;
        this.sessionDuration = SessionDuration;
        this.session1Id = Session1Id;
        this.session2Id = Session2Id;
    }

    
    public int getConSessionId() {
        return conSessionId;
    }

    
    public String getTag() {
        return tag;
    }

    
    public String getStudentGroups() {
        return studentGroups;
    }

    
    public String getSubject() {
        return subject;
    }

    
    public int getNoOfStudents() {
        return noOfStudents;
    }

    
    public int getSessionDuration() {
        return sessionDuration;
    }

    
    public int getSession1Id() {
        return session1Id;
    }

    
    public int getSession2Id() {
        return session2Id;
    }
    
}
