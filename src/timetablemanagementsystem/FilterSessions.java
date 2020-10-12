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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author acer
 */
public class FilterSessions extends javax.swing.JFrame {

    /**
     * Creates new form FilterSessions
     */
    
    int selectedSessionId  = 0; //define variable for store a selected sessionid from table
    
    public FilterSessions() {
        initComponents();
        
        hideDltBtn(); //for hide session delete button initialy
     
        getLecturerNamesForFilterSession();
        getTagsForFilterSession();
        getStudentGroupsForFilterSession();
        getSubjectsForFilterSession();
        
        //resize table column size
        tblSearchedSessions.setAutoResizeMode(tblSearchedSessions.AUTO_RESIZE_OFF);
        TableColumn col = tblSearchedSessions.getColumnModel().getColumn(0);
        col.setPreferredWidth(100);
        col = tblSearchedSessions.getColumnModel().getColumn(1);
        col.setPreferredWidth(100);
        col = tblSearchedSessions.getColumnModel().getColumn(2);
        col.setPreferredWidth(200);
        col = tblSearchedSessions.getColumnModel().getColumn(3);
        col.setPreferredWidth(200);
        col = tblSearchedSessions.getColumnModel().getColumn(4);
        col.setPreferredWidth(100);
        col = tblSearchedSessions.getColumnModel().getColumn(5);
        col.setPreferredWidth(130);
        col = tblSearchedSessions.getColumnModel().getColumn(6);
        col.setPreferredWidth(200);
    
        
        tblSearchedSessions.setRowHeight(40);
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
                
                //refresh jtable data
                DefaultTableModel model = (DefaultTableModel)tblSearchedSessions.getModel();
                model.setRowCount(0);
                //DisplaySessions();
                
                JOptionPane.showMessageDialog(null, message+" successfully");
            }else{
                 //JOptionPane.showMessageDialog(null, "Not "+message); 
            }
        }catch(Exception ex){
                ex.printStackTrace();
        }
    }
 
    //get lecturer names for set dropdown list.
    public void getLecturerNamesForFilterSession(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resLec;
          
          String getLecturerQuery = "SELECT LecturerName,Title from lecturers";
          try{
              statement = con.createStatement();
              resLec = statement.executeQuery(getLecturerQuery);
              
              while(resLec.next()){
                  comboFilterLec.addItem(resLec.getString("Title") + "." + resLec.getString("LecturerName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    //get tags for set dropdown list.
    public void getTagsForFilterSession(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resTags;
          
          String getTagQuery = "SELECT TagName from tags";
          try{
              statement = con.createStatement();
              resTags = statement.executeQuery(getTagQuery);
              
              while(resTags.next()){
                  comboFilterTag.addItem(resTags.getString("TagName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
//     //get groups for set dropdown list.
    public void getStudentGroupsForFilterSession(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resStGrp;
          
          String getStGrpQuery = "SELECT distinct GroupId, SubGroupId from students";
          try{
              statement = con.createStatement();
              resStGrp = statement.executeQuery(getStGrpQuery);
              
              while(resStGrp.next()){
                  comboFilterGrp.addItem(resStGrp.getString("GroupId"));
                  comboFilterGrp.addItem(resStGrp.getString("SubGroupId"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    //get subjects for set dropdown list.
    public void getSubjectsForFilterSession(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resSubject;
          
          String getSubQuery = "SELECT SubjectName,SubjectCode from subjects";
          try{
              statement = con.createStatement();
              resSubject = statement.executeQuery(getSubQuery);
              
              while(resSubject.next()){
                  comboFilterSub.addItem(resSubject.getString("SubjectName") + "(" + resSubject.getString("SubjectCode") + ")");
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    //for get searched result according to the tags, student groups and subjects
    public ArrayList<Session> getSearchedResult(String searchItem){
        
        ArrayList<Session> sessionList = new ArrayList<Session>();
        
        Connection connection = getConnection();
        
        Statement statement;
        ResultSet result;
        
        String getQuery = "SELECT * from sessions where Tag = '"+searchItem+"' or StudentGroup = '"+searchItem+"' "
                + "or Subject = '"+searchItem+"' ";
        
//         String getQuery = "SELECT * from sessions s, sessionlecturers sl where s.Tag = '"+searchItem+"' or s.StudentGroup = '"+searchItem+"' "
//                + "or s.Subject = '"+searchItem+"' or (sl.LecturerName = '"+searchItem+"' and s.SessionId = sl.SessionId)";

        try{
             statement = connection.createStatement();
             result = statement.executeQuery(getQuery);
             
             Session session;
             
             if(result.next() == false){   //check whether search result is empty or not
                  lblWarningMsg.setText("Searched Results Not Found in System");
                  
             }else{
                 
                 do{
                              //        session = new Session(res.getInt("SessionId"), res.getString("Tag"), res.getString("StudentGroup"),
                              //        res.getString("Subject"), res.getInt("NoOfStudents"), res.getInt("SessionDuration"), (Lecturer) res.getArray("LecturerName"));
                              
                     session = new Session(result.getInt("SessionId"), result.getString("Tag"), result.getString("StudentGroup"),
                                           result.getString("Subject"), result.getInt("NoOfStudents"), result.getInt("SessionDuration"));

                     sessionList.add(session);
                     
                 }while(result.next());
             }
             
//             while(result.next()){
//   //                 session = new Session(res.getInt("SessionId"), res.getString("Tag"), res.getString("StudentGroup"),
//   //                                        res.getString("Subject"), res.getInt("NoOfStudents"), res.getInt("SessionDuration"), (Lecturer) res.getArray("LecturerName"));
//
//                session = new Session(result.getInt("SessionId"), result.getString("Tag"), result.getString("StudentGroup"),
//                                           result.getString("Subject"), result.getInt("NoOfStudents"), result.getInt("SessionDuration"));
//
//                sessionList.add(session);
//
//             }
             
         }catch(Exception e){
             e.printStackTrace();
         }
         return sessionList;
    }
    
    //for display searched result in jtable according to the tags, student groups and subjects.
    public void displaySearchedResult(String searchItem){
        
        ArrayList<Session> sessionList = getSearchedResult(searchItem);
        
        //create a new table model
        DefaultTableModel model = (DefaultTableModel)tblSearchedSessions.getModel();
        model.setRowCount(0);
        
        Object[] row = new Object[6];
        for(int i = 0; i < sessionList.size(); i++){
            row[0] = sessionList.get(i).getSessionID();
            row[1] = sessionList.get(i).getTag();
            row[2] = sessionList.get(i).getStGrp();
            row[3] = sessionList.get(i).getSubject();
            row[4] = sessionList.get(i).getNoOfStudents();
            row[5] = sessionList.get(i).getSessionDuration();
           // row[6] = sessionList.get(i).getLecNames();
            model.addRow(row);  
        }
        
    }
    
    // for search session details accoring to lecturers
    public ArrayList<Session> getSearchedResultAccordingToLecturers(String lecName){
        
        ArrayList<Session> sessionList = new ArrayList<Session>();
        
        Connection connection = getConnection();
        
        Statement statement;
        ResultSet result;
        
        String getQuery = "Select * from sessions s, sessionLecturers sl where sl.Lecturername = '"+lecName+"' and sl.sessionId = s.SessionId ";
        
        try{
             statement = connection.createStatement();
             result = statement.executeQuery(getQuery);
             
             Session session;
             
             if(result.next() == false){  //check whether search result is empty or not
                   lblWarningMsg.setText("Searched Results Not Found in System");
             }else{
                 
                 do{
                     
                     session = new Session(result.getInt("SessionId"), result.getString("Tag"), result.getString("StudentGroup"),
                                           result.getString("Subject"), result.getInt("NoOfStudents"), result.getInt("SessionDuration"), result.getString("LecturerName"));

//                      session = new Session(result.getInt("SessionId"), result.getString("Tag"), result.getString("StudentGroup"),
//                                           result.getString("Subject"), result.getInt("NoOfStudents"), result.getInt("SessionDuration"));

                    sessionList.add(session);

                 } while(result.next());
             }
             
//             while(result.next()){
//                    session = new Session(result.getInt("SessionId"), result.getString("Tag"), result.getString("StudentGroup"),
//                                           result.getString("Subject"), result.getInt("NoOfStudents"), result.getInt("SessionDuration"), result.getString("LecturerName"));
//
////                session = new Session(result.getInt("SessionId"), result.getString("Tag"), result.getString("StudentGroup"),
////                                           result.getString("Subject"), result.getInt("NoOfStudents"), result.getInt("SessionDuration"));
//
//                sessionList.add(session);
//
//             }
             
         }catch(Exception e){
             e.printStackTrace();
         }
         return sessionList;
    }
    
    // for display search Result according to lecturers
    public void displaySearchedResultAccordingToLecturer(String lecName){
        
         ArrayList<Session> sessionList = getSearchedResultAccordingToLecturers(lecName);
         
          //create a new table model
        DefaultTableModel model = (DefaultTableModel)tblSearchedSessions.getModel();
        model.setRowCount(0);
        
        Object[] row = new Object[7];
        for(int i = 0; i < sessionList.size(); i++){
            row[0] = sessionList.get(i).getSessionID();
            row[1] = sessionList.get(i).getTag();
            row[2] = sessionList.get(i).getStGrp();
            row[3] = sessionList.get(i).getSubject();
            row[4] = sessionList.get(i).getNoOfStudents();
            row[5] = sessionList.get(i).getSessionDuration();
            row[6] = sessionList.get(i).getLecName();
            model.addRow(row);  
        }
    }

    //for refresh panel data
    public void refreshPanelData(){
        
        lblFilteredSessionSub.setText("Subject of Session");
        lblFilteredSessionTag.setText("Session tag");
        lblFilteredSessionStGrp.setText("Student group/Sub group");
        lblFilteredNoOfStAndDuration.setText("#students & Duration");
        lblFilteredSessionLec.setText("Lecturers for Particular session");
    }
    
    //for hide warning msg when not needed.
    public void hideWarningMsg(){
        lblWarningMsg.setText(null); 
    }
    
    //for hide delete button if not need
    public void hideDltBtn(){
        btnDltFilteredSession.setVisible(false);
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
        lblDayHour3 = new java.awt.Label();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblLecture = new javax.swing.JLabel();
        lblTag = new javax.swing.JLabel();
        lblStGrp = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        comboFilterLec = new javax.swing.JComboBox<>();
        comboFilterTag = new javax.swing.JComboBox<>();
        comboFilterGrp = new javax.swing.JComboBox<>();
        comboFilterSub = new javax.swing.JComboBox<>();
        btnFilterTag = new javax.swing.JButton();
        btnFilterLec = new javax.swing.JButton();
        btnFilterGrp = new javax.swing.JButton();
        btnFilterSub = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearchedSessions = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        lblFilteredSessionLec = new java.awt.Label();
        lblFilteredSessionSub = new java.awt.Label();
        lblFilteredSessionTag = new java.awt.Label();
        lblFilteredSessionStGrp = new java.awt.Label();
        lblFilteredNoOfStAndDuration = new java.awt.Label();
        label1 = new java.awt.Label();
        btnDltFilteredSession = new javax.swing.JButton();
        lblWarningMsg = new javax.swing.JLabel();
        btnSearchAll = new javax.swing.JButton();
        btnViewSession = new javax.swing.JButton();
        btnFilterSession = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblConSession = new java.awt.Label();
        lblRoomAllocate = new java.awt.Label();
        lblSession = new java.awt.Label();
        lblParallelSe = new java.awt.Label();
        lblBotOverlappingSession = new java.awt.Label();
        lblNotAvTime = new java.awt.Label();
        lblGenTimeTables = new java.awt.Label();
        lblViewTimeTables = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        lblYS.setText("Years & Semesters");
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

        lblDayHour3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDayHour3.setText("Working Days & Hours |");
        lblDayHour3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDayHour3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblDayHour3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(458, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDayHour3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("SEARCH SESSIONS");

        lblLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLecture.setText("Lecturer");

        lblTag.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTag.setText("Tag");

        lblStGrp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblStGrp.setText("Students' group/sub group");

        lblSubject.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSubject.setText("Subjects");

        comboFilterLec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFilterLecActionPerformed(evt);
            }
        });

        btnFilterTag.setBackground(new java.awt.Color(0, 102, 102));
        btnFilterTag.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFilterTag.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterTag.setText("SEARCH SESSIONS");
        btnFilterTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterTagActionPerformed(evt);
            }
        });

        btnFilterLec.setBackground(new java.awt.Color(0, 102, 102));
        btnFilterLec.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFilterLec.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterLec.setText("SEARCH SESSIONS");
        btnFilterLec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterLecActionPerformed(evt);
            }
        });

        btnFilterGrp.setBackground(new java.awt.Color(0, 102, 102));
        btnFilterGrp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFilterGrp.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterGrp.setText("SEARCH SESSION");
        btnFilterGrp.setActionCommand("SEARCH SESSIONS");
        btnFilterGrp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterGrpActionPerformed(evt);
            }
        });

        btnFilterSub.setBackground(new java.awt.Color(0, 102, 102));
        btnFilterSub.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFilterSub.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterSub.setText("SEARCH SESSION");
        btnFilterSub.setActionCommand("SEARCH SESSIONS");
        btnFilterSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterSubActionPerformed(evt);
            }
        });

        tblSearchedSessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SessionId", "Tag", "StudentGroup", "Subject", "No of Students", "Session Duration", "Lecturer"
            }
        ));
        tblSearchedSessions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSearchedSessionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSearchedSessions);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("SEARCHED RESULTS");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("SELECTED SESSION");

        panel1.setBackground(new java.awt.Color(153, 153, 255));
        panel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblFilteredSessionLec.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFilteredSessionLec.setText("Lecturers for Particular session");

        lblFilteredSessionSub.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFilteredSessionSub.setText("Subject of Session");

        lblFilteredSessionTag.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFilteredSessionTag.setText("Session tag");

        lblFilteredSessionStGrp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFilteredSessionStGrp.setText("Student group/Sub group");

        lblFilteredNoOfStAndDuration.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFilteredNoOfStAndDuration.setText("#students & Duration");

        label1.setText("label1");

        btnDltFilteredSession.setBackground(new java.awt.Color(255, 51, 51));
        btnDltFilteredSession.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDltFilteredSession.setForeground(new java.awt.Color(255, 255, 255));
        btnDltFilteredSession.setText("DELETE SESSION");
        btnDltFilteredSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDltFilteredSessionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFilteredSessionLec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFilteredSessionSub, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFilteredSessionTag, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFilteredSessionStGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 288, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(lblFilteredNoOfStAndDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDltFilteredSession)
                        .addGap(38, 38, 38))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFilteredSessionLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(lblFilteredSessionSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblFilteredSessionTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFilteredSessionStGrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFilteredNoOfStAndDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDltFilteredSession, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        lblWarningMsg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblWarningMsg.setForeground(new java.awt.Color(255, 0, 51));

        btnSearchAll.setBackground(new java.awt.Color(0, 153, 102));
        btnSearchAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearchAll.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchAll.setText("SEARCH SESSIONS FOR ALL");
        btnSearchAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAllActionPerformed(evt);
            }
        });

        btnViewSession.setBackground(new java.awt.Color(0, 102, 51));
        btnViewSession.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnViewSession.setForeground(new java.awt.Color(255, 255, 255));
        btnViewSession.setText("CREATE SESSIONS");
        btnViewSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewSessionActionPerformed(evt);
            }
        });

        btnFilterSession.setBackground(new java.awt.Color(0, 102, 51));
        btnFilterSession.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFilterSession.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterSession.setText("VIEW ALL SESSIONS");
        btnFilterSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterSessionActionPerformed(evt);
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
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTag)
                                    .addComponent(lblLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboFilterTag, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnFilterTag))
                                    .addComponent(lblSubject)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(comboFilterGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboFilterLec, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblStGrp)
                                            .addComponent(comboFilterSub, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnFilterLec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnFilterGrp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnFilterSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(btnSearchAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(jLabel2)))
                        .addGap(132, 132, 132)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblWarningMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel4)
                            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFilterSession, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewSession, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(26, 26, 26)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWarningMsg))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblTag, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboFilterTag, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilterTag, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(lblLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboFilterLec, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilterLec, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(lblStGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboFilterGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilterGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(lblSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboFilterSub, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilterSub, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(btnSearchAll, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnViewSession, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnFilterSession, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void comboFilterLecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFilterLecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboFilterLecActionPerformed

    private void btnFilterTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterTagActionPerformed
        // TODO add your handling code here:
        
        refreshPanelData(); //for refresh panel data.
        hideWarningMsg();  //for hide warning msg when not needed
        
        String tag = comboFilterTag.getSelectedItem().toString();
        displaySearchedResult(tag);
      
    }//GEN-LAST:event_btnFilterTagActionPerformed

    private void btnFilterLecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterLecActionPerformed
        // TODO add your handling code here:
        
        refreshPanelData(); //for refresh panel data.
        hideWarningMsg();  //for hide warning msg when not needed
        hideDltBtn(); //for hide session delete button initialy
         
        String lecName = comboFilterLec.getSelectedItem().toString();
        displaySearchedResultAccordingToLecturer(lecName);
    }//GEN-LAST:event_btnFilterLecActionPerformed

    private void btnFilterGrpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterGrpActionPerformed
        // TODO add your handling code here:
        
        refreshPanelData(); //for refresh panel data.
        hideWarningMsg(); //for hide warning msg when not needed
        hideDltBtn(); //for hide session delete button initialy
        
        String stGrp = comboFilterGrp.getSelectedItem().toString();
        displaySearchedResult(stGrp);

    }//GEN-LAST:event_btnFilterGrpActionPerformed

    private void btnFilterSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterSubActionPerformed
        // TODO add your handling code here:
        
        refreshPanelData(); //for refresh panel data.
        hideWarningMsg();   //for hide warning msg when not needed
        hideDltBtn(); //for hide session delete button initialy
        
        String subject = comboFilterSub.getSelectedItem().toString();
        displaySearchedResult(subject);
    }//GEN-LAST:event_btnFilterSubActionPerformed

    private void tblSearchedSessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchedSessionsMouseClicked
        // TODO add your handling code here:
        
        //Display selected row in jPanel Labels.
        int i = tblSearchedSessions.getSelectedRow();
        TableModel model = tblSearchedSessions.getModel();
        
        lblFilteredSessionSub.setText(model.getValueAt(i, 3).toString());
        lblFilteredSessionTag.setText(model.getValueAt(i, 1).toString());
        lblFilteredSessionStGrp.setText(model.getValueAt(i, 2).toString());
        lblFilteredNoOfStAndDuration.setText(model.getValueAt(i, 4).toString() + "(" + model.getValueAt(i, 5).toString() + ")");
        
        selectedSessionId = (Integer) model.getValueAt(i, 0);
        
        //get lecturers' name for particular session from table and display in a panel.
        AllSessions allSessions = new AllSessions();
        ArrayList<Lecturer> lecNameList = allSessions.getLecturersFromParticularSession(selectedSessionId);
        
          
          if(lecNameList.size() == 1){
              lblFilteredSessionLec.setText(lecNameList.get(0).getLecName());   
          }else if(lecNameList.size() == 2){
              lblFilteredSessionLec.setText(lecNameList.get(0).getLecName() + ", " + lecNameList.get(1).getLecName());
          }else if(lecNameList.size() == 3){
             // System.out.println(lecNameList.size());
               lblFilteredSessionLec.setText(lecNameList.get(0).getLecName() + ", " + lecNameList.get(1).getLecName()
                                        + ", " + lecNameList.get(2).getLecName());
          }else if(lecNameList.size() == 4){
              lblFilteredSessionLec.setText(lecNameList.get(0).getLecName() + ", " + lecNameList.get(1).getLecName()
                                        + ", " + lecNameList.get(2).getLecName() + ", " + lecNameList.get(3).getLecName());
          }else if(lecNameList.size() == 5){
              lblFilteredSessionLec.setText(lecNameList.get(0).getLecName() + ", " + lecNameList.get(1).getLecName()
                                        + ", " + lecNameList.get(2).getLecName() + ", " + lecNameList.get(3).getLecName()
                                        + ", " + lecNameList.get(4).getLecName());
          }else if(lecNameList.size() == 6){
              lblFilteredSessionLec.setText(lecNameList.get(0).getLecName() + ", " + lecNameList.get(1).getLecName()
                                        + ", " + lecNameList.get(2).getLecName() + ", " + lecNameList.get(3).getLecName()
                                        + ", " + lecNameList.get(4).getLecName() + ", " + lecNameList.get(5).getLecName());
          }
          
          btnDltFilteredSession.setVisible(true); //for visible session delete button when filter a session.
    }//GEN-LAST:event_tblSearchedSessionsMouseClicked

    private void btnSearchAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAllActionPerformed
        // TODO add your handling code here:
  
        refreshPanelData(); //for refresh panel data.
        hideWarningMsg(); //for hide warning msg when not needed
        hideDltBtn(); //for hide session delete button initialy
        
        String tag = comboFilterTag.getSelectedItem().toString();
        String lecName = comboFilterLec.getSelectedItem().toString();
        String stGrp = comboFilterGrp.getSelectedItem().toString();
        String subject = comboFilterSub.getSelectedItem().toString();
        
        Connection con = getConnection();
        Statement statement;
        ResultSet resSession;
        
        String getQuery = "Select * from sessions s, sessionlecturers sl where s.Tag = '"+tag+"' and s.StudentGroup = '"+stGrp+"' and s.Subject = '"+subject+"' "
                + "and sl.LecturerName = '"+lecName+"' and s.SessionId = sl.SessionId ";
        
         try{
              statement = con.createStatement();
              resSession = statement.executeQuery(getQuery);
              
               //create a new table model
              DefaultTableModel model = (DefaultTableModel)tblSearchedSessions.getModel();
              model.setRowCount(0);
              
              Object[] row = new Object[7];
              
              if(resSession.next() == false){  //check whether search result is empty or not
                   lblWarningMsg.setText("Searched Results Not Found in System");
              }else{
                  
                  do{
                      
                    row[0] =  resSession.getInt("SessionId");
                    row[1] =  resSession.getString("Tag");
                    row[2] =  resSession.getString("StudentGroup");
                    row[3] =  resSession.getString("Subject");
                    row[4] =  resSession.getInt("NoOfStudents");
                    row[5] =  resSession.getInt("SessionDuration");
                    row[6] =  resSession.getString("LecturerName");

                    model.addRow(row);
                      
                  }  while(resSession.next());
              }
              
//              while(resSession.next()){
//                  
//                  row[0] =  resSession.getInt("SessionId");
//                  row[1] =  resSession.getString("Tag");
//                  row[2] =  resSession.getString("StudentGroup");
//                  row[3] =  resSession.getString("Subject");
//                  row[4] =  resSession.getInt("NoOfStudents");
//                  row[5] =  resSession.getInt("SessionDuration");
//                  row[6] =  resSession.getString("LecturerName");
//                  
//                  model.addRow(row);
//              }
              
          }catch(Exception e){
              e.printStackTrace();
          }
    }//GEN-LAST:event_btnSearchAllActionPerformed

    private void btnViewSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewSessionActionPerformed
        // TODO add your handling code here:

        AddSessions addSessions = new AddSessions();
        addSessions.setSize(2000, 2000);
        addSessions.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnViewSessionActionPerformed

    private void btnFilterSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterSessionActionPerformed
        // TODO add your handling code here:
        AllSessions allSessions = new AllSessions();
        allSessions.setSize(2000, 2000);
        allSessions.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnFilterSessionActionPerformed

    private void btnDltFilteredSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDltFilteredSessionActionPerformed
        // TODO add your handling code here:
        String dltQueryOfSessionLecTbl = "DELETE FROM sessionlecturers  WHERE SessionId = '"+selectedSessionId+"'";
        executeSqlQuery(dltQueryOfSessionLecTbl, "Session lecturers Deleted");

        String dltQueryOfSessionTbl = "DELETE FROM sessions  WHERE SessionId = '"+selectedSessionId+"'";
        executeSqlQuery(dltQueryOfSessionTbl, "Session details Deleted");
        
        refreshPanelData();
        hideDltBtn();
    }//GEN-LAST:event_btnDltFilteredSessionActionPerformed

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

    private void lblDayHour3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHour3MouseClicked
        // TODO add your handling code here:
        
        Workingdaysandhours wdh = new Workingdaysandhours();
        wdh.setSize(2000, 2000);
        wdh.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblDayHour3MouseClicked

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
            java.util.logging.Logger.getLogger(FilterSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilterSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilterSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilterSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new FilterSessions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDltFilteredSession;
    private javax.swing.JButton btnFilterGrp;
    private javax.swing.JButton btnFilterLec;
    private javax.swing.JButton btnFilterSession;
    private javax.swing.JButton btnFilterSub;
    private javax.swing.JButton btnFilterTag;
    private javax.swing.JButton btnSearchAll;
    private javax.swing.JButton btnViewSession;
    private javax.swing.JComboBox<String> comboFilterGrp;
    private javax.swing.JComboBox<String> comboFilterLec;
    private javax.swing.JComboBox<String> comboFilterSub;
    private javax.swing.JComboBox<String> comboFilterTag;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label lblBotOverlappingSession;
    private java.awt.Label lblConSession;
    private java.awt.Label lblDayHour3;
    private java.awt.Label lblFilteredNoOfStAndDuration;
    private java.awt.Label lblFilteredSessionLec;
    private java.awt.Label lblFilteredSessionStGrp;
    private java.awt.Label lblFilteredSessionSub;
    private java.awt.Label lblFilteredSessionTag;
    private java.awt.Label lblGenTimeTables;
    private java.awt.Label lblHome;
    private java.awt.Label lblLec;
    private javax.swing.JLabel lblLecture;
    private java.awt.Label lblLocation;
    private java.awt.Label lblNotAvTime;
    private java.awt.Label lblParallelSe;
    private java.awt.Label lblPrgrm;
    private java.awt.Label lblRoomAllocate;
    private java.awt.Label lblSession;
    private javax.swing.JLabel lblStGrp;
    private java.awt.Label lblStat;
    private java.awt.Label lblStdGrp;
    private java.awt.Label lblStudents;
    private java.awt.Label lblSub;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTag;
    private java.awt.Label lblTags;
    private java.awt.Label lblViewTimeTables;
    private javax.swing.JLabel lblWarningMsg;
    private java.awt.Label lblYS;
    private java.awt.Panel panel1;
    private javax.swing.JTable tblSearchedSessions;
    // End of variables declaration//GEN-END:variables
}
