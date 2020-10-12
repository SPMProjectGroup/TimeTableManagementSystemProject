/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DELL
 */
public class ConSessionLocationMng extends javax.swing.JFrame {

    /**
     * Creates new form ConSessionLocationMng
     */
    public ConSessionLocationMng() {
        initComponents();
        Show_Rooms_In_JTable();
        Show_ConSessions_In_JTable();
        
        jTable_ViewConSessions.setRowHeight(40);
        jTable_ViewRooms.setRowHeight(40);
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }

    
    
     //Create database connection
    public Connection getConnection()
    {
        Connection con;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetablemanagementsystem", "root", "");
            return con;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    
    
     /*------------------ Handle Consecutive Session details table ------------------*/
    
    public ArrayList<ConSessionModel> getConSessionList()
    {
      ArrayList<ConSessionModel> conSessionList = new ArrayList<ConSessionModel>();
      Connection connection  = getConnection();
      
      String query = "SELECT * FROM `consecutivesessions`";
      Statement st;
      ResultSet rs;
      
     try{
         st = connection.createStatement();
         rs = st.executeQuery(query);
         ConSessionModel conSessionModel;
         while(rs.next())
         {
           conSessionModel = new ConSessionModel(rs.getInt("ConsecutiveSessionId"), rs.getString("Tag"), rs.getString("StudentGroups"), rs.getString("Subject"), rs.getInt("NoOfStudents"), rs.getInt("sessionDuration"), rs.getInt("Session1Id"), rs.getInt("Session2Id"));  
           conSessionList.add(conSessionModel);
           
         }
     } catch(Exception e){
        e.printStackTrace();
     }
     return conSessionList;
    }
    
    
    //Display subjects data in JTable
    public void Show_ConSessions_In_JTable(){
        ArrayList<ConSessionModel> list = getConSessionList();
        DefaultTableModel model = (DefaultTableModel)jTable_ViewConSessions.getModel();
        Object[] row = new Object[8];
        for(int  i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getConSessionId();
            row[1] = list.get(i).getTag();
            row[2] = list.get(i).getStudentGroups();
            row[3] = list.get(i).getSubject();
            row[4] = list.get(i).getNoOfStudents();
            row[5] = list.get(i).getSessionDuration();
            row[6] = list.get(i).getSession1Id();
            row[7] = list.get(i).getSession2Id();
            
            model.addRow(row);
            
        }
        
    }
    
    //Execute the subjects sql query
    public void  executeSQlQuery(String query, String message)
    {
        Connection con = getConnection();
        Statement st;
        try{
            st = con.createStatement();
            DefaultTableModel model = (DefaultTableModel)jTable_ViewConSessions.getModel();
            model.setRowCount(0);
            Show_ConSessions_In_JTable();
            /*
            if((st.executeUpdate(query)) == 1)
            {
                
                //refreash jtable data
                DefaultTableModel model = (DefaultTableModel)jTable_DisplaySubjects.getModel();
                model.setRowCount(0);
                Show_Subjects_In_JTable();
                
                JOptionPane.showMessageDialog(null, "Room for tag "+message+" Successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Room for tag Not"+message);
            }*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
      /*------------------ Handle Locations table ------------------*/
    
    
     //execute sql queries.
    public void executeSQLQuery(String query, String message){
        
        Connection con = getConnection();
        Statement statement;
        
        try{
            statement = con.createStatement();
            DefaultTableModel model = (DefaultTableModel)jTable_ViewRooms.getModel();
            model.setRowCount(0);
            Show_Rooms_In_JTable();
                /*
            if((statement.executeUpdate(query)) == 1){
                
                //refreash jtable data
                DefaultTableModel model = (DefaultTableModel)jTable_ViewRooms.getModel();
                model.setRowCount(0);
                Show_Room_Locations_In_JTable();
                
                JOptionPane.showMessageDialog(null, message+" successfully");
            }else{
                 JOptionPane.showMessageDialog(null, "Not "+message); 
            }*/
        }catch(Exception ex){
                ex.printStackTrace();
        }
    }
    
    
    public ArrayList<Room> getRoomsList()
    {
      ArrayList<Room> roomsList = new ArrayList<Room>();
      Connection connection  = getConnection();
      
      String query = "SELECT * FROM `location`";
      Statement st;
      ResultSet rs;
      
     try{
         st = connection.createStatement();
         rs = st.executeQuery(query);
         Room room;
         while(rs.next())
         {
           room = new Room(rs.getInt("LocationID"), rs.getString("BuildingName"), rs.getString("RoomName"),rs.getString("RoomType"),rs.getInt("FloorNo"),rs.getInt("RoomNo"));  
           roomsList.add(room);
           
         }
     } catch(Exception e){
        e.printStackTrace();
     }
     return roomsList;
    }
    
    
    //Display Rooms data in JTable
    
    public void Show_Rooms_In_JTable(){
        ArrayList<Room> list = getRoomsList();
        DefaultTableModel model = (DefaultTableModel)jTable_ViewRooms.getModel();
        Object[] row = new Object[6];
        for(int  i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getBuildingName();
            row[2] = list.get(i).getRoomName();
            row[3] = list.get(i).getRoomType();
            row[4] = list.get(i).getFloorNo();
            row[5] = list.get(i).getRoomNo();
            
            model.addRow(row);
            
        }
        
    }
    
       
    //execute sql queries.
    public void executeSqlQuery(String query, String message){
        
        Connection con = getConnection();
        Statement statement;
        
        try{
            statement = con.createStatement();
            if((statement.executeUpdate(query)) == 1){
                JOptionPane.showMessageDialog(null, message+" successfully");
            }else{
                 JOptionPane.showMessageDialog(null, "Not "+message); 
            }
        }catch(Exception ex){
                ex.printStackTrace();
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_ConSessionId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_ConTag = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_BuildingId = new javax.swing.JTextField();
        jButton_Insert = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_ViewConSessions = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_ViewRooms = new javax.swing.JTable();
        jButton_back = new javax.swing.JButton();
        jButton_Viewconsessionlocation = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblConSession1 = new java.awt.Label();
        lblRoomAllocate1 = new java.awt.Label();
        lblSession1 = new java.awt.Label();
        lblParallelSe1 = new java.awt.Label();
        lblBotOverlappingSession1 = new java.awt.Label();
        lblNotAvTime1 = new java.awt.Label();
        lblGenTimeTables1 = new java.awt.Label();
        lblViewTimeTables1 = new java.awt.Label();
        jPanel12 = new javax.swing.JPanel();
        lblDayHour4 = new java.awt.Label();
        lblLec4 = new java.awt.Label();
        lblSub4 = new java.awt.Label();
        lblTags = new java.awt.Label();
        lblStudents = new java.awt.Label();
        lblLocation = new java.awt.Label();
        lblPrgrm = new java.awt.Label();
        lblStdGrp = new java.awt.Label();
        lblHome4 = new java.awt.Label();
        lblYS4 = new java.awt.Label();
        lblStat = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("Manage Rooms for Consecutive Sessions ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Assign rooms for consecutive sessions");

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Consecutive Session ID");

        jTextField_ConSessionId.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tag");

        jTextField_ConTag.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Building ID");

        jTextField_BuildingId.setEditable(false);

        jButton_Insert.setBackground(new java.awt.Color(0, 51, 204));
        jButton_Insert.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Insert.setText("INSERT");
        jButton_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField_ConTag, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_ConSessionId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_BuildingId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_ConSessionId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_ConTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_BuildingId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("View Session Details");

        jTable_ViewConSessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ConsecutiveSessionId", "Tag", "StudentGroups", "Subject", "NoOfStudents", "SessionDuration", "Session1Id", "Session2Id"
            }
        ));
        jTable_ViewConSessions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ViewConSessionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_ViewConSessions);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("View Location Details");

        jTable_ViewRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Building ID", "Building Name", "Room Name", "Room Type", "Floor No", "Room No"
            }
        ));
        jTable_ViewRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ViewRoomsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_ViewRooms);

        jButton_back.setBackground(new java.awt.Color(153, 102, 0));
        jButton_back.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_back.setText("Back to Dashboard");
        jButton_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_backActionPerformed(evt);
            }
        });

        jButton_Viewconsessionlocation.setBackground(new java.awt.Color(0, 102, 102));
        jButton_Viewconsessionlocation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Viewconsessionlocation.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Viewconsessionlocation.setText("View Consecutive  Session Locations");
        jButton_Viewconsessionlocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ViewconsessionlocationActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(153, 153, 255));

        lblConSession1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblConSession1.setForeground(new java.awt.Color(0, 0, 102));
        lblConSession1.setText("CONSECUTIVE SESSIONS");
        lblConSession1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConSession1MouseClicked(evt);
            }
        });

        lblRoomAllocate1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblRoomAllocate1.setForeground(new java.awt.Color(0, 0, 102));
        lblRoomAllocate1.setText("ROOM ALLOCATION");
        lblRoomAllocate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRoomAllocate1MouseClicked(evt);
            }
        });

        lblSession1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSession1.setForeground(new java.awt.Color(0, 0, 102));
        lblSession1.setText("SESSIONS");
        lblSession1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSession1MouseClicked(evt);
            }
        });

        lblParallelSe1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblParallelSe1.setForeground(new java.awt.Color(0, 0, 102));
        lblParallelSe1.setText("PARALLEL SESSIONS");
        lblParallelSe1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblParallelSe1MouseClicked(evt);
            }
        });

        lblBotOverlappingSession1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblBotOverlappingSession1.setForeground(new java.awt.Color(0, 0, 102));
        lblBotOverlappingSession1.setText("NOT OVERLAPPING SESSIONS");
        lblBotOverlappingSession1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotOverlappingSession1MouseClicked(evt);
            }
        });

        lblNotAvTime1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNotAvTime1.setForeground(new java.awt.Color(0, 0, 102));
        lblNotAvTime1.setText("NOT AVAILABLE TIME");
        lblNotAvTime1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNotAvTime1MouseClicked(evt);
            }
        });

        lblGenTimeTables1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblGenTimeTables1.setForeground(new java.awt.Color(0, 0, 102));
        lblGenTimeTables1.setText("GENERATE TIMETABLES");

        lblViewTimeTables1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblViewTimeTables1.setForeground(new java.awt.Color(0, 0, 102));
        lblViewTimeTables1.setText("VIEW TIMETABLES");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSession1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRoomAllocate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(lblConSession1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblParallelSe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblBotOverlappingSession1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(lblNotAvTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lblGenTimeTables1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblViewTimeTables1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblViewTimeTables1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenTimeTables1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotAvTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBotOverlappingSession1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblParallelSe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSession1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRoomAllocate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConSession1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(100, 149, 237));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        lblDayHour4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDayHour4.setText("Working Day & Hours |");
        lblDayHour4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDayHour4MouseClicked(evt);
            }
        });

        lblLec4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblLec4.setText("Lecturers |");
        lblLec4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLec4MouseClicked(evt);
            }
        });

        lblSub4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSub4.setText("Subjects |");
        lblSub4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub4MouseClicked(evt);
            }
        });

        lblTags.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTags.setText(" Tags |");
        lblTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTagsMouseClicked(evt);
            }
        });

        lblStudents.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStudents.setText("Students |");
        lblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStudentsMouseClicked(evt);
            }
        });

        lblLocation.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblLocation.setText("Location |");
        lblLocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLocationMouseClicked(evt);
            }
        });

        lblPrgrm.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPrgrm.setText("Programme |");
        lblPrgrm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrgrmMouseClicked(evt);
            }
        });

        lblStdGrp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStdGrp.setText("Student Groups  |");
        lblStdGrp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStdGrpMouseClicked(evt);
            }
        });

        lblHome4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblHome4.setText("Home |");
        lblHome4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHome4MouseClicked(evt);
            }
        });

        lblYS4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblYS4.setText("Years & Semesters");
        lblYS4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblYS4MouseClicked(evt);
            }
        });

        lblStat.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStat.setText("Statistics |");
        lblStat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHome4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDayHour4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblLec4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lblPrgrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblStdGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStdGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrgrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblYS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHome4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDayHour4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLec4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(339, 339, 339))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(432, 432, 432))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Viewconsessionlocation, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jButton_Viewconsessionlocation, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_back, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertActionPerformed

        String consessionId = jTextField_ConSessionId.getText();
        String tag = jTextField_ConTag.getText();
        String buildingId = jTextField_BuildingId.getText();

        //String addQuery  = "INSERT INTO roomsforsessions (sessionId, tag, buildingId) VALUES ('"+sessionId+"','"+tag+"','"+buildingId+"')";
        //executeSqlQuery(addQuery,"Room for Session Inserted");

        int consessionIdCount = 0;
        
        if(consessionId.equals("")){
            
            JOptionPane.showMessageDialog(null, "Session selection is required !!!");   
        }else if(buildingId.equals("")){
            JOptionPane.showMessageDialog(null, "Building selection is required !!!");   
        }else{
            
        Connection connection = getConnection();
        String queryconsessionId = "SELECT COUNT(ConSessionId) from  roomsforconsecutivesessions WHERE ConSessionId = '"+consessionId+"'";
        Statement statement;
        ResultSet res;

        try{
            statement = connection.createStatement();
            res = statement.executeQuery(queryconsessionId);
            res.next();
            consessionIdCount = res.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(consessionIdCount>0){
            JOptionPane.showMessageDialog(null, "Consecutive Session ID " +consessionId+ " is already assigned for a building ");
        }else{

            String addQuery  = "INSERT INTO roomsforconsecutivesessions (ConSessionId, ConTag, BuildingId) VALUES ('"+consessionId+"','"+tag+"','"+buildingId+"')";
            executeSqlQuery(addQuery,"Room for Consecutive Session Assigned");
        }
        
        }
    }//GEN-LAST:event_jButton_InsertActionPerformed

    private void jButton_ViewconsessionlocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ViewconsessionlocationActionPerformed

        ConSessionLocationView consessionview = new ConSessionLocationView();
        consessionview.setSize(2000, 2000);
        consessionview.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_ViewconsessionlocationActionPerformed

    private void jButton_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_backActionPerformed

        RoomMngDashboard roomMngdb = new RoomMngDashboard();
        roomMngdb.setSize(2000, 2000);
        roomMngdb.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_backActionPerformed

    private void jTable_ViewRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewRoomsMouseClicked

        int  i = jTable_ViewRooms.getSelectedRow();
        TableModel model = jTable_ViewRooms.getModel();
        jTextField_BuildingId.setText(model.getValueAt(i,0).toString());

    }//GEN-LAST:event_jTable_ViewRoomsMouseClicked

    private void jTable_ViewConSessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewConSessionsMouseClicked
        
        int  i = jTable_ViewConSessions.getSelectedRow();
        TableModel model = jTable_ViewConSessions.getModel();
        jTextField_ConSessionId.setText(model.getValueAt(i,0).toString());
        jTextField_ConTag.setText(model.getValueAt(i,1).toString());
    }//GEN-LAST:event_jTable_ViewConSessionsMouseClicked

    private void lblConSession1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConSession1MouseClicked
        // TODO add your handling code here:
        Add_Consecutive_sessions addConSession = new Add_Consecutive_sessions();
        addConSession.setSize(2000, 2000);
        addConSession.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblConSession1MouseClicked

    private void lblRoomAllocate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRoomAllocate1MouseClicked
        // TODO add your handling code here:
        RoomMngDashboard roomMngDB = new RoomMngDashboard();
        roomMngDB.setSize(2000, 2000);
        roomMngDB.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblRoomAllocate1MouseClicked

    private void lblSession1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSession1MouseClicked
        // TODO add your handling code here:
        AddSessions addSessions = new AddSessions();
        addSessions.setSize(2000, 2000);
        addSessions.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblSession1MouseClicked

    private void lblParallelSe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParallelSe1MouseClicked
        // TODO add your handling code here:
        Add_Parallel_Sessions addPrSession = new Add_Parallel_Sessions();
        addPrSession.setSize(2000, 2000);
        addPrSession.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblParallelSe1MouseClicked

    private void lblBotOverlappingSession1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotOverlappingSession1MouseClicked
        // TODO add your handling code here:

        Add_Not_Overlapping_sessions addNotOvSession = new Add_Not_Overlapping_sessions();
        addNotOvSession.setSize(2000, 2000);
        addNotOvSession.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblBotOverlappingSession1MouseClicked

    private void lblNotAvTime1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNotAvTime1MouseClicked
        // TODO add your handling code here:
        NotAvailableTimes notAvTimes = new NotAvailableTimes();
        notAvTimes.setSize(2000, 2000);
        notAvTimes.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblNotAvTime1MouseClicked

    private void lblDayHour4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHour4MouseClicked

    }//GEN-LAST:event_lblDayHour4MouseClicked

    private void lblLec4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLec4MouseClicked
        // TODO add your handling code here:
        LecturerManage lecMng = new LecturerManage();
        lecMng.setSize(2000, 2000);
        lecMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblLec4MouseClicked

    private void lblSub4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub4MouseClicked
        // TODO add your handling code here:
        SubjectManage subMng = new SubjectManage();
        subMng.setSize(2000, 2000);
        subMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblSub4MouseClicked

    private void lblTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTagsMouseClicked
        // TODO add your handling code here:
        Tag tag = new Tag();
        tag.setSize(2000, 2000);
        tag.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblTagsMouseClicked

    private void lblStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudentsMouseClicked
        // TODO add your handling code here:
        Student student = new Student();
        student.setSize(2000, 2000);
        student.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblStudentsMouseClicked

    private void lblLocationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLocationMouseClicked
        // TODO add your handling code here:
        LocationDetails locationDetails = new LocationDetails();
        locationDetails.setSize(2000, 2000);
        locationDetails.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblLocationMouseClicked

    private void lblPrgrmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrgrmMouseClicked
        // TODO add your handling code here:
        Add_Programme addPrgrm = new Add_Programme();
        addPrgrm.setSize(2000, 2000);
        addPrgrm.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblPrgrmMouseClicked

    private void lblStdGrpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStdGrpMouseClicked
        // TODO add your handling code here:
        Add_GroupNo addGrpNo = new Add_GroupNo();
        addGrpNo.setSize(2000, 2000);
        addGrpNo.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblStdGrpMouseClicked

    private void lblHome4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome4MouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setSize(2000, 2000);
        home.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblHome4MouseClicked

    private void lblYS4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYS4MouseClicked
        // TODO add your handling code here:
        Add_YearAndSemester addYrSem = new Add_YearAndSemester();
        addYrSem.setSize(2000, 2000);
        addYrSem.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblYS4MouseClicked

    private void lblStatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStatMouseClicked
        // TODO add your handling code here:
        StatisticsDashboard statMng = new StatisticsDashboard();
        statMng.setSize(2000, 2000);
        statMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblStatMouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConSessionLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConSessionLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConSessionLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConSessionLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConSessionLocationMng().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Insert;
    private javax.swing.JButton jButton_Viewconsessionlocation;
    private javax.swing.JButton jButton_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_ViewConSessions;
    private javax.swing.JTable jTable_ViewRooms;
    private javax.swing.JTextField jTextField_BuildingId;
    private javax.swing.JTextField jTextField_ConSessionId;
    private javax.swing.JTextField jTextField_ConTag;
    private java.awt.Label lblBotOverlappingSession;
    private java.awt.Label lblBotOverlappingSession1;
    private java.awt.Label lblConSession;
    private java.awt.Label lblConSession1;
    private java.awt.Label lblDayHour4;
    private java.awt.Label lblGenTimeTables;
    private java.awt.Label lblGenTimeTables1;
    private java.awt.Label lblHome4;
    private java.awt.Label lblLec4;
    private java.awt.Label lblLocation;
    private java.awt.Label lblNotAvTime;
    private java.awt.Label lblNotAvTime1;
    private java.awt.Label lblParallelSe;
    private java.awt.Label lblParallelSe1;
    private java.awt.Label lblPrgrm;
    private java.awt.Label lblRoomAllocate;
    private java.awt.Label lblRoomAllocate1;
    private java.awt.Label lblSession;
    private java.awt.Label lblSession1;
    private java.awt.Label lblStat;
    private java.awt.Label lblStdGrp;
    private java.awt.Label lblStudents;
    private java.awt.Label lblSub4;
    private java.awt.Label lblTags;
    private java.awt.Label lblViewTimeTables;
    private java.awt.Label lblViewTimeTables1;
    private java.awt.Label lblYS4;
    // End of variables declaration//GEN-END:variables
}
