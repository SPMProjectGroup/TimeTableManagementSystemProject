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

public class SubGroupLocationMng extends javax.swing.JFrame {

    /**
     * Creates new form SubGroupLocationMng
     */
    public SubGroupLocationMng() {
        initComponents();
        getBuildingDetails();
        DisplaySubGroupNo();
        Show_RoomForSubGroup_In_JTable();
        
        jTable_ViewSubGroupDetails.setRowHeight(40);
        jTable_ViewSubGroupLocations.setRowHeight(40);
        
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
    
    
      /*----------------------- Handle Sub Group Details table ------------------------*/
    
    //retrive all lecturers details
    public ArrayList<SubGroupNo> getSubGroupNoList(){
         ArrayList<SubGroupNo> subgroupNoList = new ArrayList<SubGroupNo>();
         Connection connection = getConnection();
         String query = "SELECT * from studentsubgroups";
         Statement statement;
         ResultSet res;
         
         try{
             statement = connection.createStatement();
             res = statement.executeQuery(query);
             
             SubGroupNo subgroupNo;
             while(res.next()){
                 subgroupNo = new SubGroupNo(res.getInt("SubGroupNoId"),res.getInt("SubGroupNo"));
                 subgroupNoList.add(subgroupNo);
                 
             }
         }catch(Exception e){
             e.printStackTrace();
         }
         return subgroupNoList;
    }
    
     //Display Display Sub Group data in Jtable
    public void DisplaySubGroupNo(){
        
        ArrayList<SubGroupNo> subGrpNoList = getSubGroupNoList();
        DefaultTableModel model = (DefaultTableModel)jTable_ViewSubGroupDetails.getModel();
        Object[] row = new Object[2];
        for(int i = 0; i < subGrpNoList.size(); i++){
            row[0] = subGrpNoList.get(i).getId();
            row[1] = subGrpNoList.get(i).getSubGroupNo();
            
            model.addRow(row);  
        }
    }
    
     //Execute sql queries.
    public void executeSqlQuery(String query, String message){
        
        Connection con = getConnection();
        Statement statement;
        
        try{
            statement = con.createStatement();
             //refresh jtable data
                DefaultTableModel model = (DefaultTableModel)jTable_ViewSubGroupDetails.getModel();
                model.setRowCount(0);
                DisplaySubGroupNo();
            /*   
            if((statement.executeUpdate(query)) == 1){
                
                //refresh jtable data
                DefaultTableModel model = (DefaultTableModel)jTable_ViewGroupDetails.getModel();
                model.setRowCount(0);
                DisplayGroupNo();
                
                JOptionPane.showMessageDialog(null, message+" successfully");
            }else{
                 JOptionPane.showMessageDialog(null, "Not "+message); 
            }*/
        }catch(Exception ex){
                ex.printStackTrace();
        }
    }
    
    
    
    
     //Get building to dropdown list from the location table 
    
    public void getBuildingDetails(){
          Connection con = getConnection();
          Statement statement;
          ResultSet resBuilding;
          
          String getBuildingQuery = "SELECT DISTINCT BuildingName from location";
          try{
              statement = con.createStatement();
              resBuilding = statement.executeQuery(getBuildingQuery);
              
              while(resBuilding.next()){
                  jComboBox_BuildingName.addItem(resBuilding.getString("BuildingName"));
              }
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    
    
    
      /*----------------------- Handle rooms for Student sub groups table -------------------------------*/
    
 
    //Execute the sql query for Rooms for student table
    public void  executeSQLQuery(String query, String message)
    {
        Connection con = getConnection();
        Statement st;
        try{
            st = con.createStatement();
            
         
            if((st.executeUpdate(query)) == 1)
            {
                
                //refreash jtable data
                DefaultTableModel model = (DefaultTableModel)jTable_ViewSubGroupLocations.getModel();
                model.setRowCount(0);
                Show_RoomForSubGroup_In_JTable();
                
                JOptionPane.showMessageDialog(null, "Room for selected Student Sub Group "+message+" Successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Room for selected Student Sub Group Not"+message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<SubGroupLocation> getSubGroupLocationList()
    {
      ArrayList<SubGroupLocation> subGroupLocationList = new ArrayList<SubGroupLocation>();
      Connection connection  = getConnection();
      
      String query = "SELECT * FROM `roomsforstudentsubgroups`";
      Statement st;
      ResultSet rs;
      
     try{
         st = connection.createStatement();
         rs = st.executeQuery(query);
         SubGroupLocation subGroupLocation;
         while(rs.next())
         {
           subGroupLocation = new SubGroupLocation(rs.getInt("SubGroupLocationId"), rs.getInt("SubGroupId"), rs.getString("BuildingName"));  
           subGroupLocationList.add(subGroupLocation);
           
         }
     } catch(Exception e){
        e.printStackTrace();
     }
     return subGroupLocationList;
    }
     
    
     public void Show_RoomForSubGroup_In_JTable(){
      
        ArrayList<SubGroupLocation> list = getSubGroupLocationList();
        DefaultTableModel submodel = (DefaultTableModel)jTable_ViewSubGroupLocations.getModel();
        Object[] row = new Object[3];
        for(int  i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getSubGroupLocationId();
            row[1] = list.get(i).getSubGroupId();
            row[2] = list.get(i).getBuildingName();
            
            submodel.addRow(row);
            
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
        jPanel4 = new javax.swing.JPanel();
        lblDayHour1 = new java.awt.Label();
        lblLec1 = new java.awt.Label();
        lblSub1 = new java.awt.Label();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        lblPrgm1 = new java.awt.Label();
        lblGrp1 = new java.awt.Label();
        lblHome1 = new java.awt.Label();
        lblYS1 = new java.awt.Label();
        jLblStatistics1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_SubGroupId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_BuildingName = new javax.swing.JComboBox<>();
        jButton_Insert = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_ViewSubGroupDetails = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_SubGroupLocationId = new javax.swing.JTextField();
        jButton_Delete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_ViewSubGroupLocations = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton_back = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

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

        jPanel4.setBackground(new java.awt.Color(100, 149, 237));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        lblDayHour1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDayHour1.setText("Working Daya & Hours |");
        lblDayHour1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDayHour1MouseClicked(evt);
            }
        });

        lblLec1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLec1.setText("Lecturers |");
        lblLec1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLec1MouseClicked(evt);
            }
        });

        lblSub1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSub1.setText("Subjects |");
        lblSub1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub1MouseClicked(evt);
            }
        });

        label6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label6.setText(" Tags |");

        label7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label7.setText("Students |");

        label8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label8.setText("Location |");

        lblPrgm1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPrgm1.setText("Programme |");

        lblGrp1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblGrp1.setText("Student Groups  |");

        lblHome1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHome1.setText("Home |");
        lblHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHome1MouseClicked(evt);
            }
        });

        lblYS1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblYS1.setText("Years & Semesters |");

        jLblStatistics1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLblStatistics1.setText("Statistics");
        jLblStatistics1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblStatistics1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDayHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblLec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lblPrgm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblGrp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLblStatistics1)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLblStatistics1)
                    .addComponent(lblYS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGrp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDayHour1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPrgm1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Manage Buildings for Student Sub Groups");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Assign Buildings for Student Sub Groups");

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sub Group ID  :");

        jTextField_SubGroupId.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Building Name :");

        jButton_Insert.setBackground(new java.awt.Color(0, 51, 204));
        jButton_Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_BuildingName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_SubGroupId, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_SubGroupId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox_BuildingName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButton_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("View Sub Group Details");

        jTable_ViewSubGroupDetails.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_ViewSubGroupDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sub Group ID", "Sub Group No"
            }
        ));
        jTable_ViewSubGroupDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ViewSubGroupDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_ViewSubGroupDetails);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Delete Assigned Buildings");

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Sub Group Location ID :");

        jTextField_SubGroupLocationId.setEditable(false);

        jButton_Delete.setBackground(new java.awt.Color(255, 0, 0));
        jButton_Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Delete.setText("DELETE");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTextField_SubGroupLocationId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_SubGroupLocationId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Delete))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTable_ViewSubGroupLocations.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_ViewSubGroupLocations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SubGroupLocationId", "SubGroupId", "Building Name"
            }
        ));
        jTable_ViewSubGroupLocations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ViewSubGroupLocationsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_ViewSubGroupLocations);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("View Student Sub Group Location Details");

        jButton_back.setBackground(new java.awt.Color(153, 102, 0));
        jButton_back.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_back.setText("Back to Dashboard");
        jButton_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_backActionPerformed(evt);
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
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addGap(118, 118, 118)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(97, 97, 97))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(jLabel5))
                                    .addComponent(jButton_back, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_back, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblDayHour1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHour1MouseClicked

    }//GEN-LAST:event_lblDayHour1MouseClicked

    private void lblLec1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLec1MouseClicked
        // TODO add your handling code here:
        /*LecturerManage lecMng = new LecturerManage();
        lecMng.setSize(2000, 2000);
        lecMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();*/
    }//GEN-LAST:event_lblLec1MouseClicked

    private void lblSub1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub1MouseClicked
        // TODO add your handling code here:
        /*SubjectManage subMng = new SubjectManage();
        subMng.setSize(2000, 2000);
        subMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();*/
    }//GEN-LAST:event_lblSub1MouseClicked

    private void lblHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHome1MouseClicked

        Home home = new Home();
        home.setSize(2000, 2000);
        home.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblHome1MouseClicked

    private void jLblStatistics1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblStatistics1MouseClicked

        StatisticsDashboard stMng = new StatisticsDashboard();
        stMng.setSize(2000, 2000);
        stMng.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLblStatistics1MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertActionPerformed

        String grpId = jTextField_SubGroupId.getText();
        String buildingName = jComboBox_BuildingName.getSelectedItem().toString();
        
        int subgrpIdCount = 0;
        if(grpId.equals("")){
            JOptionPane.showMessageDialog(null, "Sub Group selection is required !!!");   
        }else{
        Connection connection = getConnection();
            String querygrpId = "SELECT COUNT(SubGroupId) from  roomsforstudentsubgroups WHERE SubGroupId = '"+grpId+"'";
            Statement statement;
            ResultSet res;

            try{
                statement = connection.createStatement();
                res = statement.executeQuery(querygrpId);
                res.next();
                subgrpIdCount = res.getInt(1);
            }catch(Exception e){
                e.printStackTrace();
            }
        
            if(subgrpIdCount>0){
                 JOptionPane.showMessageDialog(null, "Sub Group ID " +grpId+ " is already assigned for a building ");   
            }else{
                 
		String addQuery  = "INSERT INTO roomsforstudentsubgroups (SubGroupId , BuildingName ) VALUES ('"+grpId+"','"+buildingName+"')";
                executeSQLQuery(addQuery,"Assigned");
		}
        }
    }//GEN-LAST:event_jButton_InsertActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        String query = "DELETE FROM `roomsforstudentsubgroups` WHERE SubGroupLocationId = "+jTextField_SubGroupLocationId.getText();
        executeSQLQuery(query,"Deleted");
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jTable_ViewSubGroupDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewSubGroupDetailsMouseClicked
        
        int i = jTable_ViewSubGroupDetails.getSelectedRow();
        TableModel model = jTable_ViewSubGroupDetails.getModel();
        jTextField_SubGroupId.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_jTable_ViewSubGroupDetailsMouseClicked

    private void jTable_ViewSubGroupLocationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewSubGroupLocationsMouseClicked
        
        int i = jTable_ViewSubGroupLocations.getSelectedRow();
        TableModel model = jTable_ViewSubGroupLocations.getModel();
        jTextField_SubGroupLocationId.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_jTable_ViewSubGroupLocationsMouseClicked

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
            java.util.logging.Logger.getLogger(SubGroupLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubGroupLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubGroupLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubGroupLocationMng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubGroupLocationMng().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Insert;
    private javax.swing.JButton jButton_back;
    private javax.swing.JComboBox<String> jComboBox_BuildingName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblStatistics1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_ViewSubGroupDetails;
    private javax.swing.JTable jTable_ViewSubGroupLocations;
    private javax.swing.JTextField jTextField_SubGroupId;
    private javax.swing.JTextField jTextField_SubGroupLocationId;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label lblDayHour1;
    private java.awt.Label lblGrp1;
    private java.awt.Label lblHome1;
    private java.awt.Label lblLec1;
    private java.awt.Label lblPrgm1;
    private java.awt.Label lblSub1;
    private java.awt.Label lblYS1;
    // End of variables declaration//GEN-END:variables
}
