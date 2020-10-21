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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class AddSessions extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public AddSessions() {
        initComponents();
        
        //call methods for get details to create session
        getLecturerNames();
        getTags();
        getStudentGroups();
        getSubjects();
        
        //for set session Id text field as not editable.
        txtSessionID.setEditable(false);
        
        //for set max size for frame
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
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
    
    //execute sql queries.
    public void executeSqlQuery(String query, String message){
        
        Connection con = getConnection();
        Statement statement;
        
        try{
            statement = con.createStatement();
            if((statement.executeUpdate(query)) == 1){
                JOptionPane.showMessageDialog(null, message+" successfully.");
            }else{
                 JOptionPane.showMessageDialog(null, "Not "+message); 
            }
        }catch(Exception ex){
                ex.printStackTrace();
        }
    }
    
    //get lecturer names for set dropdown list.
    public void getLecturerNames(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resLec;
          
          String getLecturerQuery = "SELECT LecturerName,Title from lecturers";
          try{
              statement = con.createStatement();
              resLec = statement.executeQuery(getLecturerQuery);
              
              while(resLec.next()){
                  comboSessionLec.addItem(resLec.getString("Title") + "." + resLec.getString("LecturerName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    //get tags for set dropdown list.
    public void getTags(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resTags;
          
          String getTagQuery = "SELECT TagName from tags";
          try{
              statement = con.createStatement();
              resTags = statement.executeQuery(getTagQuery);
              
              while(resTags.next()){
                  comboSessionTag.addItem(resTags.getString("TagName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
//     //get groups for set dropdown list.
    public void getStudentGroups(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resStGrp;
          
          String getStGrpQuery = "SELECT distinct GroupId, SubGroupId from students";
          try{
              statement = con.createStatement();
              resStGrp = statement.executeQuery(getStGrpQuery);
              
              while(resStGrp.next()){
                  comboSessionStGrp.addItem(resStGrp.getString("GroupId"));
                  comboSessionStGrp.addItem(resStGrp.getString("SubGroupId"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    //get subjects for set dropdown list.
    public void getSubjects(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resSubject;
          
          String getSubQuery = "SELECT SubjectName,SubjectCode from subjects";
          try{
              statement = con.createStatement();
              resSubject = statement.executeQuery(getSubQuery);
              while(resSubject.next()){
                  comboSessionSub.addItem(resSubject.getString("SubjectName") + "(" + resSubject.getString("SubjectCode") + ")");
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    //get maxID from SessionLecturerTable for add particular lecturers.
    public int getMaxIDOfLecturerSessionTable(){
         Connection con = getConnection();
         Statement statement;
         
         int maxID = 0;
         
         ResultSet resMaxID;
         String getMaxIDQuery = "SELECT max(SessionId) from sessions";
         
          try{
              statement = con.createStatement();
              resMaxID = statement.executeQuery(getMaxIDQuery);
              resMaxID.next();
              
              maxID = resMaxID.getInt(1);
             
          }catch(Exception e){
              e.printStackTrace();
          }
          
          return maxID;
         
    }
    
    public void refreshFormData(){
        comboSessionTag.setSelectedItem(ABORT);
        txtSessionNoOfSt.setText(null);
        txtSessionDuration.setText(null);
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
        lblLecture = new javax.swing.JLabel();
        lblTag = new javax.swing.JLabel();
        lblStGrp = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        lblNoOfStudents = new javax.swing.JLabel();
        lblSession = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSessionDuration = new java.awt.TextField();
        txtSessionNoOfSt = new java.awt.TextField();
        comboSessionLec = new javax.swing.JComboBox<>();
        comboSessionStGrp = new javax.swing.JComboBox<>();
        comboSessionTag = new javax.swing.JComboBox<>();
        comboSessionSub = new javax.swing.JComboBox<>();
        btnAddSessions = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblLecture1 = new javax.swing.JLabel();
        btnAddSessionsLec = new javax.swing.JButton();
        txtSessionID = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnFilterSession = new javax.swing.JButton();
        btnViewSession = new javax.swing.JButton();
        lblErrorSessionDuration = new javax.swing.JLabel();
        lblErrorNoOfSt = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
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
        lblDayHour1 = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        lblConSession = new java.awt.Label();
        lblRoomAllocate = new java.awt.Label();
        lblSession2 = new java.awt.Label();
        lblParallelSe = new java.awt.Label();
        lblBotOverlappingSession = new java.awt.Label();
        lblNotAvTime = new java.awt.Label();
        lblGenTimeTables = new java.awt.Label();

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(613, 613, 613)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLecture.setText("Lecturer");

        lblTag.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTag.setText("Tag");

        lblStGrp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblStGrp.setText("Students' group/sub group");

        lblSubject.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSubject.setText("Subjects");

        lblNoOfStudents.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNoOfStudents.setText("No of Students of Session");

        lblSession.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSession.setText("Session Duration(Hours)");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("ALLOCATE LECTURERS FOR THIS NEW SESSION");

        txtSessionDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSessionDurationActionPerformed(evt);
            }
        });
        txtSessionDuration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSessionDurationKeyPressed(evt);
            }
        });

        txtSessionNoOfSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSessionNoOfStActionPerformed(evt);
            }
        });
        txtSessionNoOfSt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSessionNoOfStKeyPressed(evt);
            }
        });

        comboSessionStGrp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSessionStGrpActionPerformed(evt);
            }
        });

        comboSessionTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSessionTagActionPerformed(evt);
            }
        });

        comboSessionSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSessionSubActionPerformed(evt);
            }
        });

        btnAddSessions.setBackground(new java.awt.Color(0, 51, 204));
        btnAddSessions.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddSessions.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSessions.setText("ADD");
        btnAddSessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSessionsActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("ADD SESSIONS");

        lblLecture1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLecture1.setText("Session ID");

        btnAddSessionsLec.setBackground(new java.awt.Color(0, 51, 204));
        btnAddSessionsLec.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddSessionsLec.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSessionsLec.setText("ADD");
        btnAddSessionsLec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSessionsLecActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 0, 153));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(613, 613, 613)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

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

        btnFilterSession.setBackground(new java.awt.Color(0, 102, 51));
        btnFilterSession.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFilterSession.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterSession.setText("SEARCH SESSIONS");
        btnFilterSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterSessionActionPerformed(evt);
            }
        });

        btnViewSession.setBackground(new java.awt.Color(0, 102, 51));
        btnViewSession.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnViewSession.setForeground(new java.awt.Color(255, 255, 255));
        btnViewSession.setText("VIEW ALL SESSIONS");
        btnViewSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewSessionActionPerformed(evt);
            }
        });

        lblErrorSessionDuration.setForeground(new java.awt.Color(255, 51, 0));
        lblErrorSessionDuration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblErrorSessionDurationKeyPressed(evt);
            }
        });

        lblErrorNoOfSt.setForeground(new java.awt.Color(255, 51, 0));

        jPanel2.setBackground(new java.awt.Color(100, 149, 237));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
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
        lblYS.setText("Years & Semesters ");
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

        lblDayHour1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDayHour1.setText("Working Days & Hours |");
        lblDayHour1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDayHour1MouseClicked(evt);
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
                .addComponent(lblDayHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(24, 24, 24)
                .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(lblPrgrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblStdGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDayHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStdGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrgrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblYS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

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

        lblSession2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSession2.setForeground(new java.awt.Color(0, 0, 102));
        lblSession2.setText("SESSIONS");
        lblSession2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSession2MouseClicked(evt);
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
        lblGenTimeTables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGenTimeTablesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSession2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGenTimeTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotAvTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBotOverlappingSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblParallelSe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSession2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRoomAllocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConSession, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(196, 196, 196))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFilterSession, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(layout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(btnAddSessions, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTag)
                    .addComponent(lblSubject)
                    .addComponent(lblStGrp)
                    .addComponent(lblNoOfStudents)
                    .addComponent(lblSession))
                .addGap(159, 159, 159)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboSessionTag, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboSessionStGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(282, 282, 282)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLecture1)
                                    .addComponent(lblLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboSessionLec, 0, 250, Short.MAX_VALUE)
                                    .addComponent(txtSessionID)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboSessionSub, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddSessionsLec, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(198, 198, 198))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSessionDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblErrorNoOfSt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSessionNoOfSt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(139, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblErrorSessionDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(1196, Short.MAX_VALUE)
                    .addComponent(btnViewSession, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(357, 357, 357)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTag, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSessionTag, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLecture1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSessionID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSessionStGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSessionLec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(lblSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboSessionSub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddSessionsLec, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNoOfStudents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSessionNoOfSt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErrorNoOfSt)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSessionDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSession))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErrorSessionDuration)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnAddSessions, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnFilterSession, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(829, Short.MAX_VALUE)
                    .addComponent(btnViewSession, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(105, 105, 105)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnAddSessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSessionsActionPerformed

        String tag = comboSessionTag.getSelectedItem().toString();
        String stGroup = comboSessionStGrp.getSelectedItem().toString();
        String subject = comboSessionSub.getSelectedItem().toString();
        int noOfStudents = Integer.parseInt(txtSessionNoOfSt.getText());
        int sessionDuration = Integer.parseInt(txtSessionDuration.getText());
        

        if((Integer)sessionDuration == null){
            System.out.println("Action2");
            JOptionPane.showMessageDialog(null, "Warning!! Session Duration is required!!");   
        }else if((Integer)noOfStudents == null){
           JOptionPane.showMessageDialog(null, "Warning!! No of Students per perticular session is required!!");   
       }else{
    
            String addQuery = "INSERT INTO sessions(Tag, StudentGroup, Subject, NoOfStudents, SessionDuration) VALUES ('"+tag+"', '"+stGroup+"', '"+subject+"', '"+noOfStudents+"', '"+sessionDuration+"')";

            executeSqlQuery(addQuery, "Session Details Inserted");
            
            refreshFormData();
            //get maxID from getMaxIDOfLecturerSessionTable() method
            int maxIDOfSessionLecturerTable = getMaxIDOfLecturerSessionTable();
            
            //set max id of text field to allocate lecturers of particular session
            txtSessionID.setText(String.valueOf(maxIDOfSessionLecturerTable));
            

            
        }
         

    }//GEN-LAST:event_btnAddSessionsActionPerformed

    private void comboSessionSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSessionSubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSessionSubActionPerformed

    private void txtSessionNoOfStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSessionNoOfStActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSessionNoOfStActionPerformed

    private void comboSessionTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSessionTagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSessionTagActionPerformed

    private void txtSessionDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSessionDurationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSessionDurationActionPerformed

    private void comboSessionStGrpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSessionStGrpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSessionStGrpActionPerformed

    private void btnAddSessionsLecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSessionsLecActionPerformed
        // TODO add your handling code here:
         
         int sessionId = Integer.parseInt(txtSessionID.getText());
         String lecName = comboSessionLec.getSelectedItem().toString();
         
         int noOfLecturers = 0;
         
         //for check the lecturer uniqueness of particular session
         Connection connection = getConnection();
         String querySubCode = "SELECT COUNT(LecturerName) from sessionlecturers WHERE SessionId = '"+sessionId+"' && LecturerName = '"+lecName+"'";

         Statement statement;
         ResultSet res;
         
         try{
                statement = connection.createStatement();
                res = statement.executeQuery(querySubCode);
                res.next();
                
                noOfLecturers = res.getInt(1);
                
         }catch(Exception e){
                e.printStackTrace();
         }
         
         //check whether lecturer is already allocated or not for particular seesion
         if(noOfLecturers>0){
                 JOptionPane.showMessageDialog(null, lecName+ " Lecturer is already Allocated for this session ???");   
         }else{
         
            String addQuery = "INSERT INTO sessionlecturers(SessionId, LecturerName) VALUES ('"+sessionId+"', '"+lecName+"')";

            executeSqlQuery(addQuery, "Lecturer for this Session is Allocated ");
         }
    }//GEN-LAST:event_btnAddSessionsLecActionPerformed

    private void btnViewSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewSessionActionPerformed
        // TODO add your handling code here:
        
        AllSessions allSessions = new AllSessions();
        allSessions.setSize(2000, 2000);
        allSessions.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnViewSessionActionPerformed

    private void btnFilterSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterSessionActionPerformed
        // TODO add your handling code here:
        FilterSessions filterSessions = new FilterSessions();
        filterSessions.setSize(2000, 2000);
        filterSessions.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnFilterSessionActionPerformed

    private void txtSessionNoOfStKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSessionNoOfStKeyPressed
        // TODO add your handling code here:

        //validate text field
        char value = evt.getKeyChar();
        if(Character.isLetter(value)){
            //can't able to enter in char values to text field
            txtSessionNoOfSt.setEditable(false);
            lblErrorNoOfSt.setText("**Please only enter numbers**");
        }else{
            txtSessionNoOfSt.setEditable(true);
            lblErrorNoOfSt.setText("");
        }
        
    }//GEN-LAST:event_txtSessionNoOfStKeyPressed

    private void lblErrorSessionDurationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblErrorSessionDurationKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblErrorSessionDurationKeyPressed

    private void txtSessionDurationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSessionDurationKeyPressed
        // TODO add your handling code here:
        
        //validate text filed
            char value = evt.getKeyChar();
            if(Character.isLetter(value)){
                //can't able to enter in char values to text field
                txtSessionDuration.setEditable(false);
                lblErrorSessionDuration.setText("**Please only enter numbers**");
            }else{
                txtSessionDuration.setEditable(true);
                lblErrorSessionDuration.setText("");
            }  
    }//GEN-LAST:event_txtSessionDurationKeyPressed

    private void lblLecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLecMouseClicked
        // TODO add your handling code here:
        LecturerManage lecMng = new LecturerManage();
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

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setSize(2000, 2000);
        home.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

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

    private void lblDayHour1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHour1MouseClicked

        Workingdaysandhours wdh = new Workingdaysandhours();
        wdh.setSize(2000, 2000);
        wdh.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblDayHour1MouseClicked

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

    private void lblSession2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSession2MouseClicked
        // TODO add your handling code here:
        AddSessions addSessions = new AddSessions();
        addSessions.setSize(2000, 2000);
        addSessions.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblSession2MouseClicked

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

    private void lblGenTimeTablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenTimeTablesMouseClicked
        // TODO add your handling code here:

        generateTimetable gt = new generateTimetable();
        gt.setSize(2000, 2000);
        gt.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblGenTimeTablesMouseClicked

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
            java.util.logging.Logger.getLogger(AddSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new AddSessions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSessions;
    private javax.swing.JButton btnAddSessionsLec;
    private javax.swing.JButton btnFilterSession;
    private javax.swing.JButton btnViewSession;
    private javax.swing.JComboBox<String> comboSessionLec;
    private javax.swing.JComboBox<String> comboSessionStGrp;
    private javax.swing.JComboBox<String> comboSessionSub;
    private javax.swing.JComboBox<String> comboSessionTag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private java.awt.Label lblBotOverlappingSession;
    private java.awt.Label lblConSession;
    private java.awt.Label lblDayHour1;
    private javax.swing.JLabel lblErrorNoOfSt;
    private javax.swing.JLabel lblErrorSessionDuration;
    private java.awt.Label lblGenTimeTables;
    private java.awt.Label lblHome;
    private java.awt.Label lblLec;
    private javax.swing.JLabel lblLecture;
    private javax.swing.JLabel lblLecture1;
    private java.awt.Label lblLocation;
    private javax.swing.JLabel lblNoOfStudents;
    private java.awt.Label lblNotAvTime;
    private java.awt.Label lblParallelSe;
    private java.awt.Label lblPrgrm;
    private java.awt.Label lblRoomAllocate;
    private javax.swing.JLabel lblSession;
    private java.awt.Label lblSession2;
    private javax.swing.JLabel lblStGrp;
    private java.awt.Label lblStat;
    private java.awt.Label lblStdGrp;
    private java.awt.Label lblStudents;
    private java.awt.Label lblSub;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTag;
    private java.awt.Label lblTags;
    private java.awt.Label lblYS;
    private java.awt.TextField txtSessionDuration;
    private javax.swing.JTextField txtSessionID;
    private java.awt.TextField txtSessionNoOfSt;
    // End of variables declaration//GEN-END:variables
}
