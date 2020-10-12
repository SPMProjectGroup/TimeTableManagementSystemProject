/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

public class SessionModel {
    
    private int sessionId;
    private String tag;
    private String studentGroup;
    private String subject;
    private int noOfStudents;
    private int sessionDuration;
    
    public SessionModel(int SessionId, String Tag, String StudentGroup, String Subject, int NoOfStudents, int SessionDuration){
      
        this.sessionId = SessionId;
        this.tag = Tag;
        this.studentGroup = StudentGroup;
        this.subject = Subject;
        this.noOfStudents = NoOfStudents;
        this.sessionDuration = SessionDuration;    
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
     * @return the studentGroup
     */
    public String getStudentGroup() {
        return studentGroup;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return the noOfStudents
     */
    public int getNoOfStudents() {
        return noOfStudents;
    }

    /**
     * @return the sessionDuration
     */
    public int getSessionDuration() {
        return sessionDuration;
    }
    
    
    
}
