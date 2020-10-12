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
public class TagLocationMng extends javax.swing.JFrame {

    /**
     * Creates new form TagLocationMng
     */
    public TagLocationMng() {
        initComponents();
        Show_Rooms_In_JTable();
        Show_TagRooms_In_JTable();
        //getBuildingDetails();
        getTagsDetails();
        
        //Set height for table columns
         jTable_ViewRooms.setRowHeight(40);
         jTable_ViewRoomsforTags.setRowHeight(40);
          
        //Set max size for frame
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
    
    
    public ArrayList<TagLocation> getTagLocationsList()
    {
      ArrayList<TagLocation> tagLocationsList = new ArrayList<TagLocation>();
      Connection connection  = getConnection();
      
      String query = "SELECT * FROM `roomsfortags`";
      Statement st;
      ResultSet rs;
      
     try{
         st = connection.createStatement();
         rs = st.executeQuery(query);
         TagLocation tagLocation;
         while(rs.next())
         {
           tagLocation = new TagLocation(rs.getInt("TagRoomId"), rs.getString("TagName"), rs.getInt("BuildingId"), rs.getString("RoomType"));  
           tagLocationsList.add(tagLocation);
           
         }
     } catch(Exception e){
        e.printStackTrace();
     }
     return tagLocationsList;
    }
    
    
    //Display tag location data in JTable
    
    public void Show_TagRooms_In_JTable(){
        ArrayList<TagLocation> list = getTagLocationsList();
        DefaultTableModel model = (DefaultTableModel)jTable_ViewRoomsforTags.getModel();
        Object[] row = new Object[4];
        for(int  i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getTagLocationId();
            row[1] = list.get(i).getTagName();
            row[2] = list.get(i).getBuildingId();
            row[3] = list.get(i).getRoomType();
            
            model.addRow(row);
            
        }
        
    }
    
    
     //Execute the sql query
    public void  executeSQlQuery(String query, String message)
    {
        Connection con = getConnection();
        Statement st;
        try{
            st = con.createStatement();
            if((st.executeUpdate(query)) == 1)
            {
                
                //refreash jtable data
                DefaultTableModel model = (DefaultTableModel)jTable_ViewRoomsforTags.getModel();
                model.setRowCount(0);
                Show_TagRooms_In_JTable();
                
                JOptionPane.showMessageDialog(null, "Room for tag "+message+" Successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Room for tag Not"+message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
   /* 
    //Get building details the dropdown list
    public void getBuildingDetails(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resBuilding;
          
          String getBuildingQuery = "SELECT * from location";
          try{
              statement = con.createStatement();
              resBuilding = statement.executeQuery(getBuildingQuery);
              
              while(resBuilding.next()){
                  jComboBox_BuildingName.addItem(resBuilding.getString("BuildingName"));
                  jComboBox_RoomName.addItem(resBuilding.getString("RoomName"));
                  jComboBox_RoomType.addItem(resBuilding.getString("RoomType"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    */
    
    //Get tag name for the dropdown list
    public void getTagsDetails(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resTag;
          
          String getBuildingQuery = "SELECT DISTINCT TagName FROM tags";
          try{
              statement = con.createStatement();
              resTag = statement.executeQuery(getBuildingQuery);
              
              while(resTag.next()){
                  jComboBox_TagName.addItem(resTag.getString("TagName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    
    
    
     /*------------------ Handle Rooms table ------------------*/
    
    
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblDayHour = new java.awt.Label();
        lblLec = new java.awt.Label();
        lblSub = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        lblPrgm = new java.awt.Label();
        lblGrp = new java.awt.Label();
        lblHome = new java.awt.Label();
        lblYS = new java.awt.Label();
        jLblStatistics = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_TagId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_TagName = new javax.swing.JComboBox<>();
        jButton_Insert = new javax.swing.JButton();
        jButton_Update = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jTextField_BuildingId = new javax.swing.JTextField();
        jTextField_RoomType = new javax.swing.JTextField();
        jButton_back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_ViewRoomsforTags = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_ViewRooms = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(100, 149, 237));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        lblDayHour.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDayHour.setText("Working Daya & Hours |");
        lblDayHour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDayHourMouseClicked(evt);
            }
        });

        lblLec.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLec.setText("Lecturers |");
        lblLec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLecMouseClicked(evt);
            }
        });

        lblSub.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSub.setText("Subjects |");
        lblSub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubMouseClicked(evt);
            }
        });

        label3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label3.setText(" Tags |");

        label4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label4.setText("Students |");

        label5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label5.setText("Location |");

        lblPrgm.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPrgm.setText("Programme |");

        lblGrp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblGrp.setText("Student Groups  |");

        lblHome.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHome.setText("Home |");
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });

        lblYS.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblYS.setText("Years & Semesters |");

        jLblStatistics.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLblStatistics.setText("Statistics");
        jLblStatistics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblStatisticsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDayHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lblPrgm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLblStatistics)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLblStatistics)
                    .addComponent(lblYS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDayHour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPrgm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("TagLocation ID");

        jTextField_TagId.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tag Name ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Building ID");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Room Type");

        jButton_Insert.setBackground(new java.awt.Color(0, 51, 204));
        jButton_Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Insert.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Insert.setText("INSERT");
        jButton_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
            }
        });

        jButton_Update.setBackground(new java.awt.Color(0, 102, 0));
        jButton_Update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Update.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Update.setText("UPDATE");
        jButton_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });

        jButton_Delete.setBackground(new java.awt.Color(255, 0, 0));
        jButton_Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Delete.setText("DELETE");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        jTextField_BuildingId.setEditable(false);

        jTextField_RoomType.setEditable(false);

        jButton_back.setBackground(new java.awt.Color(153, 102, 0));
        jButton_back.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_back.setText("Back To Dashboard");
        jButton_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_back, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_TagName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_TagId, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_BuildingId, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_RoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_TagId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox_TagName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_BuildingId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_RoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_back, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Manage Rooms For Tags");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Assign Room for Tags");

        jTable_ViewRoomsforTags.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TagLocation ID", "Tag Name", "Building Id", "Room Type"
            }
        ));
        jTable_ViewRoomsforTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ViewRoomsforTagsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_ViewRoomsforTags);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("View Rooms for Tags");

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

        jPanel4.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("View Location Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(311, 311, 311))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(303, 303, 303))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblDayHourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHourMouseClicked

    }//GEN-LAST:event_lblDayHourMouseClicked

    private void lblLecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLecMouseClicked
        // TODO add your handling code here:
        /*LecturerManage lecMng = new LecturerManage();
        lecMng.setSize(2000, 2000);
        lecMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();*/
    }//GEN-LAST:event_lblLecMouseClicked

    private void lblSubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubMouseClicked
        // TODO add your handling code here:
        /*SubjectManage subMng = new SubjectManage();
        subMng.setSize(2000, 2000);
        subMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();*/
    }//GEN-LAST:event_lblSubMouseClicked

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked

        Home home = new Home();
        home.setSize(2000, 2000);
        home.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

    private void jLblStatisticsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblStatisticsMouseClicked

        StatisticsDashboard stMng = new StatisticsDashboard();
        stMng.setSize(2000, 2000);
        stMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLblStatisticsMouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertActionPerformed
        
        String tagName = jComboBox_TagName.getSelectedItem().toString();
        String buildingId = jTextField_BuildingId.getText().toString();
        String roomType = jTextField_RoomType.getText().toString();
	
        if(buildingId.equals("")){
            JOptionPane.showMessageDialog(null, "building Id and Room Type is required !!!");   
        }else{
            String query  = "INSERT INTO roomsfortags (TagName, BuildingId, RoomType) VALUES ('"+tagName+"','"+buildingId+"','"+roomType+"')";
            executeSQlQuery(query,"Inserted");
        }
        
        
        /*
       int roomNameCount = 0;
        
        if(tagName.equals("")){
            JOptionPane.showMessageDialog(null, "Tag Name is required !!!");   
        }else if(buildingName.equals("")){
            JOptionPane.showMessageDialog(null, "Building Name is required !!!");   
        }else if(roomName.equals("")){
            JOptionPane.showMessageDialog(null, "Room Name is required !!!");   
        }else if(roomType.equals("")){
            JOptionPane.showMessageDialog(null, "Room Type is required !!!");   
        }else{
            
            
            Connection connection = getConnection();
            String queryRoomName = "SELECT COUNT(RoomName) from roomsfortags WHERE RoomName = '"+roomName+"'";
            Statement statement;
            ResultSet res;

            try{
                statement = connection.createStatement();
                res = statement.executeQuery(queryRoomName);
                res.next();
                roomNameCount = res.getInt(1);
            }catch(Exception e){
                e.printStackTrace();
            }
        
            if(roomNameCount>0){
                 JOptionPane.showMessageDialog(null, roomName+ " room is already exist ");   
            }else{
                 
		String query  = "INSERT INTO roomsfortags (TagName, BuildingName, RoomName, RoomType) VALUES ('"+tagName+"','"+buildingName+"','"+roomName+"','"+roomType+"')";
                executeSQlQuery(query,"Inserted");
		}
        }   */    
        
    }//GEN-LAST:event_jButton_InsertActionPerformed

    private void jTable_ViewRoomsforTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewRoomsforTagsMouseClicked
        
        int  i = jTable_ViewRoomsforTags.getSelectedRow();
        TableModel model = jTable_ViewRoomsforTags.getModel();
        jTextField_TagId.setText(model.getValueAt(i,0).toString());
        jComboBox_TagName.setSelectedItem(model.getValueAt(i,1).toString());
        jTextField_BuildingId.setText(model.getValueAt(i,2).toString());
        jTextField_RoomType.setText(model.getValueAt(i,4).toString());
        
    }//GEN-LAST:event_jTable_ViewRoomsforTagsMouseClicked

    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateActionPerformed
       
        String tagRoomId = jTextField_TagId.getText();
        String tagName = jComboBox_TagName.getSelectedItem().toString();
        String buildingName = jTextField_BuildingId.getText().toString();
        String roomType = jTextField_RoomType.getText().toString();
        
        String updateQuery = "UPDATE roomsfortags SET TagName = '"+tagName+"', BuildingName = '"+buildingName+"', RoomType= '"+roomType+"' WHERE TagRoomId = " +tagRoomId;
        executeSQlQuery(updateQuery, "Room details Updated");
    }//GEN-LAST:event_jButton_UpdateActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
       
        String query = "DELETE FROM roomsfortags WHERE TagRoomId = "+jTextField_TagId.getText();
        executeSQlQuery(query,"Deleted");
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jTable_ViewRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewRoomsMouseClicked

        int  i = jTable_ViewRooms.getSelectedRow();
        TableModel model = jTable_ViewRooms.getModel();
        //jTextField_Id.setText(model.getValueAt(i,0).toString());
        jTextField_BuildingId.setText(model.getValueAt(i,0).toString());
        jTextField_RoomType.setText(model.getValueAt(i,3).toString());
    }//GEN-LAST:event_jTable_ViewRoomsMouseClicked

    private void jButton_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_backActionPerformed
        
        RoomMngDashboard roomMngdb = new RoomMngDashboard();
        roomMngdb.setSize(2000, 2000);
        roomMngdb.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_backActionPerformed

    
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
            java.util.logging.Logger.getLogger(TagLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TagLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TagLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TagLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TagLocationMng().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Insert;
    private javax.swing.JButton jButton_Update;
    private javax.swing.JButton jButton_back;
    private javax.swing.JComboBox<String> jComboBox_TagName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblStatistics;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_ViewRooms;
    private javax.swing.JTable jTable_ViewRoomsforTags;
    private javax.swing.JTextField jTextField_BuildingId;
    private javax.swing.JTextField jTextField_RoomType;
    private javax.swing.JTextField jTextField_TagId;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label lblDayHour;
    private java.awt.Label lblGrp;
    private java.awt.Label lblHome;
    private java.awt.Label lblLec;
    private java.awt.Label lblPrgm;
    private java.awt.Label lblSub;
    private java.awt.Label lblYS;
    // End of variables declaration//GEN-END:variables
}
