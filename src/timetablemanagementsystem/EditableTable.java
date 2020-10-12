/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.event.*;
import static timetablemanagementsystem.TableModel.timeslotpattern;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditableTable extends JFrame
{
    static int id_n = 0;
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    
    
    public EditableTable()
    {
        
        TimeSlot t1 = new TimeSlot("8.30", false, false); 
        TimeSlot t2 = new TimeSlot("", false, false); 
        TimeSlot t3 = new TimeSlot("", false, false); 
        TimeSlot t4 = new TimeSlot("", false, false);
        TimeSlot t5 = new TimeSlot("", false, false);
        TimeSlot t6 = new TimeSlot("", false, false); 
        TimeSlot t7 = new TimeSlot("", false, false);
        TimeSlot t8 = new TimeSlot("", false, false);
        TimeSlot t9 = new TimeSlot("", false, false);
        TimeSlot t10 = new TimeSlot("", false, false);
        TimeSlot t11 = new TimeSlot("", false, false);
        TimeSlot t12 = new TimeSlot("", false, false);
        TimeSlot t13 = new TimeSlot("", false, false);
        TimeSlot t14 = new TimeSlot("", false, false);
        TimeSlot t15 = new TimeSlot("", false, false);
        TimeSlot t16 = new TimeSlot("", false, false);
        TimeSlot t17 = new TimeSlot("", false, false);
        TimeSlot t18 = new TimeSlot("", false, false);
        TimeSlot t19 = new TimeSlot("", false, false);
        TimeSlot t20 = new TimeSlot("", false, false);
        
        //build the list
        List<TimeSlot> list = new ArrayList<TimeSlot>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        list.add(t8);
        list.add(t9);
        list.add(t10);
        list.add(t11);
        list.add(t12);
        list.add(t13);
        list.add(t14);
        list.add(t15);
        list.add(t16);
        list.add(t17);
        list.add(t18);
        list.add(t19);
        list.add(t20);
         
        //create the model
        TableModel model = new TableModel(list);
        

        JFrame f = new JFrame("Add Time Slots");

        
       // f.setSize(690, 300);
        //f.setLocation(100, 150);
        
        JButton btn = new JButton("Add row ");
        Dimension size = btn.getPreferredSize();
        btn.setBounds(50,400, size.width, size.height);
       
        
        
        //create the table
        JTable table = new JTable(model);
        JScrollPane tb = new JScrollPane(table);
        
        
        
        f.add(btn) ;
        f.add(tb);
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // you can call any code you want here
                   
                    System.out.println(id_n);
                    System.out.println(timeslotpattern);
                    String q = "INSERT INTO timeslot (TimetableID, timeslots) VALUES (" + id_n + ", '" + timeslotpattern + "');";
                    System.out.println(q);
                    MyDBConnection mdbc = new MyDBConnection();
                    mdbc.init();
                    con=(Connection) mdbc.getMyConnection();
                    Statement st = con.createStatement();  
                    st.executeUpdate(q);
                    JOptionPane.showMessageDialog(null,"Data Successfully Inserted!");
                    
                    timeslotdisplayer t = new timeslotdisplayer();
                    t.setVisible(true);
                    
                } catch (Exception ex) {
                    Logger.getLogger(EditableTable.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        });
        
        
        
        //add the table to the frame
        //this.add(new JScrollPane(table));
        
         
        //this.setTitle("Editable Table Example");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        f.pack();
        f.setVisible(true);
    }
     
    public static void generateTimeSlots(int id)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditableTable();
                id_n = id;
            }
        });
    }   
}