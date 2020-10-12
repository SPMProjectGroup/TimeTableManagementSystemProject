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
public class ViewSubjects extends javax.swing.JFrame {

    /**
     * Creates new form viewSubjects
     */
    int selectedSubjectId = 0;
    
    public ViewSubjects() {
        initComponents();
        DisplaySubjects();
        
        //resize table column size
        viewSubTbl.setAutoResizeMode(viewSubTbl.AUTO_RESIZE_OFF);
        TableColumn col = viewSubTbl.getColumnModel().getColumn(0);
        col.setPreferredWidth(130);
        col = viewSubTbl.getColumnModel().getColumn(1);
        col.setPreferredWidth(130);
        col = viewSubTbl.getColumnModel().getColumn(2);
        col.setPreferredWidth(150);
        col = viewSubTbl.getColumnModel().getColumn(3);
        col.setPreferredWidth(130);
        col = viewSubTbl.getColumnModel().getColumn(4);
        col.setPreferredWidth(130);
        col = viewSubTbl.getColumnModel().getColumn(5);
        col.setPreferredWidth(130);
        col = viewSubTbl.getColumnModel().getColumn(6);
        col.setPreferredWidth(130);
        col = viewSubTbl.getColumnModel().getColumn(7);
        col.setPreferredWidth(130);
        col = viewSubTbl.getColumnModel().getColumn(8);
        col.setPreferredWidth(130);
        
        viewSubTbl.setRowHeight(40);
        
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
                DefaultTableModel model = (DefaultTableModel)viewSubTbl.getModel();
                model.setRowCount(0);
                DisplaySubjects();
                
                JOptionPane.showMessageDialog(null, message+" successfully");
            }else{
                 JOptionPane.showMessageDialog(null, "Not "+message); 
            }
        }catch(Exception ex){
                ex.printStackTrace();
        }
    }
    
     //get all subject details
    public ArrayList<Subject> getSubjectList(){
         ArrayList<Subject> subjectList = new ArrayList<Subject>();
         Connection connection = getConnection();
         String query = "SELECT * from subjects";
         Statement statement;
         ResultSet res;
         
         try{
             statement = connection.createStatement();
             res = statement.executeQuery(query);
             
             Subject sub;
             while(res.next()){
                 sub = new Subject(res.getInt("SubjectID"),res.getString("SubjectCode"),res.getString("SubjectName"),
                                        res.getString("OfferedYear"),res.getString("OfferedSem"), res.getInt("NoOfLecHours"), 
                                        res.getInt("NoOfTuteHours"),res.getInt("NoOfLabHours"),res.getInt("NoOfEvaluationHours"));
               
                 subjectList.add(sub);
                 
             }
         }catch(Exception e){
             e.printStackTrace();
         }
         return subjectList;
    }
    
    //display data in Jtable
    public void DisplaySubjects(){
        
        ArrayList<Subject> subList = getSubjectList();
        DefaultTableModel model = (DefaultTableModel)viewSubTbl.getModel();
        Object[] row = new Object[9];
        for(int i = 0; i < subList.size(); i++){
            row[0] = subList.get(i).getSubID();
            row[1] = subList.get(i).getSubCode();
            row[2] = subList.get(i).getSubName();
            row[3] = subList.get(i).getOfferedYear();
            row[4] = subList.get(i).getOfferedSem();
            row[5] = subList.get(i).getNoOfLecH();
            row[6] = subList.get(i).getNoOfTuteH();
            row[7] = subList.get(i).getNoOfLabH();
            row[8] = subList.get(i).getNoOfEvaluationH();
            
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
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewSubTbl = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comboMngOfferedYear = new javax.swing.JComboBox<>();
        comboMngOfferedSem = new javax.swing.JComboBox<>();
        btnEditSub = new javax.swing.JButton();
        btnDltSub = new javax.swing.JButton();
        txtMngSubCode = new javax.swing.JTextField();
        txtMngLecH = new javax.swing.JTextField();
        txtMngSubName = new javax.swing.JTextField();
        txtMngLabH = new javax.swing.JTextField();
        txtMngTuteH = new javax.swing.JTextField();
        txtMngEvlH = new javax.swing.JTextField();
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
        lblErrMngLbH = new java.awt.Label();
        lblErrMngEvH = new java.awt.Label();
        lblErrMngTH = new java.awt.Label();
        lblErrMngLecH = new java.awt.Label();
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

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(607, 607, 607)
                .addComponent(jLabel13)
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("VIEW SUBJECTS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("UPDATE - DELETE");

        viewSubTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject ID", "Subject Code", "Subject Name", "Offered Year", "Offered Sem", "NoOfLecHours", "NoOfTuteHours", "NoOfLabHours", "NoOfEvaluationHours"
            }
        ));
        viewSubTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewSubTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(viewSubTbl);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Subject Code");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Subject  Name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Offered Year");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Offered Semester");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("No of Lecture hours");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("No of tutorial hours");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("No of lab hours");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("No of evaluation hours");

        comboMngOfferedYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboMngOfferedYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Y1", "Y2", "Y3", "Y4", "Y5" }));
        comboMngOfferedYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMngOfferedYearActionPerformed(evt);
            }
        });

        comboMngOfferedSem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboMngOfferedSem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S1", "S2" }));
        comboMngOfferedSem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMngOfferedSemActionPerformed(evt);
            }
        });

        btnEditSub.setBackground(new java.awt.Color(0, 102, 0));
        btnEditSub.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEditSub.setForeground(new java.awt.Color(255, 255, 255));
        btnEditSub.setText("UPDATE");
        btnEditSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSubActionPerformed(evt);
            }
        });

        btnDltSub.setBackground(new java.awt.Color(255, 0, 0));
        btnDltSub.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDltSub.setForeground(new java.awt.Color(255, 255, 255));
        btnDltSub.setText("DELETE");
        btnDltSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDltSubActionPerformed(evt);
            }
        });

        txtMngSubCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMngSubCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMngSubCodeActionPerformed(evt);
            }
        });

        txtMngLecH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMngLecH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMngLecHActionPerformed(evt);
            }
        });
        txtMngLecH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMngLecHKeyPressed(evt);
            }
        });

        txtMngSubName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMngSubName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMngSubNameActionPerformed(evt);
            }
        });

        txtMngLabH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMngLabH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMngLabHActionPerformed(evt);
            }
        });
        txtMngLabH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMngLabHKeyPressed(evt);
            }
        });

        txtMngTuteH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMngTuteH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMngTuteHActionPerformed(evt);
            }
        });
        txtMngTuteH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMngTuteHKeyPressed(evt);
            }
        });

        txtMngEvlH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMngEvlH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMngEvlHKeyPressed(evt);
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
            .addGap(0, 65, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
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
                                    .addComponent(lblLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(13, 13, 13))
        );

        lblErrMngLbH.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblErrMngLbH.setForeground(new java.awt.Color(255, 0, 0));

        lblErrMngEvH.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblErrMngEvH.setForeground(new java.awt.Color(255, 0, 0));

        lblErrMngTH.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblErrMngTH.setForeground(new java.awt.Color(255, 0, 0));

        lblErrMngLecH.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblErrMngLecH.setForeground(new java.awt.Color(255, 0, 0));

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(487, 487, 487)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(btnEditSub, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnDltSub, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMngSubCode)
                            .addComponent(txtMngSubName)
                            .addComponent(comboMngOfferedYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboMngOfferedSem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMngLecH)
                            .addComponent(txtMngTuteH)
                            .addComponent(txtMngLabH)
                            .addComponent(txtMngEvlH)
                            .addComponent(lblErrMngLbH, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrMngEvH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrMngLecH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrMngTH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(52, 52, 52))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                        .addGap(195, 195, 195))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMngSubCode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtMngSubName)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboMngOfferedYear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboMngOfferedSem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMngLecH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(lblErrMngLecH, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMngTuteH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(lblErrMngTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMngLabH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(lblErrMngLbH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMngEvlH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrMngEvH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDltSub, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditSub, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboMngOfferedSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMngOfferedSemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMngOfferedSemActionPerformed

    private void btnEditSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSubActionPerformed
        // TODO add your handling code here:

        String subCode = txtMngSubCode.getText();
        String subName = txtMngSubName.getText();
        String offeredYear = comboMngOfferedYear.getSelectedItem().toString();
        String offeredSem = comboMngOfferedSem.getSelectedItem().toString();
        int lecH = Integer.parseInt(txtMngLecH.getText());
        int tuteH = Integer.parseInt(txtMngTuteH.getText());
        int labH = Integer.parseInt(txtMngLabH.getText());
        int evlH = Integer.parseInt(txtMngEvlH.getText());

        //jLabel10.setText(String.valueOf(selectedSubjectId));

        String updateQuery = "UPDATE subjects SET  SubjectName = '"+subName+"', SubjectCode = '"+subCode+"', OfferedYear = '"+offeredYear+"', OfferedSem = '"+offeredSem+"',"
        + "NoOfLecHours = '"+lecH+"', NoOfTuteHours = '"+tuteH+"', NoOfLabHours = '"+labH+"', NoOfEvaluationHours = '"+evlH+"' WHERE SubjectID = " + selectedSubjectId;
        executeSqlQuery(updateQuery, "Subject details Updated");
    }//GEN-LAST:event_btnEditSubActionPerformed

    private void btnDltSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDltSubActionPerformed

        String dltQuery = "DELETE FROM subjects WHERE SubjectID = "+selectedSubjectId;
        executeSqlQuery(dltQuery, "Subject details Deleted");
    }//GEN-LAST:event_btnDltSubActionPerformed

    private void comboMngOfferedYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMngOfferedYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMngOfferedYearActionPerformed

    private void txtMngLabHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMngLabHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMngLabHActionPerformed

    private void txtMngTuteHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMngTuteHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMngTuteHActionPerformed

    private void txtMngSubNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMngSubNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMngSubNameActionPerformed

    private void txtMngLecHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMngLecHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMngLecHActionPerformed

    private void viewSubTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewSubTblMouseClicked
        
          //Display selected row in jtextfields
        int i = viewSubTbl.getSelectedRow();
        TableModel model = viewSubTbl.getModel();
        txtMngSubCode.setText(model.getValueAt(i, 1).toString());
        txtMngSubName.setText(model.getValueAt(i, 2).toString());
        comboMngOfferedYear.setSelectedItem(model.getValueAt(i, 3).toString());
        comboMngOfferedSem.setSelectedItem(model.getValueAt(i, 4).toString());
        txtMngLecH.setText(model.getValueAt(i, 5).toString());
        txtMngTuteH.setText(model.getValueAt(i, 6).toString());
        txtMngLabH.setText(model.getValueAt(i, 7).toString());
        txtMngEvlH.setText(model.getValueAt(i, 8).toString());
        
        selectedSubjectId = (Integer) model.getValueAt(i, 0);
       // int no =9;
        //jLabel10.setText(String.valueOf(selectedSubjectId));
    }//GEN-LAST:event_viewSubTblMouseClicked

    private void txtMngSubCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMngSubCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMngSubCodeActionPerformed

    private void lblDayHourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHourMouseClicked

        Workingdaysandhours wdh = new Workingdaysandhours();
        wdh.setSize(2000, 2000);
        wdh.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblDayHourMouseClicked

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

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setSize(2000, 2000);
        home.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

    private void txtMngLecHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMngLecHKeyPressed
        // TODO add your handling code here:
        
         //validate text filed
            char value = evt.getKeyChar();
            if(Character.isLetter(value)){
                //can't able to enter in char values to text field
                txtMngLecH.setEditable(false);
                lblErrMngLecH.setText("**Please only enter numbers**");
            }else{
                txtMngLecH.setEditable(true);
                lblErrMngLecH.setText("");
            } 
    }//GEN-LAST:event_txtMngLecHKeyPressed

    private void txtMngTuteHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMngTuteHKeyPressed
        // TODO add your handling code here:
        
        //validate text filed
            char value = evt.getKeyChar();
            if(Character.isLetter(value)){
                //can't able to enter in char values to text field
                txtMngTuteH.setEditable(false);
                lblErrMngTH.setText("**Please only enter numbers**");
            }else{
                txtMngTuteH.setEditable(true);
                lblErrMngTH.setText("");
            } 
    }//GEN-LAST:event_txtMngTuteHKeyPressed

    private void txtMngLabHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMngLabHKeyPressed
        // TODO add your handling code here:
        
        //validate text filed
            char value = evt.getKeyChar();
            if(Character.isLetter(value)){
                //can't able to enter in char values to text field
                txtMngLabH.setEditable(false);
                lblErrMngLbH.setText("**Please only enter numbers**");
            }else{
                txtMngLabH.setEditable(true);
                lblErrMngLbH.setText("");
            } 
    }//GEN-LAST:event_txtMngLabHKeyPressed

    private void txtMngEvlHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMngEvlHKeyPressed
        // TODO add your handling code here:
        
        //validate text filed
            char value = evt.getKeyChar();
            if(Character.isLetter(value)){
                //can't able to enter in char values to text field
                txtMngEvlH.setEditable(false);
                lblErrMngEvH.setText("**Please only enter numbers**");
            }else{
                txtMngEvlH.setEditable(true);
                lblErrMngEvH.setText("");
            } 

    }//GEN-LAST:event_txtMngEvlHKeyPressed

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
            java.util.logging.Logger.getLogger(ViewSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                JFrame.setDefaultLookAndFeelDecorated(true);
                new ViewSubjects().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDltSub;
    private javax.swing.JButton btnEditSub;
    private javax.swing.JComboBox<String> comboMngOfferedSem;
    private javax.swing.JComboBox<String> comboMngOfferedYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lblBotOverlappingSession;
    private java.awt.Label lblConSession;
    private java.awt.Label lblDayHour;
    private java.awt.Label lblErrMngEvH;
    private java.awt.Label lblErrMngLbH;
    private java.awt.Label lblErrMngLecH;
    private java.awt.Label lblErrMngTH;
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
    private java.awt.Label lblViewTimeTables;
    private java.awt.Label lblYS;
    private javax.swing.JTextField txtMngEvlH;
    private javax.swing.JTextField txtMngLabH;
    private javax.swing.JTextField txtMngLecH;
    private javax.swing.JTextField txtMngSubCode;
    private javax.swing.JTextField txtMngSubName;
    private javax.swing.JTextField txtMngTuteH;
    private javax.swing.JTable viewSubTbl;
    // End of variables declaration//GEN-END:variables
}
