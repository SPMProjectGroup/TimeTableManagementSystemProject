
package timetablemanagementsystem;

public class StudentBatch {

    private int id;
    private String yearAndSem;
    private String programme;
    private int groupNo;
    private int subGroupNo;
    private String groupId;
    private String SubGroupId;
    
     public StudentBatch(int id, String yearAndSem, String programme, int groupNo, int subGroupNo, String groupId, String SubGroupId) {
        this.id = id;
        this.yearAndSem = yearAndSem;
        this.programme = programme;
        this.groupNo = groupNo;
        this.subGroupNo = subGroupNo;
        this.groupId = groupId;
        this.SubGroupId = SubGroupId;
    }

    StudentBatch(String string, String string0, int aInt, int aInt0, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearAndSem() {
        return yearAndSem;
    }

    public void setYearAndSem(String yearAndSem) {
        this.yearAndSem = yearAndSem;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getSubGroupNo() {
        return subGroupNo;
    }

    public void setSubGroupNo(int subGroupNo) {
        this.subGroupNo = subGroupNo;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSubGroupId() {
        return SubGroupId;
    }

    public void setSubGroupId(String SubGroupId) {
        this.SubGroupId = SubGroupId;
    }

}
