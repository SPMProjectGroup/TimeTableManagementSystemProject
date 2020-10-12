
package timetablemanagementsystem;


public class Lecturer {
    
    private int lecID;
    private String lecName;
    private String lecTitle;
    private String faculty;
    private String dept;
    private String center;
    private String building;
    private String category;
    private int level;
    private String rank;
    
    public Lecturer(int lecID,String lecName,String lecTitle,String faculty,String dept,String center,String building,String category,int level,String rank ){
        this.lecID = lecID;
        this.lecName = lecName;
        this.lecTitle = lecTitle;
        this.faculty = faculty;
        this.dept = dept;
        this.center = center;
        this.building = building;
        this.category = category;
        this.level = level;
        this.rank = rank;     
    }
    
    public Lecturer(String lecName){
        this.lecName = lecName;
    }
  
    public int getLecID() {
        return lecID;
    }

    public void setLecID(int lecID) {
        this.lecID = lecID;
    }

    public String getLecName() {
        return lecName;
    }
    
    public void setLecName(String lecName) {
        this.lecName = lecName;
    }
    
    public String getLecTitle() {
        return lecTitle;
    }

    public void setLecTitle(String lecTitle) {
        this.lecTitle = lecTitle;
    }
 
    public String getFaculty() {
        return faculty;
    }
 
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDept() {
        return dept;
    }
 
    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCenter() {
        return center;
    }

     public void setCenter(String center) {
        this.center = center;
    }

    public String getBuilding() {
        return building;
    }
 
    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
 
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

 
  
}
