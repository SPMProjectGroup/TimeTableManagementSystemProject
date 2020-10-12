/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DELL
 */
public class Add_Not_Overlapping_sessions extends javax.swing.JFrame {

    /**
     * Creates new form Add_Not_Overlapping_sessions
     */
//    JTextField no;
//    JPanel panel1;
    int SelectedId;
    
    public Add_Not_Overlapping_sessions() {
        initComponents();
        setvalues();
        sessions.setRowHeight(30);
        show_NotOverlappingSessions();
        notoverlappingsessions.setRowHeight(30);
                
       //for set max size for frame
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
//        no= new JTextField(10);
//        panel1= new JPanel();
//        setLayout(null);
//        no.setBounds(50, 20, 50, 30);
//        no.addCaretListener(this);
//        panel1.setBounds(50, 80, 200, 200);
//        panel1.setOpaque(true);
//        panel1.setBackground(Color.LIGHT_GRAY);
//        
//        add(no);
//        add(panel1);
    }

//    public void caretUpdate(CaretEvent ce){
//    String str= no.getText();
//     
//     panel1.removeAll();
//     if(str.equals("")){
//       JOptionPane.showMessageDialog(null, "Please enter a number and continue !!!");
//     }
//     else if(!str.equals("")){
//       int num= Integer.parseInt(str);
//       JTextField jtextfields[]= new JTextField[num];
//       
//       for(int i=0;i<jtextfields.length;i++ ){
//         jtextfields[i]= new JTextField("Session"+i);
//         panel1.add(jtextfields[i]);
//       }
//     }
//     panel1.validate();
//     panel1.repaint();
//    
//    }
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
    
     //to retrieve session db table data
    
    public ArrayList<Session> getSessionList(){
    
    ArrayList<Session> sessionList= new ArrayList<Session>(); 
    Connection connection=getConnection();
    
    String studentGroup = combo_studentGroup.getSelectedItem().toString();

    String query= "SELECT `SessionId`,`Tag`,`StudentGroup`,`Subject`,`NoOfStudents`,`SessionDuration` FROM `sessions` WHERE StudentGroup='"+studentGroup+"' ";
    Statement st;
    ResultSet rs;

     try{
     st = connection.createStatement();
     rs= st.executeQuery(query);
     Session session;
     while(rs.next()){
     session = new Session(rs.getInt("SessionId"),rs.getString("Tag"),rs.getString("StudentGroup"),rs.getString("Subject"),rs.getInt("NoOfStudents"),rs.getInt("SessionDuration"));
     sessionList.add(session);
     }
     }catch(Exception e){
      e.printStackTrace();
     }
      return sessionList;  
    }
    
    //Display data in session Jtable
    public void show_Sessions(){
        
    ArrayList<Session> list= getSessionList();
    DefaultTableModel model=(DefaultTableModel)sessions.getModel();
    Object[] row=new Object[6];
    
    for(int i=0; i<list.size(); i++){
        row[0]=list.get(i).getSessionID();
        row[1]=list.get(i).getTag();
        row[2]=list.get(i).getStGrp();
        row[3]=list.get(i).getSubject();
        row[4]=list.get(i).getNoOfStudents();
        row[5]=list.get(i).getSessionDuration();
   
        model.addRow(row);
    }
    
    }
    
      //to retrieve not Overlapping sessions db table data
    
    public ArrayList<notOverlapping> getNotOverlappingSessionList(){
    
    ArrayList<notOverlapping> sessionList= new ArrayList<notOverlapping>(); 
    Connection connection=getConnection();
   
    String query= "SELECT  `NotOverlappingSessionId`, `SessionId`, `Subject` FROM `notoverlappingsessions`";
    Statement st;
    ResultSet rs;

     try{
     st = connection.createStatement();
     rs= st.executeQuery(query);
     notOverlapping session;
     while(rs.next()){
     session = new notOverlapping(rs.getInt("NotOverlappingSessionId"),rs.getInt("SessionId"),rs.getString("Subject"));
     sessionList.add(session);
             
     }
     }catch(Exception e){
      e.printStackTrace();
     }
      return sessionList;  
    }
    
    //Display data in not Overlapping sessions Jtable
    public void show_NotOverlappingSessions(){
        
    ArrayList<notOverlapping> list= getNotOverlappingSessionList();
    DefaultTableModel model=(DefaultTableModel)notoverlappingsessions.getModel();
    Object[] row=new Object[3];
    
    for(int i=0; i<list.size(); i++){
        row[0]=list.get(i).getoId();
        row[1]=list.get(i).getSessionId();
        row[2]=list.get(i).getSubject();

        model.addRow(row);
    }
    
    }
    
    public int getMaxId(){
   
    Connection connection=getConnection();
 
    Statement st1;
    ResultSet rs1;
    String maxquery="SELECT max(TableId) FROM notoverlappingsessions ";
    int maxId=0;
    
     try{
     st1= connection.createStatement();
     rs1= st1.executeQuery(maxquery);
     rs1.next();

     maxId = rs1.getInt(1);
  
     }
     catch(Exception e){
      e.printStackTrace();
     }

       return maxId;
    }
       
    //to generte and return an unique id
    public int generateUniqueID(){

          int uniqueID = 100; 
          int maxID =getMaxId() ;
          
          uniqueID = uniqueID + maxID;

          return uniqueID;
    }

       //Execute the sql query
    public void executeSQlQuery(String query,String message){
    
        Connection con= getConnection();
        Statement st;
        
        try{
        st= con.createStatement();
        if((st.executeUpdate(query))==1){
            
            //refresh Jtable data

            DefaultTableModel model2=(DefaultTableModel) notoverlappingsessions.getModel();
            model2.setRowCount(0);
            show_NotOverlappingSessions();

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

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
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
        lblDayHour1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        combo_studentGroup = new javax.swing.JComboBox<>();
        btn_viewsessions = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        sessions = new javax.swing.JTable();
        ok = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ADDnew = new javax.swing.JButton();
        combo_session = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        notoverlappingsessions = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        subject = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        maxId = new javax.swing.JTextField();
        addsessiononly = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblConSession1 = new java.awt.Label();
        lblRoomAllocate1 = new java.awt.Label();
        lblSession1 = new java.awt.Label();
        lblParallelSe1 = new java.awt.Label();
        lblBotOverlappingSession1 = new java.awt.Label();
        lblNotAvTime1 = new java.awt.Label();
        lblGenTimeTables1 = new java.awt.Label();
        lblViewTimeTables1 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

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
        lblYS1.setText("Years & Semesters ");
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
                .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDayHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStdGrp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblYS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(387, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDayHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStdGrp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrgrm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblYS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStudents1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTags1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Add Not Overlapping Sessions");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("View Session Details");

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

        ok.setBackground(new java.awt.Color(0, 153, 153));
        ok.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ok.setText("OK");
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Session ID");

        ADDnew.setBackground(new java.awt.Color(0, 51, 204));
        ADDnew.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ADDnew.setText("ADD NEW NOT OVERLAPPING SESSION");
        ADDnew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ADDnewMouseClicked(evt);
            }
        });
        ADDnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDnewActionPerformed(evt);
            }
        });

        combo_session.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        combo_session.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combo_sessionFocusGained(evt);
            }
        });
        combo_session.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_sessionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                combo_sessionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                combo_sessionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                combo_sessionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                combo_sessionMouseReleased(evt);
            }
        });
        combo_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_sessionActionPerformed(evt);
            }
        });
        combo_session.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                combo_sessionPropertyChange(evt);
            }
        });
        combo_session.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combo_sessionKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Subject");

        notoverlappingsessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Not Overlapping Session ID", "Session ID", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        notoverlappingsessions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notoverlappingsessionsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(notoverlappingsessions);

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("DELETE");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        subject.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Not Overlapping ID");

        maxId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        addsessiononly.setBackground(new java.awt.Color(0, 102, 0));
        addsessiononly.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addsessiononly.setText("ADD ANOTHER SESSION ONLY");
        addsessiononly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsessiononlyActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(153, 153, 255));

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
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
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(addsessiononly)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ADDnew))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(47, 47, 47)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(combo_session, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(88, 88, 88)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(maxId, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(jButton1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(460, 460, 460))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(combo_studentGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btn_viewsessions, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_studentGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_viewsessions, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combo_session, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maxId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addsessiononly, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ADDnew, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void combo_studentGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_studentGroupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_studentGroupActionPerformed

    private void btn_viewsessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_viewsessionsMouseClicked
        DefaultTableModel model1=(DefaultTableModel) sessions.getModel();
        model1.setRowCount(0);
        Connection con= getConnection();
        Statement pst2;
        ResultSet rs2;

        String studentGroup = combo_studentGroup.getSelectedItem().toString();
//       String query= "SELECT `SessionId`,`Tag`,`StudentGroup`,`Subject`,`NoOfStudents`,`SessionDuration` FROM `sessions` WHERE StudentGroup='"+studentGroup+"' ";

        try{

            String qry2="SELECT `SessionId` FROM `sessions` WHERE StudentGroup='"+studentGroup+"' ";

            pst2=con.prepareStatement(qry2);
            rs2=pst2.executeQuery(qry2);

            combo_session.removeAllItems();
          
            
            while(rs2.next()){
            combo_session.addItem(rs2.getString("SessionId"));
            
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

        show_Sessions();
    }//GEN-LAST:event_btn_viewsessionsMouseClicked

    private void btn_viewsessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewsessionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_viewsessionsActionPerformed

    private void okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseClicked
//        NOverlapping noverlapping = new NOverlapping();
//        noverlapping.setSize(2000, 2000);
//        noverlapping.setVisible(true);
//        this.setVisible(false);
//        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//        this.dispose();

        Connection con= getConnection();
        Statement pst;
        ResultSet rs;
        String session = combo_session.getSelectedItem().toString();
        try{
             
             String qry="SELECT `Subject` FROM `sessions` WHERE SessionId='"+session+"'";
             pst=con.prepareStatement(qry);
             rs=pst.executeQuery(qry);
             
            if(combo_session.getSelectedItem().equals(" ")){
              JOptionPane.showMessageDialog(null, "Please select a student group first !!!");
            }
//             combo_subject.removeAllItems();
            subject.removeAll();
            
            while(rs.next()){
//            combo_subject.addItem(rs.getString("Subject"));
              subject.setText(rs.getString("Subject"));
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_okMouseClicked

    private void combo_sessionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_sessionMouseEntered
        
    }//GEN-LAST:event_combo_sessionMouseEntered

    private void combo_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sessionActionPerformed
   

    }//GEN-LAST:event_combo_sessionActionPerformed

    private void combo_sessionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_sessionMousePressed
//        Connection con= getConnection();
//        Statement pst;
//        ResultSet rs;
//
//        String session = combo_session.getSelectedItem().toString();
//        try{
//             
//             String qry="SELECT `Subject` FROM `sessions` WHERE SessionId='"+session+"'";
//             pst=con.prepareStatement(qry);
//             rs=pst.executeQuery(qry);
//             
//
//             combo_subject.removeAllItems();
//          
//            
//            while(rs.next()){
//            combo_subject.addItem(rs.getString("Subject"));
//            
//            }
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
    }//GEN-LAST:event_combo_sessionMousePressed

    private void combo_sessionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_sessionMouseClicked
       
    }//GEN-LAST:event_combo_sessionMouseClicked

    private void combo_sessionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_sessionMouseReleased
       
    }//GEN-LAST:event_combo_sessionMouseReleased

    private void combo_sessionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_sessionFocusGained
    
    }//GEN-LAST:event_combo_sessionFocusGained

    private void combo_sessionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_sessionMouseExited
        
    }//GEN-LAST:event_combo_sessionMouseExited

    private void combo_sessionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_combo_sessionPropertyChange
    
    }//GEN-LAST:event_combo_sessionPropertyChange

    private void combo_sessionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_sessionKeyPressed
 
    }//GEN-LAST:event_combo_sessionKeyPressed

    private void ADDnewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ADDnewMouseClicked
        
        Connection connection=getConnection();
        Statement st;
        ResultSet rs;
        int count=0;
        int NotOverlappingSessionId=generateUniqueID();
        maxId.setText(String.valueOf(NotOverlappingSessionId));
        subject.removeAll();
        String Query="SELECT Count(CONCAT(`NotOverlappingSessionId`,`SessionId`, `Subject`)) FROM `notoverlappingsessions` WHERE NotOverlappingSessionId='"+NotOverlappingSessionId+"' AND SessionId='"+combo_session.getSelectedItem()+"' AND Subject='"+subject.getText()+"' ";
        try{

            st= connection.createStatement();
            rs= st.executeQuery(Query);
            rs.next();
            count= rs.getInt(1);

            if(count>0){

                JOptionPane.showMessageDialog(null,"Session already exists !!!");
            }
            else if(subject.getText().isEmpty()){
                 JOptionPane.showMessageDialog(null,"Please select a session first !!!");
            }
            else{
                
                String query = "INSERT INTO `notoverlappingsessions`( `NotOverlappingSessionId`,`SessionId`, `Subject`) VALUES ('"+NotOverlappingSessionId+"','"+combo_session.getSelectedItem()+"','"+subject.getText()+"')";
                executeSQlQuery(query, "Not overlapping session Inserted");
     
                maxId.setText(String.valueOf(NotOverlappingSessionId));
                
            }

        }catch(Exception e){
            e.printStackTrace();
        }        
    }//GEN-LAST:event_ADDnewMouseClicked

    private void notoverlappingsessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notoverlappingsessionsMouseClicked
    //Display selected row in input fields
    
    int i= notoverlappingsessions.getSelectedRow();
    TableModel model= notoverlappingsessions.getModel();

    combo_session.setSelectedItem(model.getValueAt(i,1).toString());
    subject.setText(model.getValueAt(i,2).toString());

 
     SelectedId=(Integer) model.getValueAt(i,1);
    }//GEN-LAST:event_notoverlappingsessionsMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String query="DELETE FROM `notoverlappingsessions` WHERE SessionId = "+SelectedId;
        executeSQlQuery(query, "Deleted");
    }//GEN-LAST:event_jButton1MouseClicked

    private void ADDnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDnewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ADDnewActionPerformed

    private void addsessiononlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsessiononlyActionPerformed
        Connection connection=getConnection();
        Statement st;
        ResultSet rs;
        int count=0;
               
        String Query="SELECT Count(CONCAT(`NotOverlappingSessionId`,`SessionId`, `Subject`)) FROM `notoverlappingsessions` WHERE NotOverlappingSessionId='"+maxId.getText()+"' AND SessionId='"+combo_session.getSelectedItem()+"' AND Subject='"+subject.getText()+"' ";
        try{

            st= connection.createStatement();
            rs= st.executeQuery(Query);
            rs.next();
            count= rs.getInt(1);

            if(count>0){

                JOptionPane.showMessageDialog(null,"Session already exists !!!");
            }

            else if(subject.getText().isEmpty()){
                 JOptionPane.showMessageDialog(null,"Please select a session first !!!");
            }
            else{
                
                String query = "INSERT INTO `notoverlappingsessions`( `NotOverlappingSessionId`,`SessionId`, `Subject`) VALUES ('"+maxId.getText()+"','"+combo_session.getSelectedItem()+"','"+subject.getText()+"')";
                executeSQlQuery(query, "Not overlapping session Inserted");
                
                subject.removeAll();
     
            }

        }catch(Exception e){
            e.printStackTrace();
        }        
    }//GEN-LAST:event_addsessiononlyActionPerformed

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

    private void lblDayHour1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHour1MouseClicked

        Workingdaysandhours wdh = new Workingdaysandhours();
        wdh.setSize(2000, 2000);
        wdh.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblDayHour1MouseClicked

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
            java.util.logging.Logger.getLogger(Add_Not_Overlapping_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Not_Overlapping_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Not_Overlapping_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Not_Overlapping_sessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Not_Overlapping_sessions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADDnew;
    private javax.swing.JButton addsessiononly;
    private javax.swing.JButton btn_viewsessions;
    private javax.swing.JComboBox<String> combo_session;
    private javax.swing.JComboBox<String> combo_studentGroup;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label lblBotOverlappingSession1;
    private java.awt.Label lblConSession1;
    private java.awt.Label lblDayHour1;
    private java.awt.Label lblGenTimeTables1;
    private java.awt.Label lblHome1;
    private java.awt.Label lblLec1;
    private java.awt.Label lblLocation1;
    private java.awt.Label lblNotAvTime1;
    private java.awt.Label lblParallelSe1;
    private java.awt.Label lblPrgrm1;
    private java.awt.Label lblRoomAllocate1;
    private java.awt.Label lblSession1;
    private java.awt.Label lblStat1;
    private java.awt.Label lblStdGrp1;
    private java.awt.Label lblStudents1;
    private java.awt.Label lblSub1;
    private java.awt.Label lblTags1;
    private java.awt.Label lblViewTimeTables1;
    private java.awt.Label lblYS1;
    private javax.swing.JTextField maxId;
    private javax.swing.JTable notoverlappingsessions;
    private javax.swing.JButton ok;
    private javax.swing.JTable sessions;
    private javax.swing.JTextField subject;
    // End of variables declaration//GEN-END:variables
}
