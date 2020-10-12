/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

 
public class TableModel extends AbstractTableModel
{
    private final List<TimeSlot> timeslots;
    
    double start_time = 8.3;
    public static String timeslotpattern = "8.30";
    
    private final String[] columnNames = new String[] {
            "Time", "One Hour", "Half Hour"
    };
    private final Class[] columnClass = new Class[] {
         String.class, Boolean.class, Boolean.class
    };
 
    public TableModel(List<TimeSlot> employeeList)
    {
        this.timeslots = employeeList;
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return timeslots.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        TimeSlot row = timeslots.get(rowIndex);
        if(0 == columnIndex) {
            return row.getDate();
        }
        else if(1 == columnIndex) {
            return row.isOneHour();
        }
        else if(2 == columnIndex) {
            return row.isHalfHour();
        }
        return null;
    }
    
    @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       try{
       TimeSlot row = timeslots.get(rowIndex);
        
       //if(0 == columnIndex) {
         // row.setDate((String) aValue);
        //}
       if(1 == columnIndex) {
           
           row.setOneHour((Boolean) aValue);
           
           String t = Double.toString(start_time).toString();
           String [] time = t.split("[.]");
           System.out.println(time[1]);
           
           if(time[1].equals("0")){
               System.out.println(start_time);
               int hour = (Integer.parseInt(time[0])+ 1);
               String newttime = String.valueOf(hour) + ".00";
               start_time = Double.parseDouble(newttime);
               //if(rowIndex!=0){
                    TimeSlot row2 = timeslots.get(rowIndex+1);
                    row2.setDate(newttime);
                    timeslotpattern = timeslotpattern+"_"+newttime;
               //}
           }else if(time[1].equals("3")){
               System.out.println(start_time);
               int hour = (Integer.parseInt(time[0])+ 1);
               String newttime = String.valueOf(hour) + ".30";
               start_time = Double.parseDouble(newttime);
               //if(rowIndex!=0){
                TimeSlot row2 = timeslots.get(rowIndex+1);
                    row2.setDate(newttime);
                    timeslotpattern = timeslotpattern+"_"+newttime;
              // }
           }
           
       }
       else if(2 == columnIndex) {
           row.setHalfHour((Boolean) aValue);
           
           String t = Double.toString(start_time).toString();
           String [] time = t.split("[.]");
           System.out.println(time[1]);
           
           
           if(time[1].equals("0")){
               System.out.println(start_time);
               int hour = (Integer.parseInt(time[0]));
               String newttime = String.valueOf(hour) + ".30";
               start_time = Double.parseDouble(newttime);
               //if(rowIndex!=0){
                    TimeSlot row2 = timeslots.get(rowIndex+1);
                    row2.setDate(newttime);
                    timeslotpattern = timeslotpattern+"_"+newttime;
               //}
           }else if(time[1].equals("3")){
               System.out.println(start_time);
               int hour = (Integer.parseInt(time[0])+ 1);
               String newttime = String.valueOf(hour) + ".00";
               start_time = Double.parseDouble(newttime);
               //if(rowIndex!=0){
                    TimeSlot row2 = timeslots.get(rowIndex+1);
                    row2.setDate(newttime);
                    timeslotpattern = timeslotpattern+"_"+newttime;
               //}
           }
           
           //start_time = ((start_time)+new Double(0.30));
           //System.out.println(start_time);
           //if(rowIndex!=0){
             //  row.setDate(Double.toString(start_time));
           //}
       }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error!");
       }
   }
   
   @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
}

