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
public class YearAndSemester {
    
    private int id;
    private String YearAndSem;
    
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearAndSem() {
        return YearAndSem;
    }

    public void setYearAndSem(String YearAndSem) {
        this.YearAndSem = YearAndSem;
    }

    public YearAndSemester(int id, String YearAndSem) {
        this.id = id;
        this.YearAndSem = YearAndSem;
    }
    
}
