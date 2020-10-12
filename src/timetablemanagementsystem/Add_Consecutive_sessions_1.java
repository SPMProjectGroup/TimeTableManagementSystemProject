/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time._table_management;

import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DELL
 */
public class Add_Consecutive_sessions extends javax.swing.JFrame {

    /**
     * Creates new form Add_Consecutive_sessions
     */
    
    int SelectedId;
    
    public Add_Consecutive_sessions(){
        initComponents();
        setvalues();
        
        show_consecutiveSessions();
        sessions.setRowHeight(30);
        consecutive_sessions.setRowHeight(30);
                
       //for set max size for frame
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }
    
      //get the connection
    public Connection getConnection(){
    
    Connection con;
    try{
//    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost/timetablemanagementsystem", "root", "");
    return con;
    }catch(Exception e){
     e.printStackTrace();
     return null;
    }
    
    }
    
     //to retrieve db table data
    
    public ArrayList<sessions> getSessionList(){
    
    ArrayList<sessions> sessionList= new ArrayList<sessions>(); 
    Connection connection=getConnection();
    
    String studentGroup = combo_studentGroup.getSelectedItem().toString();

    String query= "SELECT `SessionId`,`Tag`,`StudentGroup`,`Subject`,`NoOfStudents`,`SessionDuration` FROM `sessions` WHERE StudentGroup='"+studentGroup+"' ";
    Statement st;
    ResultSet rs;

     try{
     st = connection.createStatement();
     rs= st.executeQuery(query);
     sessions session;
     while(rs.next()){
     session = new sessions(rs.getInt("SessionId"),rs.getString("Tag"),rs.getString("StudentGroup"),rs.getString("Subject"),rs.getInt("NoOfStudents"),rs.getInt("SessionDuration"));
     sessionList.add(session);
     }
     }catch(Exception e){
      e.printStackTrace();
     }
      return sessionList;  
    }
    
    //Display data in Jtable
    public void show_Sessions(){
        
    ArrayList<sessions> list= getSessionList();
    DefaultTableModel model=(DefaultTableModel)sessions.getModel();
    Object[] row=new Object[6];
    
    for(int i=0; i<list.size(); i++){
        row[0]=list.get(i).getSessionId();
        row[1]=list.get(i).getTag();
        row[2]=list.get(i).getStudentGroup();
        row[3]=list.get(i).getSubject();
        row[4]=list.get(i).getNoOfStudents();
        row[5]=list.get(i).getDuration();
   
        model.addRow(row);
    }
    
    }
    
    //to retrieve db table data
    
    public ArrayList<consecutiveSessions> getConsecutiveSessionList(){
    
    ArrayList<consecutiveSessions> consecutivesessionList= new ArrayList<consecutiveSessions>(); 
    Connection connection=getConnection();
    
   
    String query= "SELECT `ConsecutiveSessionId`, `Tag`, `StudentGroups`, `Subject`, `NoOfStudents`, `SessionDuration`, `Session1Id`, `Session2Id` FROM `consecutivesessions` ";
    Statement st;
    ResultSet rs;

     try{
     st = connection.createStatement();
     rs= st.executeQuery(query);
     consecutiveSessions csession;
     while(rs.next()){
     csession = new consecutiveSessions(rs.getInt("ConsecutiveSessionId"),rs.getString("Tag"),rs.getString("StudentGroups"),rs.getString("Subject"),rs.getInt("NoOfStudents"),rs.getInt("SessionDuration"),rs.getInt("Session1Id"),rs.getInt("Session2Id"));
     consecutivesessionList.add(csession);
     }
     }catch(Exception e){
      e.printStackTrace();
     }
      return consecutivesessionList;  
    }
    
    //Display data in Jtable
    public void show_consecutiveSessions(){
        
    ArrayList<consecutiveSessions> list= getConsecutiveSessionList();
    DefaultTableModel model=(DefaultTableModel)consecutive_sessions.getModel();
    Object[] row=new Object[8];
    
    for(int i=0; i<list.size(); i++){
        row[0]=list.get(i).getConsecutiveSessionsId();
        row[1]=list.get(i).getcTag();
        row[2]=list.get(i).getcStudentGroup();
        row[3]=list.get(i).getcSubject();
        row[4]=list.get(i).getcNoOfStudents();
        row[5]=list.get(i).getcSessionDuration();
        row[6]=list.get(i).getSession1();
        row[7]=list.get(i).getSession2();
        model.addRow(row);
    }
    
    }
    
    
     //Execute the sql query
    public void executeSQlQuery2(String query,String message){
    
        Connection con= getConnection();
        Statement st;
        
        try{
        st= con.createStatement();
        if((st.executeUpdate(query))==1){
            
            //refresh Jtable data

            DefaultTableModel model2=(DefaultTableModel) consecutive_sessions.getModel();
            model2.setRowCount(0);
            show_consecutiveSessions();

           JOptionPane.showMessageDialog(null, message+ " Successfully");
        }else{
           JOptionPane.showMessageDialog(null, message+"can Not be done ");
        }
        }catch(Exception e){}
    
    }

           
    //to set values to drop down lists from database tables.
    
    public void setvalues(){
        Connection con= getConnection();
        Statement pst1;
        ResultSet rs1;
        
    try{
        
    String qry1="SELECT DISTINCT(`StudentGroup`) FROM `sessions`";
    
    pst1=con.prepareStatement(qry1);
    rs1=pst1.executeQuery(qry1);

    combo_studentGroup.removeAllItems();
     
    while(rs1.next()){
    combo_studentGroup.addItem(rs1.getString("StudentGroup"));
    }
    
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
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
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDayHour1 = new java.awt.Label();
        lblLec1 = new java.awt.Label();
        lblSub1 = new java.awt.Label();
        lblTags1 = new java.awt.Label();
        lblStudents1 = new java.awt.Label();
        lblLocation1 = new java.awt.Label();
        lblPrgrm1 = new java.awt.Label();
        lblStdGrp1 = new java.awt.Label();
        lblHome1 = new java.awt.Label();
        lblYS1 = new java.awt.Label();
        lblStat1 = new java.awt.Label();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        combo_studentGroup = new javax.swing.JComboBox<>();
        btn_viewsessions = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        BTN_add = new javax.swing.JButton();
        btn_ok = new javax.swing.JButton();
        s1_tag = new javax.swing.JTextField();
        s2_tag = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        sessions = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        consecutive_sessions = new javax.swing.JTable();
        studentGroup = new javax.swing.JTextField();
        subject = new javax.swing.JTextField();
        s2_sno = new javax.swing.JTextField();
        s1_sno = new javax.swing.JTextField();
        s2_duration = new javax.swing.JTextField();
        s1_duration = new javax.swing.JTextField();
        session1 = new javax.swing.JComboBox<>();
        session2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(483, 483, 483)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(100, 149, 237));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        lblDayHour1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDayHour1.setText("Working Day & Hours |");
        lblDayHour1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDayHour1MouseClicked(evt);
            }
        });

        lblLec1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblLec1.setText("Lecturers |");
        lblLec1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLec1MouseClicked(evt);
            }
        });

        lblSub1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSub1.setText("Subjects |");
        lblSub1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub1MouseClicked(evt);
            }
        });

        lblTags1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTags1.setText(" Tags |");
        lblTags1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTags1MouseClicked(evt);
            }
        });

        lblStudents1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStudents1.setText("Students |");
        lblStudents1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStudents1MouseClicked(evt);
            }
        });

        lblLocation1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblLocation1.setText("Location |");
        lblLocation1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLocation1MouseClicked(evt);
            }
        });

        lblPrgrm1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPrgrm1.setText("Programme |");
        lblPrgrm1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrgrm1MouseClicked(evt);
            }
        });

        lblStdGrp1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStdGrp1.setText("Student Groups  |");
        lblStdGrp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStdGrp1MouseClicked(evt);
            }
        });

        lblHome1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblHome1.setText("Home |");
        lblHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHome1MouseClicked(evt);
            }
        });

        lblYS1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblYS1.setText("Years & Semesters |");
        lblYS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblYS1MouseClicked(evt);
            }
        });

        lblStat1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStat1.setText("Statistics |");
        lblStat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStat1MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setText("Sub Group Number");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDayHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblLec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblStudents1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTags1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(lblLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lblStat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lblPrgrm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblStdGrp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStdGrp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrgrm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblYS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblDayHour1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblStudents1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTags1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblLec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(13, 13, 13))))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Student Group ID");

        combo_studentGroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_studentGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_studentGroupActionPerformed(evt);
            }
        });

        btn_viewsessions.setBackground(new java.awt.Color(0, 153, 153));
        btn_viewsessions.setLabel("View Sessions");
        btn_viewsessions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_viewsessionsMouseClicked(evt);
            }
        });
        btn_viewsessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewsessionsActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("View Session Details");

        BTN_add.setBackground(new java.awt.Color(0, 153, 255));
        BTN_add.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BTN_add.setText("ADD ");
        BTN_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_addActionPerformed(evt);
            }
        });

        btn_ok.setBackground(new java.awt.Color(0, 153, 153));
        btn_ok.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_ok.setText("OK");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        s1_tag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        s1_tag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1_tagActionPerformed(evt);
            }
        });

        s2_tag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        s2_tag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s2_tagActionPerformed(evt);
            }
        });

        sessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "session Id", "Tag", "Student Group", "Subject", "No Of Students", "Session Duration"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(sessions);
        if (sessions.getColumnModel().getColumnCount() > 0) {
            sessions.getColumnModel().getColumn(0).setPreferredWidth(20);
            sessions.getColumnModel().getColumn(1).setPreferredWidth(50);
            sessions.getColumnModel().getColumn(2).setPreferredWidth(150);
            sessions.getColumnModel().getColumn(3).setPreferredWidth(100);
            sessions.getColumnModel().getColumn(4).setPreferredWidth(100);
            sessions.getColumnModel().getColumn(5).setResizable(false);
            sessions.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        consecutive_sessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "consecutive session Id", "Tag", "Student Group", "Subject", "No Of students", "session duration"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        consecutive_sessions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consecutive_sessionsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(consecutive_sessions);
        if (consecutive_sessions.getColumnModel().getColumnCount() > 0) {
            consecutive_sessions.getColumnModel().getColumn(1).setPreferredWidth(75);
            consecutive_sessions.getColumnModel().getColumn(2).setPreferredWidth(150);
            consecutive_sessions.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        studentGroup.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        subject.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        s2_sno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        s2_sno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s2_snoActionPerformed(evt);
            }
        });

        s1_sno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        s2_duration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        s1_duration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        session1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        session1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        session2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        session2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        session2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                session2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Session Two ID");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Session One ID");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("Add Consecutive Sessions");

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

        btn_delete.setBackground(new java.awt.Color(255, 51, 51));
        btn_delete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(526, 526, 526)
                        .addComponent(jLabel6)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combo_studentGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_viewsessions, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(session1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(s1_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(141, 141, 141)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(session2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(s2_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(s1_sno, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(s2_sno, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(s1_duration, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(s2_duration, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(73, 73, 73)
                                    .addComponent(BTN_add, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_studentGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(btn_viewsessions, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(session2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(session1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s2_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s1_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s2_sno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s1_sno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s1_duration, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s2_duration, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTN_add, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblDayHour1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHour1MouseClicked

    }//GEN-LAST:event_lblDayHour1MouseClicked

    private void lblLec1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLec1MouseClicked
        // TODO add your handling code here:
        LecturerManage lecMng = new LecturerManage();
        lecMng.setSize(2000, 2000);
        lecMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblLec1MouseClicked

    private void lblSub1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub1MouseClicked
        // TODO add your handling code here:
        SubjectManage subMng = new SubjectManage();
        subMng.setSize(2000, 2000);
        subMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblSub1MouseClicked

    private void lblTags1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTags1MouseClicked
        // TODO add your handling code here:
        Tag tag = new Tag();
        tag.setSize(2000, 2000);
        tag.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblTags1MouseClicked

    private void lblStudents1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudents1MouseClicked
        // TODO add your handling code here:
        Student student = new Student();
        student.setSize(2000, 2000);
        student.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblStudents1MouseClicked

    private void lblLocation1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLocation1MouseClicked
        // TODO add your handling code here:
        LocationDetails locationDetails = new LocationDetails();
        locationDetails.setSize(2000, 2000);
        locationDetails.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblLocation1MouseClicked

    private void lblPrgrm1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrgrm1MouseClicked
        // TODO add your handling code here:
        Add_Programme addPrgrm = new Add_Programme();
        addPrgrm.setSize(2000, 2000);
        addPrgrm.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblPrgrm1MouseClicked

    private void lblStdGrp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStdGrp1MouseClicked
        // TODO add your handling code here:
        Add_GroupNo addGrpNo = new Add_GroupNo();
        addGrpNo.setSize(2000, 2000);
        addGrpNo.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblStdGrp1MouseClicked

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setSize(2000, 2000);
        home.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblHome1MouseClicked

    private void lblYS1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblYS1MouseClicked
        // TODO add your handling code here:
        Add_YearAndSemester addYrSem = new Add_YearAndSemester();
        addYrSem.setSize(2000, 2000);
        addYrSem.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblYS1MouseClicked

    private void lblStat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStat1MouseClicked
        // TODO add your handling code here:
        StatisticsDashboard statMng = new StatisticsDashboard();
        statMng.setSize(2000, 2000);
        statMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblStat1MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        Add_SubGroupNo addSubGrpNo = new Add_SubGroupNo();
        addSubGrpNo.setSize(2000, 2000);
        addSubGrpNo.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void btn_viewsessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_viewsessionsMouseClicked
        DefaultTableModel model1=(DefaultTableModel) sessions.getModel();
        model1.setRowCount(0);
        Connection con= getConnection();
        Statement pst2;
        ResultSet rs2;
        
       
    try{

    String qry2="SELECT `SessionId` FROM `sessions` WHERE StudentGroup='"+combo_studentGroup.getSelectedItem()+"' ";
    
    pst2=con.prepareStatement(qry2);
    rs2=pst2.executeQuery(qry2);

     session1.removeAllItems();
     session2.removeAllItems();

     while(rs2.next()){
     session1.addItem(rs2.getString("SessionId"));
     session2.addItem(rs2.getString("SessionId"));
    }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }

    show_Sessions();        
    }//GEN-LAST:event_btn_viewsessionsMouseClicked

    private void btn_viewsessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewsessionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_viewsessionsActionPerformed

    private void BTN_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_addActionPerformed

        Connection connection=getConnection();
        Statement st;
        ResultSet rs;
        int count=0;
        String Tag=s1_tag.getText()+ " , "+s2_tag.getText();
        int NO= Integer.sum(Integer.parseInt(s1_sno.getText()),Integer.parseInt(s2_sno.getText()));
        int Duration= Integer.sum(Integer.parseInt(s1_duration.getText()),Integer.parseInt(s2_duration.getText()));

        String Query="SELECT Count(CONCAT(`Tag`, `StudentGroups`, `Subject`, `NoOfStudents`, `SessionDuration`, `Session1Id`, `Session2Id`)) FROM `consecutivesessions` WHERE Tag='"+Tag+"' AND StudentGroups='"+studentGroup.getText()+"' AND Subject='"+subject.getText()+"' AND NoOfStudents='"+NO+"' AND SessionDuration='"+Duration+"' AND Session1Id='"+session1.getSelectedItem()+"' AND Session2Id='"+session2.getSelectedItem()+"'";
        try{

            st= connection.createStatement();
            rs= st.executeQuery(Query);
            rs.next();
            count= rs.getInt(1);

            if(count>0){

                JOptionPane.showMessageDialog(null,"Session already exists !!!");
            }
            else  if(session1.getSelectedItem().equals(session2.getSelectedItem())){
                JOptionPane.showMessageDialog(null,"Please choose two different sessions !!!");
            }
            else{
                
                String query = "INSERT INTO `consecutivesessions`( `Tag`, `StudentGroups`, `Subject`, `NoOfStudents`, `SessionDuration`, `Session1Id`, `Session2Id`) VALUES ('"+Tag+"','"+studentGroup.getText()+"','"+subject.getText()+"', '"+NO+"','"+Duration+"','"+session1.getSelectedItem()+"','"+session2.getSelectedItem()+"')";
                executeSQlQuery2(query, "Consecutive session Inserted");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_BTN_addActionPerformed

    private void s1_tagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1_tagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s1_tagActionPerformed

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        Connection con=getConnection();
        Statement st1,st2,st3,st4,st5,st6,st7,st8;
        ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8;

        try{
           
            String tag1Query="SELECT `Tag` FROM `sessions` WHERE `SessionId`='"+session1.getSelectedItem()+"'";
            String tag2Query="SELECT `Tag` FROM `sessions` WHERE `SessionId`='"+session2.getSelectedItem()+"'";

            String studentGroupQuery="SELECT `StudentGroup` FROM `sessions` WHERE `SessionId`='"+session1.getSelectedItem()+"'";
            String subjectQuery="SELECT `Subject` FROM `sessions` WHERE `SessionId`='"+session1.getSelectedItem()+"'";

            String studentNo1Query="SELECT NoOfStudents FROM `sessions` WHERE SessionId="+session1.getSelectedItem()+" ";
            String studentNo2Query="SELECT NoOfStudents FROM `sessions` WHERE SessionId="+session2.getSelectedItem()+" ";

            String duration1Query="SELECT SessionDuration FROM `sessions` WHERE SessionId= "+session1.getSelectedItem()+" ";
            String duration2Query="SELECT SessionDuration FROM `sessions` WHERE SessionId= "+session2.getSelectedItem()+" ";

            st1=con.prepareStatement(tag1Query);
            rs1=st1.executeQuery(tag1Query);

            st2=con.prepareStatement(tag2Query);
            rs2=st2.executeQuery(tag2Query);

            st3=con.prepareStatement(studentGroupQuery);
            rs3=st3.executeQuery(studentGroupQuery);

            st4=con.prepareStatement(subjectQuery);
            rs4=st4.executeQuery(subjectQuery);

            st5=con.prepareStatement(studentNo1Query);
            rs5=st5.executeQuery(studentNo1Query);

            st6=con.prepareStatement(studentNo2Query);
            rs6=st6.executeQuery(studentNo2Query);

            st7=con.prepareStatement(duration1Query);
            rs7=st7.executeQuery(duration1Query);

            st8=con.prepareStatement(duration2Query);
            rs8=st8.executeQuery(duration2Query);

            s1_tag.removeAll();
            s2_tag.removeAll();
            studentGroup.removeAll();
            subject.removeAll();
            s1_sno.removeAll();
            s2_sno.removeAll();
            s1_duration.removeAll();
            s2_duration.removeAll();

            
            while(rs1.next()){
                s1_tag.setText(rs1.getString("Tag"));
            }
            while(rs2.next()){
                s2_tag.setText(rs2.getString("Tag"));
            }
            while(rs3.next()){
                studentGroup.setText(rs3.getString("StudentGroup"));
            }
            while(rs4.next()){
                subject.setText(rs4.getString("Subject"));
            }
            while(rs5.next()){
                s1_sno.setText(rs5.getString("NoOfStudents"));
            }
            while(rs6.next()){
                s2_sno.setText(rs6.getString("NoOfStudents"));
            }
            while(rs7.next()){
                s1_duration.setText(rs7.getString("SessionDuration"));
            }
            while(rs8.next()){
                s2_duration.setText(rs8.getString("SessionDuration"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

       
    }//GEN-LAST:event_btn_okActionPerformed

    private void session2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_session2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_session2ActionPerformed

    private void combo_studentGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_studentGroupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_studentGroupActionPerformed

    private void s2_tagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s2_tagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s2_tagActionPerformed

    private void s2_snoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s2_snoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s2_snoActionPerformed

    private void btn_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseClicked
        String query="DELETE FROM `consecutivesessions` WHERE ConsecutiveSessionId = "+SelectedId;
        executeSQlQuery2(query, "Deleted");
    }//GEN-LAST:event_btn_deleteMouseClicked

    private void consecutive_sessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consecutive_sessionsMouseClicked
           //Display selected row in input fields
    
    int i= consecutive_sessions.getSelectedRow();
    TableModel model= consecutive_sessions.getModel();

    SelectedId=(Integer) model.getValueAt(i,0);
       
    }//GEN-LAST:event_consecutive_sessionsMouseClicked

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
            java.util.logging.Logger.getLogger(Add_Consecutive_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Consecutive_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Consecutive_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Consecutive_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new Add_Consecutive_sessions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_ok;
    private javax.swing.JButton btn_viewsessions;
    private javax.swing.JComboBox<String> combo_studentGroup;
    private javax.swing.JTable consecutive_sessions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label lblDayHour1;
    private java.awt.Label lblHome1;
    private java.awt.Label lblLec1;
    private java.awt.Label lblLocation1;
    private java.awt.Label lblPrgrm1;
    private java.awt.Label lblStat1;
    private java.awt.Label lblStdGrp1;
    private java.awt.Label lblStudents1;
    private java.awt.Label lblSub1;
    private java.awt.Label lblTags1;
    private java.awt.Label lblYS1;
    private javax.swing.JTextField s1_duration;
    private javax.swing.JTextField s1_sno;
    private javax.swing.JTextField s1_tag;
    private javax.swing.JTextField s2_duration;
    private javax.swing.JTextField s2_sno;
    private javax.swing.JTextField s2_tag;
    private javax.swing.JComboBox<String> session1;
    private javax.swing.JComboBox<String> session2;
    private javax.swing.JTable sessions;
    private javax.swing.JTextField studentGroup;
    private javax.swing.JTextField subject;
    // End of variables declaration//GEN-END:variables
}
