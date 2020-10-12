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


public class SubjectLocationMng extends javax.swing.JFrame {

    /**
     * Creates new form SubjectLocation
     */
    public SubjectLocationMng() {
        initComponents();
        getTagsDetails();
        Show_Subjects_In_JTable();
        Show_Rooms_In_JTable();
        
        jTable_DisplaySubjects.setRowHeight(40);
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
    
    
    /*------------------ Handle Subject table ------------------*/
    
    public ArrayList<Subject> getSubjectsList()
    {
      ArrayList<Subject> subjectsList = new ArrayList<Subject>();
      Connection connection  = getConnection();
      
      String query = "SELECT * FROM `subjects`";
      Statement st;
      ResultSet rs;
      
     try{
         st = connection.createStatement();
         rs = st.executeQuery(query);
         Subject subject;
         while(rs.next())
         {
           subject = new Subject(rs.getInt("SubjectID"), rs.getString("SubjectName"), rs.getString("SubjectCode"), rs.getString("OfferedYear"), rs.getString("OfferedSem"), rs.getInt("NoOfLecHours"), rs.getInt("NoOfTuteHours"), rs.getInt("NoOfLabHours"), rs.getInt("NoOfEvaluationHours"));  
           subjectsList.add(subject);
           
         }
     } catch(Exception e){
        e.printStackTrace();
     }
     return subjectsList;
    }
    
    
    //Display subjects data in JTable
    public void Show_Subjects_In_JTable(){
        ArrayList<Subject> list = getSubjectsList();
        DefaultTableModel model = (DefaultTableModel)jTable_DisplaySubjects.getModel();
        Object[] row = new Object[9];
        for(int  i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getSubjectId();
            row[1] = list.get(i).getSubjectName();
            row[2] = list.get(i).getSubjectCode();
            row[3] = list.get(i).getOfferedYear();
            row[4] = list.get(i).getOfferedSem();
            row[5] = list.get(i).getNoOfLecHours();
            row[6] = list.get(i).getNoOfTuteHours();
            row[7] = list.get(i).getNoOfLabHours();
            row[8] = list.get(i).getNoOfEvaluationHours();
            
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
            DefaultTableModel model = (DefaultTableModel)jTable_DisplaySubjects.getModel();
            model.setRowCount(0);
            Show_Subjects_In_JTable();
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
    
    
    
    /*------------------ Locations table ------------------*/
    
    
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
    
    

    //Get tag name from tag table for the dropdown list
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
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_RoomType = new javax.swing.JTextField();
        jTextField_SubjectId = new javax.swing.JTextField();
        jTextField_Id = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButton_Insert = new javax.swing.JButton();
        jComboBox_TagName = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_DisplaySubjects = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_ViewRooms = new javax.swing.JTable();
        jButton_ViewRoomLocations = new javax.swing.JButton();
        jButton_back = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

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
                .addGap(229, 229, 229)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
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
                .addContainerGap(44, Short.MAX_VALUE))
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

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Subject ID    :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Building ID   :");

        jTextField_RoomType.setEditable(false);

        jTextField_SubjectId.setEditable(false);

        jTextField_Id.setEditable(false);
        jTextField_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_IdActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Room Type  :");

        jButton_Insert.setBackground(new java.awt.Color(0, 51, 204));
        jButton_Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Insert.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Insert.setText("INSERT");
        jButton_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Subject Tag :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_RoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_SubjectId, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_TagName, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_SubjectId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jTextField_RoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_TagName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Assign Rooms for subjects");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Manage Rooms for Subjects ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("View Subjects & Room Details");

        jTable_DisplaySubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SubjectID", "SubjectName", "SubjectCode", "OfferedYear", "OfferedSem", "LecHours", "TuteHours", "LabHours", "Eva Hours"
            }
        ));
        jTable_DisplaySubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_DisplaySubjectsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_DisplaySubjects);

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

        jButton_ViewRoomLocations.setBackground(new java.awt.Color(0, 102, 102));
        jButton_ViewRoomLocations.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_ViewRoomLocations.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ViewRoomLocations.setText("VIEW ROOM LOCATIONS");
        jButton_ViewRoomLocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ViewRoomLocationsActionPerformed(evt);
            }
        });

        jButton_back.setBackground(new java.awt.Color(153, 102, 0));
        jButton_back.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_back.setText("Back to Dashboard");
        jButton_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_backActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("View Location Details");

        jPanel4.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_ViewRoomLocations, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(jButton_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(311, 311, 311))))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(267, 267, 267))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jButton_ViewRoomLocations, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_back, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void jTable_DisplaySubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DisplaySubjectsMouseClicked

        //Display selected row in JtextFields
        int  i = jTable_DisplaySubjects.getSelectedRow();
        TableModel model = jTable_DisplaySubjects.getModel();
        jTextField_SubjectId.setText(model.getValueAt(i,0).toString()); 
    }//GEN-LAST:event_jTable_DisplaySubjectsMouseClicked

    private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertActionPerformed
        
        //String query  = "INSERT INTO `roomsforsubjects` (`SubjectID`, `SubjectName`, `SubjectCode`, `OfferedYear`, `OfferedSem`, `NoOfLecHours`, `NoOfTuteHours`, `NoOfLabHours`, `NoOfEvaluationHours`, `SubjectTag`) VALUES "
                //+ "('"+jTextField_SubjectId.getText()+"','"+jTextField_SubjectName.getText()+"','"+jTextField_RoomType.getText()+"')";
        //executeSQlQuery(query,"Inserted");
        
        String subjectId = jTextField_SubjectId.getText();
        String buildingId = jTextField_Id.getText();
        String roomType = jTextField_RoomType.getText();
        String tagName = jComboBox_TagName.getSelectedItem().toString();
	
        
        //String addQuery  = "INSERT INTO roomsforsubjects (SubjectID, BuildingID, RoomType, SubjectTag) "
                //+ "VALUES ('"+subjectId+"','"+buildingId+"' ,'"+roomType+"','"+tagName+"')";
        //executeSqlQuery(addQuery,"Room for Subject Inserted");
        
        /*SubjectLocationView slview = new SubjectLocationView();
        slview.setSize(2000, 2000);
        slview.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();*/
        
        
         int SubjectIDCount = 0;
           int SubjectTagCount = 0;
        
       if(buildingId.equals("")){
            JOptionPane.showMessageDialog(null, "Building ID and Room Type is required !!!");   
        }else{
            
            
            Connection connection = getConnection();
            String query  = "SELECT COUNT(SubjectID, SubjectTag) from roomsforsubjects WHERE SubjectID = '"+subjectId+"' and SubjectTag  = '"+tagName+"'";
            Statement statement;
            ResultSet res;

            try{
                statement = connection.createStatement();
                res = statement.executeQuery(query);
                res.next();
                SubjectIDCount = res.getInt(1);
                SubjectTagCount = res.getInt(1);
            }catch(Exception e){
                e.printStackTrace();
            }
        
            if(SubjectIDCount>0 && SubjectTagCount>0){
                 JOptionPane.showMessageDialog(null, subjectId+ " Subject ID and Yag name is already exist, Cannot add this room ");   
            }else{
                 
		String addQuery  = "INSERT INTO roomsforsubjects (SubjectID, BuildingID, RoomType, SubjectTag) "
                + "VALUES ('"+subjectId+"','"+buildingId+"' ,'"+roomType+"','"+tagName+"')";
        executeSqlQuery(addQuery,"Room for Subject Inserted");
		}
        
       
    }                        
    }//GEN-LAST:event_jButton_InsertActionPerformed

    private void jTable_ViewRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewRoomsMouseClicked

        int  i = jTable_ViewRooms.getSelectedRow();
        TableModel model = jTable_ViewRooms.getModel();
        jTextField_Id.setText(model.getValueAt(i,0).toString());
        jTextField_RoomType.setText(model.getValueAt(i,3).toString());
    }//GEN-LAST:event_jTable_ViewRoomsMouseClicked

    private void jButton_ViewRoomLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ViewRoomLocationsActionPerformed
        
        SubjectLocationView slview = new SubjectLocationView();
        slview.setSize(2000, 2000);
        slview.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_ViewRoomLocationsActionPerformed

    private void jTextField_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_IdActionPerformed

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
            java.util.logging.Logger.getLogger(SubjectLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubjectLocationMng().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Insert;
    private javax.swing.JButton jButton_ViewRoomLocations;
    private javax.swing.JButton jButton_back;
    private javax.swing.JComboBox<String> jComboBox_TagName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblStatistics;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_DisplaySubjects;
    private javax.swing.JTable jTable_ViewRooms;
    private javax.swing.JTextField jTextField_Id;
    private javax.swing.JTextField jTextField_RoomType;
    private javax.swing.JTextField jTextField_SubjectId;
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
