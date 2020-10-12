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
public class NotAvailableTimes extends javax.swing.JFrame {

    /**
     * Creates new form NotAvailableTime_Lecturers
     */
    
    int SelectedId;
    
    public NotAvailableTimes() {
        initComponents();
        show_NotAvailableTimes();
        find_NotAvailableTimes();
        NAtime.setRowHeight(30);
        
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
    
     //to retrieve not available times db table data
    
    public ArrayList<NATimes> getDetailsList(){
    
    ArrayList<NATimes> DetailsList= new ArrayList<NATimes>(); 
    Connection connection=getConnection();
    
    String query= "SELECT `RecordID`, `Type`, `NotAvailableFor`, `NotAvailableDay`, `NotAvailableFrom`, `NotAvailableTo` FROM `notavailabletime`  ";
    Statement st;
    ResultSet rs;
    
     try{
     st = connection.createStatement();
     rs= st.executeQuery(query);
     NATimes naTimes;
     while(rs.next()){
     naTimes = new NATimes(rs.getInt("RecordID"),rs.getString("Type"),rs.getString("NotAvailableFor"),rs.getString("NotAvailableDay"),rs.getString("NotAvailableFrom"),rs.getString("NotAvailableTo"));
     DetailsList.add(naTimes);
     }
     }catch(Exception e){
      e.printStackTrace();
     }
      return DetailsList;  
    }
    
    //search the selected records
    public ArrayList<NATimes> searchDetailsList(String valueToSearch){
    
    ArrayList<NATimes> searchedList= new ArrayList<NATimes>(); 
    Connection connection=getConnection();
    
    String searchquery= "SELECT * FROM `notavailabletime` WHERE Concat( Type,NotAvailableFor,NotAvailableDay, NotAvailableFrom, NotAvailableTo )LIKE '%"+ valueToSearch +"%'";
    Statement st;
    ResultSet rs;
    
     try{
     st = connection.createStatement();
     rs= st.executeQuery(searchquery);
     NATimes natimes;
     while(rs.next()){
     natimes = new NATimes(rs.getInt("RecordID"),rs.getString("Type"),rs.getString("NotAvailableFor"),rs.getString("NotAvailableDay"),rs.getString("NotAvailableFrom"),rs.getString("NotAvailableTo"));
     searchedList.add(natimes);
     }
     }catch(Exception e){
      e.printStackTrace();
     }
      return searchedList;  
    }
    
    //Display data in Jtable
    public void show_NotAvailableTimes(){
        
    ArrayList<NATimes> list= getDetailsList();
    DefaultTableModel model=(DefaultTableModel)NAtime.getModel();
    Object[] row=new Object[6];
    
    for(int i=0; i<list.size(); i++){
        row[0]=list.get(i).getNAId();
        row[1]=list.get(i).getNAtype();
        row[2]=list.get(i).getNAFor();
        row[3]=list.get(i).getNADay();
        row[4]=list.get(i).getNAFrom();
        row[5]=list.get(i).getNATo();
   
        model.addRow(row);
    }
    
    }
    
    //display search data in jtable
    public void find_NotAvailableTimes(){
        
    
    ArrayList<NATimes> searchlist= searchDetailsList(search.getText());
    DefaultTableModel model=new DefaultTableModel();
    model.setColumnIdentifiers(new Object[]{"RecordID","Type","NotAvailableFor","NotAvailableDay","NotAvailableFrom","NotAvailableTo"});
    Object[] row=new Object[6];
    
    for(int i=0; i<searchlist.size(); i++){
        row[0]=searchlist.get(i).getNAId();
        row[1]=searchlist.get(i).getNAtype();
        row[2]=searchlist.get(i).getNAFor();
        row[3]=searchlist.get(i).getNADay();
        row[4]=searchlist.get(i).getNAFrom();
        row[5]=searchlist.get(i).getNATo();
   
        model.addRow(row);
    }
      NAtime.setModel(model);
    }
    
    //Execute the sql query
    public void executeSQlQuery(String query,String message){
    
        Connection con= getConnection();
        Statement st;
        
        try{
        st= con.createStatement();
        if((st.executeUpdate(query))==1){
            
            //refresh Jtable data
            DefaultTableModel model=(DefaultTableModel) NAtime.getModel();
            model.setRowCount(0);
            show_NotAvailableTimes();

           JOptionPane.showMessageDialog(null, message+ " Successfully");
        }else{
           JOptionPane.showMessageDialog(null, message+"can Not be done ");
        }
        }catch(Exception e){}
    
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
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Combo_For = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        notAvailableDay_combo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        notAvailableFrom_combo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        notAvailableTo_combo = new javax.swing.JComboBox<>();
        btn_Add = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Combo_Type = new javax.swing.JComboBox<>();
        OK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        NAtime = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        err = new javax.swing.JLabel();
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

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(494, 494, 494))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
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
                .addGap(1, 1, 1)
                .addComponent(lblDayHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblLec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStudents1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTags1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrgrm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStdGrp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Add Not Available Times");

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Not Avaliable Time for");

        Combo_For.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Combo_For.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {" " }));
        Combo_For.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_ForActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Not Available Day");

        notAvailableDay_combo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        notAvailableDay_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday","Wednessday", "Thursday", "Friday","Saturday","Sunday" }));
        notAvailableDay_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notAvailableDay_comboActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Not Available Time : From");

        notAvailableFrom_combo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        notAvailableFrom_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08.00", "08.30", "09.00", "09.30","10.00", "10.30", "11.00", "11.30", "12.00","12.30","13.00","13.30","14.00","14.30","15.00","15.30","16.00","16.30","17.00","17.30","18.00","18.30","19.00","19.30","20.00" }));
        notAvailableFrom_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notAvailableFrom_comboActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("To");

        notAvailableTo_combo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        notAvailableTo_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08.00", "08.30", "09.00", "09.30","10.00", "10.30", "11.00", "11.30","12.00","12.30","13.00","13.30","14.00","14.30","15.00","15.30","16.00","16.30","17.00","17.30","18.00","18.30","19.00","19.30","20.00"}));
        notAvailableTo_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notAvailableTo_comboActionPerformed(evt);
            }
        });

        btn_Add.setBackground(new java.awt.Color(0, 51, 204));
        btn_Add.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Add.setText("ADD");
        btn_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AddMouseClicked(evt);
            }
        });
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("View Not Available Times");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Not Avaliable Type");

        Combo_Type.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Combo_Type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lecturers","Sessions","Groups","Sub Groups"}));
        Combo_Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_TypeActionPerformed(evt);
            }
        });

        OK.setBackground(new java.awt.Color(0, 153, 153));
        OK.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        OK.setText("OK");
        OK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OKMouseClicked(evt);
            }
        });
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        NAtime.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Type", "Not Available For", "Not Available Day", "Not Available From", "Not Available To"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        NAtime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NAtimeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(NAtime);
        if (NAtime.getColumnModel().getColumnCount() > 0) {
            NAtime.getColumnModel().getColumn(0).setPreferredWidth(30);
            NAtime.getColumnModel().getColumn(1).setPreferredWidth(75);
            NAtime.getColumnModel().getColumn(2).setPreferredWidth(200);
            NAtime.getColumnModel().getColumn(3).setPreferredWidth(200);
            NAtime.getColumnModel().getColumn(4).setPreferredWidth(100);
            NAtime.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        search.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btn_search.setBackground(new java.awt.Color(0, 153, 153));
        btn_search.setText("SEARCH");
        btn_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_searchMouseClicked(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(255, 0, 0));
        btn_delete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteMouseClicked(evt);
            }
        });

        err.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        err.setForeground(new java.awt.Color(255, 51, 102));

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
                .addGap(220, 220, 220)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(Combo_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Combo_For, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(OK)
                                            .addGap(83, 83, 83))))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(notAvailableDay_combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(notAvailableFrom_combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(notAvailableTo_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(err, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(24, 24, 24))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Combo_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(OK)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Combo_For, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(notAvailableDay_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(notAvailableFrom_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(notAvailableTo_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(err, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblDayHour1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHour1MouseClicked

        
        Workingdaysandhours wdh = new Workingdaysandhours();
        wdh.setSize(2000, 2000);
        wdh.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
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

    private void Combo_ForActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_ForActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_ForActionPerformed

    private void notAvailableDay_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notAvailableDay_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notAvailableDay_comboActionPerformed

    private void notAvailableFrom_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notAvailableFrom_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notAvailableFrom_comboActionPerformed

    private void notAvailableTo_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notAvailableTo_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notAvailableTo_comboActionPerformed

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
       
        int count=0;
        
        Connection connection=getConnection();
        String Query = "SELECT Count(Concat( Type,NotAvailableFor,NotAvailableDay, NotAvailableFrom, NotAvailableTo )) FROM notavailabletime WHERE Type='"+Combo_Type.getSelectedItem()+"' and NotAvailableFor='"+Combo_For.getSelectedItem()+"' and NotAvailableDay='"+notAvailableDay_combo.getSelectedItem()+"' and NotAvailableFrom='"+notAvailableFrom_combo.getSelectedItem()+"' and NotAvailableTo= '"+notAvailableTo_combo.getSelectedItem()+"'";
        Statement st;
        ResultSet rs;
        
//        String typeFor =Combo_For.getSelectedItem().toString();
//        int From =Integer.parseInt(notAvailableFrom_combo.getSelectedItem().toString());
//        int To =Integer.parseInt(notAvailableTo_combo.getSelectedItem().toString());
//        System.out.println(From);
//        System.out.println(To);   
//         
//        if(typeFor.equals("")){
//            
//           err.setText("Please select a type !!!");
//        }
//        else if(To <= From){
//           err.setText("Please select a suitable end time graeter than starting time !!!");
//        } 
        
        try{

        st= connection.createStatement();
        rs= st.executeQuery(Query);
        rs.next();
        count= rs.getInt(1);
        
        if(count>0){
            
           JOptionPane.showMessageDialog(null,"Record already exists !!!");
        }
        else if(Combo_For.getSelectedItem().equals(" ")){
           JOptionPane.showMessageDialog(null,"Please select a type first !!!");
        }
        else if(notAvailableFrom_combo.getSelectedItem().equals(notAvailableTo_combo.getSelectedItem())){
           JOptionPane.showMessageDialog(null,"Please select a sutable end time !!!");
        }
//        else if(Integer.max(From,To)== From){
//           JOptionPane.showMessageDialog(null,"Please select a value greater than starting time for the end time !!!");
//        }
        else if(Double.parseDouble(notAvailableFrom_combo.getSelectedItem().toString())>=(Double.parseDouble(notAvailableTo_combo.getSelectedItem().toString()))){
        JOptionPane.showMessageDialog(null,"Please select a value greater than starting time for the end time !!!");
        }
        else{
        
        String query = "INSERT INTO `notavailabletime`( `Type`, `NotAvailableFor`,`NotAvailableDay`, `NotAvailableFrom`, `NotAvailableTo`) VALUES ('"+Combo_Type.getSelectedItem()+"','"+Combo_For.getSelectedItem()+"','"+notAvailableDay_combo.getSelectedItem()+"','"+notAvailableFrom_combo.getSelectedItem()+"','"+notAvailableTo_combo.getSelectedItem()+"')";
        executeSQlQuery(query, "Record Inserted");
        
        }
        }catch(Exception e){
        e.printStackTrace();
        }
        
   
    }//GEN-LAST:event_btn_AddActionPerformed

    private void btn_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AddMouseClicked
         
    }//GEN-LAST:event_btn_AddMouseClicked

    private void Combo_TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_TypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_TypeActionPerformed

    private void OKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OKMouseClicked
        Connection con= getConnection();
        Statement pst1,pst2,pst3,pst4;
        ResultSet rs1,rs2,rs3,rs4;
        String type=Combo_Type.getSelectedItem().toString();
       
    try{
 
       
        String qry1="SELECT  `LecturerName`,`Rank` FROM `lecturers`";
        String qry2="SELECT `SessionId` FROM `sessions` ";
        String qry3="SELECT  DISTINCT(`GroupId`) FROM `students`";
        String qry4="SELECT  `SubGroupId` FROM `students`";
        
        Combo_For.removeAllItems();
        
      if(type.equals("Lecturers")){
      
      pst1=con.prepareStatement(qry1);
      rs1=pst1.executeQuery(qry1);
      
      while(rs1.next()){
      Combo_For.addItem(rs1.getString("LecturerName")+"  -  "+rs1.getString("Rank"));
    }
    } 
    else if(type.equals("Sessions")){
      
      pst2=con.prepareStatement(qry2);
      rs2=pst2.executeQuery(qry2);

      while(rs2.next()){
      Combo_For.addItem(rs2.getString("SessionId"));
    }
    }
     else if(type.equals("Groups")){
     
      pst3=con.prepareStatement(qry3);
      rs3=pst3.executeQuery(qry3);

      while(rs3.next()){
      Combo_For.addItem(rs3.getString("GroupId"));
    }
    }
    else if(type.equals("Sub Groups")){
      
      pst4=con.prepareStatement(qry4);
      rs4=pst4.executeQuery(qry4);
 
      while(rs4.next()){
      Combo_For.addItem(rs4.getString("SubGroupId"));
    }
    }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    
    }//GEN-LAST:event_OKMouseClicked

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OKActionPerformed

    private void NAtimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NAtimeMouseClicked
        //Display selected row in input fields
    
    int i= NAtime.getSelectedRow();
    TableModel model= NAtime.getModel();
//    ID.setText(model.getValueAt(i,0).toString());
    Combo_Type.setSelectedItem(model.getValueAt(i,1).toString());
    Combo_For.setSelectedItem(model.getValueAt(i,2).toString());
    notAvailableDay_combo.setSelectedItem(model.getValueAt(i,3).toString());
    notAvailableFrom_combo.setSelectedItem(model.getValueAt(i,4).toString());
    notAvailableTo_combo.setSelectedItem(model.getValueAt(i,5).toString());
    
      SelectedId = (Integer) model.getValueAt(i,0);
    }//GEN-LAST:event_NAtimeMouseClicked

    private void btn_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseClicked
       find_NotAvailableTimes();
    }//GEN-LAST:event_btn_searchMouseClicked

    private void btn_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseClicked
        String query="DELETE FROM `notavailabletime` WHERE RecordID = "+SelectedId;
        executeSQlQuery(query, "Deleted");
    }//GEN-LAST:event_btn_deleteMouseClicked

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
            java.util.logging.Logger.getLogger(NotAvailableTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotAvailableTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotAvailableTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotAvailableTimes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotAvailableTimes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_For;
    private javax.swing.JComboBox<String> Combo_Type;
    private javax.swing.JTable NAtime;
    private javax.swing.JButton OK;
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel err;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lblBotOverlappingSession;
    private java.awt.Label lblConSession;
    private java.awt.Label lblDayHour1;
    private java.awt.Label lblGenTimeTables;
    private java.awt.Label lblHome1;
    private java.awt.Label lblLec1;
    private java.awt.Label lblLocation1;
    private java.awt.Label lblNotAvTime;
    private java.awt.Label lblParallelSe;
    private java.awt.Label lblPrgrm1;
    private java.awt.Label lblRoomAllocate;
    private java.awt.Label lblSession;
    private java.awt.Label lblStat1;
    private java.awt.Label lblStdGrp1;
    private java.awt.Label lblStudents1;
    private java.awt.Label lblSub1;
    private java.awt.Label lblTags1;
    private java.awt.Label lblViewTimeTables;
    private java.awt.Label lblYS1;
    private javax.swing.JComboBox<String> notAvailableDay_combo;
    private javax.swing.JComboBox<String> notAvailableFrom_combo;
    private javax.swing.JComboBox<String> notAvailableTo_combo;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
