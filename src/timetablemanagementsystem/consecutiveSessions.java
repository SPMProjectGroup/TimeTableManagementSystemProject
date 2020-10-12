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
public class consecutiveSessions {
    
    private int consecutiveSessionsId;
    private String cTag;
    private String cStudentGroup;
    private String cSubject;
    private int cNoOfStudents;
    private int cSessionDuration;
    private int session1;
    private int session2;

    public int getSession1() {
        return session1;
    }

    public void setSession1(int session1) {
        this.session1 = session1;
    }

    public int getSession2() {
        return session2;
    }

    public void setSession2(int session2) {
        this.session2 = session2;
    }

    public consecutiveSessions(int consecutiveSessionsId, String cTag, String cStudentGroup, String cSubject, int cNoOfStudents, int cSessionDuration, int session1, int session2) {
        this.consecutiveSessionsId = consecutiveSessionsId;
        this.cTag = cTag;
        this.cStudentGroup = cStudentGroup;
        this.cSubject = cSubject;
        this.cNoOfStudents = cNoOfStudents;
        this.cSessionDuration = cSessionDuration;
        this.session1 = session1;
        this.session2 = session2;
    }

    public int getConsecutiveSessionsId() {
        return consecutiveSessionsId;
    }

    public void setConsecutiveSessionsId(int consecutiveSessionsId) {
        this.consecutiveSessionsId = consecutiveSessionsId;
    }

    public String getcTag() {
        return cTag;
    }

    public void setcTag(String cTag) {
        this.cTag = cTag;
    }

    public String getcStudentGroup() {
        return cStudentGroup;
    }

    public void setcStudentGroup(String cStudentGroup) {
        this.cStudentGroup = cStudentGroup;
    }

    public String getcSubject() {
        return cSubject;
    }

    public void setcSubject(String cSubject) {
        this.cSubject = cSubject;
    }

    public int getcNoOfStudents() {
        return cNoOfStudents;
    }

    public void setcNoOfStudents(int cNoOfStudents) {
        this.cNoOfStudents = cNoOfStudents;
    }

    public int getcSessionDuration() {
        return cSessionDuration;
    }

    public void setcSessionDuration(int cSessionDuration) {
        this.cSessionDuration = cSessionDuration;
    }

}
