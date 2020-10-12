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
import java.util.HashSet;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
/**
 *
 * @author acer
 */
public class NotAvailable extends javax.swing.JFrame {

    /**
     * Creates new form LecturerInfo
     *
     */
    int selectedLecturerId = 0;
    
    public NotAvailable() {
        initComponents();
    
         //for set max size for frame
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
        getLecturerNamesForFilterSession();
        getRoomNameDetails();
        getGroupNamesForSet();
        
    }
    
    //for get database connection
    public Connection getConnection(){
        
        Connection con;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/timetablemanagementsystem", "root", "");
            return con;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void getRoomNameDetails(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resBuilding;
          
          String getBuildingQuery = "SELECT DISTINCT RoomName from location";
          try{
              statement = con.createStatement();
              resBuilding = statement.executeQuery(getBuildingQuery);
              
              while(resBuilding.next()){
                  roomSlct.addItem(resBuilding.getString("RoomName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    public void getLecturerNamesForFilterSession(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resLec;
          
          String getLecturerQuery = "SELECT LecturerName,Title from lecturers";
          try{
              statement = con.createStatement();
              resLec = statement.executeQuery(getLecturerQuery);
              
              while(resLec.next()){
                  lctrSelect.addItem(resLec.getString("Title") + "." + resLec.getString("LecturerName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
   
    public void getGroupNamesForSet(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resLec;
          
          String getLecturerQuery = "SELECT SubGroupId from students";
          try{
              statement = con.createStatement();
              resLec = statement.executeQuery(getLecturerQuery);
              
              while(resLec.next()){
                  groupSlct.addItem(resLec.getString("SubGroupId"));
              }
          }catch(Exception e){
              e.printStackTrace();
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
    
     //generate random no for lecturer id
    public int generateUniqueLecID(){
   
//        int rndNumber = 100000;
//        Random random = new Random();
//        
//        for(int i =0; i < 6; i++ ){
//            rndNumber = rndNumber + random.nextInt(9);
//        }
//        return rndNumber; 

          int uniqueLecID = 100000; 
          int maxID = 0;
          
          Connection con = getConnection();
          Statement statement;
          ResultSet result;
          
          String getMaxTblQuery = "SELECT max(LecturerTableID) from lecturers";
          
          try{
              statement = con.createStatement();
              result = statement.executeQuery(getMaxTblQuery);
              result.next();
              maxID = result.getInt(1);
              
          }catch(Exception e){
              e.printStackTrace();
          }
        
          uniqueLecID = uniqueLecID + maxID;
          
          System.out.println("max : "+maxID);
          System.out.println("Unique ID : "+uniqueLecID);
          
          return uniqueLecID;
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
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAddLec = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblDayHour = new java.awt.Label();
        lblLec = new java.awt.Label();
        lblSub = new java.awt.Label();
        lblTags = new java.awt.Label();
        lblStudents = new java.awt.Label();
        lblLocation = new java.awt.Label();
        lblPrgrm = new java.awt.Label();
        lblStdGrp = new java.awt.Label();
        lblHome = new java.awt.Label();
        lblYS = new java.awt.Label();
        lblStat = new java.awt.Label();
        lblViewLec = new java.awt.Label();
        jLabel9 = new javax.swing.JLabel();
        roomSlct = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        lblConSession = new java.awt.Label();
        lblRoomAllocate = new java.awt.Label();
        lblSession = new java.awt.Label();
        lblParallelSe = new java.awt.Label();
        lblBotOverlappingSession = new java.awt.Label();
        lblNotAvTime = new java.awt.Label();
        lblGenTimeTables = new java.awt.Label();
        lblViewTimeTables = new java.awt.Label();
        lctrSelect = new javax.swing.JComboBox<>();
        groupSlct = new javax.swing.JComboBox<>();
        shours = new javax.swing.JComboBox<>();
        smin = new javax.swing.JComboBox<>();
        endhours = new javax.swing.JComboBox<>();
        endmin = new javax.swing.JComboBox<>();
        selectDay = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sessionSelect = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(570, 570, 570)
                .addComponent(jLabel13)
                .addContainerGap(513, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Not Available");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Lecturer");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Group");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Start Time");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("End Time");

        btnAddLec.setBackground(new java.awt.Color(0, 51, 204));
        btnAddLec.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddLec.setForeground(new java.awt.Color(255, 255, 255));
        btnAddLec.setText("ADD");
        btnAddLec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLecActionPerformed(evt);
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
            .addGap(0, 64, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(100, 149, 237));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        lblDayHour.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDayHour.setText("Working Day & Hours |");
        lblDayHour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDayHourMouseClicked(evt);
            }
        });

        lblLec.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblLec.setText("Lecturers |");
        lblLec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLecMouseClicked(evt);
            }
        });

        lblSub.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSub.setText("Subjects |");
        lblSub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubMouseClicked(evt);
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

        lblHome.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblHome.setText("Home |");
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });

        lblYS.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblYS.setText("Years & Semesters |");
        lblYS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblYSMouseClicked(evt);
            }
        });

        lblStat.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStat.setText("Statistics |");
        lblStat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStatMouseClicked(evt);
            }
        });

        lblViewLec.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblViewLec.setText("View All Lecturers");
        lblViewLec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewLecMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDayHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(lblYS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblViewLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblViewLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblStdGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPrgrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblYS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDayHour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(13, 13, 13))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Room");

        roomSlct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomSlctActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(153, 153, 255));

        lblConSession.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblConSession.setForeground(new java.awt.Color(0, 0, 102));
        lblConSession.setText("CONSECUTIVE SESSIONS");
        lblConSession.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConSessionMouseClicked(evt);
            }
        });

        lblRoomAllocate.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblRoomAllocate.setForeground(new java.awt.Color(0, 0, 102));
        lblRoomAllocate.setText("ROOM ALLOCATION");
        lblRoomAllocate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRoomAllocateMouseClicked(evt);
            }
        });

        lblSession.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSession.setForeground(new java.awt.Color(0, 0, 102));
        lblSession.setText("SESSIONS");
        lblSession.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSessionMouseClicked(evt);
            }
        });

        lblParallelSe.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblParallelSe.setForeground(new java.awt.Color(0, 0, 102));
        lblParallelSe.setText("PARALLEL SESSIONS");
        lblParallelSe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblParallelSeMouseClicked(evt);
            }
        });

        lblBotOverlappingSession.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblBotOverlappingSession.setForeground(new java.awt.Color(0, 0, 102));
        lblBotOverlappingSession.setText("NOT OVERLAPPING SESSIONS");
        lblBotOverlappingSession.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotOverlappingSessionMouseClicked(evt);
            }
        });

        lblNotAvTime.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNotAvTime.setForeground(new java.awt.Color(0, 0, 102));
        lblNotAvTime.setText("NOT AVAILABLE TIME");
        lblNotAvTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNotAvTimeMouseClicked(evt);
            }
        });

        lblGenTimeTables.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblGenTimeTables.setForeground(new java.awt.Color(0, 0, 102));
        lblGenTimeTables.setText("GENERATE TIMETABLES");

        lblViewTimeTables.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblViewTimeTables.setForeground(new java.awt.Color(0, 0, 102));
        lblViewTimeTables.setText("VIEW TIMETABLES");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSession, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRoomAllocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(lblConSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblParallelSe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblBotOverlappingSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(lblNotAvTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lblGenTimeTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblViewTimeTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblViewTimeTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenTimeTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotAvTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBotOverlappingSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblParallelSe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRoomAllocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConSession, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        groupSlct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Group" }));
        groupSlct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupSlctItemStateChanged(evt);
            }
        });
        groupSlct.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                groupSlctPropertyChange(evt);
            }
        });

        shours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        smin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "30" }));

        endhours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        endhours.setMinimumSize(new java.awt.Dimension(33, 20));

        endmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "30" }));
        endmin.setPreferredSize(new java.awt.Dimension(33, 20));

        selectDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        selectDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDayActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Day");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Session");

        sessionSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Session" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(567, 567, 567)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(372, 372, 372)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(245, 245, 245)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(endhours, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(endmin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(shours, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(smin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(195, 195, 195)
                                            .addComponent(btnAddLec, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(sessionSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(selectDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                        .addGap(254, 254, 254)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(roomSlct, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(groupSlct, 0, 336, Short.MAX_VALUE)
                                            .addComponent(lctrSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(0, 677, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lctrSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomSlct, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(groupSlct))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(sessionSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(smin)
                    .addComponent(shours, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endhours, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnAddLec, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddLecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLecActionPerformed
        
        boolean lec=false,a_room=false,a_group=false;
        
        String lecName = lctrSelect.getSelectedItem().toString();
        String room = roomSlct.getSelectedItem().toString();
        String group = groupSlct.getSelectedItem().toString();
        String day =selectDay.getSelectedItem().toString();
        
        String str_hours = shours.getSelectedItem().toString();
        String str_min = smin.getSelectedItem().toString();
        
        String end_hours = endhours.getSelectedItem().toString();
        String end_min = endmin.getSelectedItem().toString();
                
        if(groupSlct.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Warning!! Select a Group!!");   
        }else{
            
            if(sessionSelect.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(null, "Warning!! Select a Session!!");   
            }else{
        
        Connection con = getConnection();
        Statement statement;
        ResultSet res;

        if(Integer.parseInt(str_hours)>=Integer.parseInt(end_hours)){
           
            JOptionPane.showMessageDialog(null, "Warning!! Times Error!!");
            
        }else{

            String query = "SELECT * from notavailable where (( str_hours<="+str_hours+" and end_hours>"+str_hours+" ) or ( str_hours<"+end_hours+" and end_hours>="+end_hours+" )) and lecturer='"+lecName+"' and day='"+day+"' ";
            try{
                statement = con.createStatement();
                res = statement.executeQuery(query);

                while(res.next()){
                    lec=true;
                }

                if(lec){
                    JOptionPane.showMessageDialog(null, "Warning!! Lecturer have a another session!!");
                }else{

                    String query1 = "SELECT * from notavailable where room='"+room+"' and day='"+day+"' and (( str_hours<="+str_hours+" and end_hours>"+str_hours+" ) or ( str_hours<"+end_hours+" and end_hours>="+end_hours+" ))";

                    res = statement.executeQuery(query1);

                    while(res.next()){
                        a_room=true;
                    }

                    if(a_room){
                        JOptionPane.showMessageDialog(null, "Warning!! Lecture room unavailable!!");
                    }else{

                        String query2 = "SELECT * from notavailable where groupName='"+group+"' and day='"+day+"' and (( str_hours<="+str_hours+" and end_hours>"+str_hours+" ) or ( str_hours<"+end_hours+" and end_hours>="+end_hours+" ))";

                        res = statement.executeQuery(query2);

                        while(res.next()){
                            a_group=true;
                        }

                        if(a_group){
                            JOptionPane.showMessageDialog(null, "Warning!! Group have a another session!!");
                        }else{

                            String query3 = "INSERT INTO `notavailable`( `lecturer`, `room`,`groupName`, `day`, `str_hours`, `str_min`, `end_hours`, `end_min`,`session`) VALUES ('"+lecName+"','"+room+"','"+group+"','"+day+"',"+str_hours+","+str_min+","+end_hours+","+end_min+",'"+sessionSelect.getSelectedItem().toString()+"')";
                            int resl = statement.executeUpdate(query3);

                            if(resl==1){
                                JOptionPane.showMessageDialog(null, "Success!! Record Insert Successful!!");
                            }else{
                                JOptionPane.showMessageDialog(null, "Warning!! Something is wrong!!");
                            }

                        }

                    }

                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        }
        }
        
    }//GEN-LAST:event_btnAddLecActionPerformed
  
    private void lblDayHourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHourMouseClicked

    }//GEN-LAST:event_lblDayHourMouseClicked

    private void lblLecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLecMouseClicked
        // TODO add your handling code here:
        NotAvailable lecMng = new NotAvailable();
        lecMng.setSize(2000, 2000);
        lecMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblLecMouseClicked

    private void lblSubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubMouseClicked
        // TODO add your handling code here:
        SubjectManage subMng = new SubjectManage();
        subMng.setSize(2000, 2000);
        subMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblSubMouseClicked

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

    private void lblYSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYSMouseClicked
        // TODO add your handling code here:
        Add_YearAndSemester addYrSem = new Add_YearAndSemester();
        addYrSem.setSize(2000, 2000);
        addYrSem.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblYSMouseClicked

    private void lblStatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStatMouseClicked
        // TODO add your handling code here:
        StatisticsDashboard statMng = new StatisticsDashboard();
        statMng.setSize(2000, 2000);
        statMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblStatMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void lblViewLecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewLecMouseClicked
        // TODO add your handling code here:
        ViewLecturers viewLec = new ViewLecturers();
        viewLec.setSize(2000, 2000);
        viewLec.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblViewLecMouseClicked

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setSize(2000, 2000);
        home.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

    private void lblConSessionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConSessionMouseClicked
        // TODO add your handling code here:
        Add_Consecutive_sessions addConSession = new Add_Consecutive_sessions();
        addConSession.setSize(2000, 2000);
        addConSession.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblConSessionMouseClicked

    private void lblRoomAllocateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRoomAllocateMouseClicked
        // TODO add your handling code here:
        RoomMngDashboard roomMngDB = new RoomMngDashboard();
        roomMngDB.setSize(2000, 2000);
        roomMngDB.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblRoomAllocateMouseClicked

    private void lblSessionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSessionMouseClicked
        // TODO add your handling code here:
        AddSessions addSessions = new AddSessions();
        addSessions.setSize(2000, 2000);
        addSessions.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblSessionMouseClicked

    private void lblParallelSeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParallelSeMouseClicked
        // TODO add your handling code here:
        Add_Parallel_Sessions addPrSession = new Add_Parallel_Sessions();
        addPrSession.setSize(2000, 2000);
        addPrSession.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblParallelSeMouseClicked

    private void lblBotOverlappingSessionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotOverlappingSessionMouseClicked
        // TODO add your handling code here:

        Add_Not_Overlapping_sessions addNotOvSession = new Add_Not_Overlapping_sessions();
        addNotOvSession.setSize(2000, 2000);
        addNotOvSession.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblBotOverlappingSessionMouseClicked

    private void lblNotAvTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNotAvTimeMouseClicked
        // TODO add your handling code here:
        NotAvailableTimes notAvTimes = new NotAvailableTimes();
        notAvTimes.setSize(2000, 2000);
        notAvTimes.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblNotAvTimeMouseClicked

    private void roomSlctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomSlctActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomSlctActionPerformed

    private void selectDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDayActionPerformed

    private void groupSlctPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_groupSlctPropertyChange
        
        
    }//GEN-LAST:event_groupSlctPropertyChange

    private void groupSlctItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupSlctItemStateChanged
        String group = groupSlct.getSelectedItem().toString();
        
        System.out.println(groupSlct.getSelectedIndex());
        if(groupSlct.getSelectedIndex()==0){
            
        }else{
            Connection con = getConnection();
          Statement statement;
          ResultSet resLec;
          
          int itemCount = sessionSelect.getItemCount();

            for(int i=0;i<itemCount;i++){
                sessionSelect.removeItemAt(0);
             }
            
            sessionSelect.addItem("Select Group");
          
          String getLecturerQuery = "SELECT * from sessions where StudentGroup='"+group+"'";
          try{
              statement = con.createStatement();
              resLec = statement.executeQuery(getLecturerQuery);
              
              while(resLec.next()){
                  sessionSelect.addItem(resLec.getString("Subject")+" "+resLec.getString("Tag"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
        }
    }//GEN-LAST:event_groupSlctItemStateChanged

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
            java.util.logging.Logger.getLogger(NotAvailable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotAvailable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotAvailable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotAvailable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                JFrame.setDefaultLookAndFeelDecorated(true);
                new NotAvailable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLec;
    private javax.swing.JComboBox<String> endhours;
    private javax.swing.JComboBox<String> endmin;
    private javax.swing.JComboBox<String> groupSlct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private java.awt.Label lblBotOverlappingSession;
    private java.awt.Label lblConSession;
    private java.awt.Label lblDayHour;
    private java.awt.Label lblGenTimeTables;
    private java.awt.Label lblHome;
    private java.awt.Label lblLec;
    private java.awt.Label lblLocation;
    private java.awt.Label lblNotAvTime;
    private java.awt.Label lblParallelSe;
    private java.awt.Label lblPrgrm;
    private java.awt.Label lblRoomAllocate;
    private java.awt.Label lblSession;
    private java.awt.Label lblStat;
    private java.awt.Label lblStdGrp;
    private java.awt.Label lblStudents;
    private java.awt.Label lblSub;
    private java.awt.Label lblTags;
    private java.awt.Label lblViewLec;
    private java.awt.Label lblViewTimeTables;
    private java.awt.Label lblYS;
    private javax.swing.JComboBox<String> lctrSelect;
    private javax.swing.JComboBox<String> roomSlct;
    private javax.swing.JComboBox<String> selectDay;
    private javax.swing.JComboBox<String> sessionSelect;
    private javax.swing.JComboBox<String> shours;
    private javax.swing.JComboBox<String> smin;
    // End of variables declaration//GEN-END:variables
}
