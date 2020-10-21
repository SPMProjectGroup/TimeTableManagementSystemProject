/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class generateTimetable extends javax.swing.JFrame {

    /**
     * Creates new form Locations
     */
    public generateTimetable() {
        initComponents();

        getGroupNamesForSet();
        getLecturerNamesForFilterSession();
        getRoomNameDetails();

    }

    public Connection getConnection() {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetablemanagementsystem", "root", "");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getRoomNameDetails() {
        Connection con = getConnection();
        Statement statement;
        ResultSet resBuilding;

        String getBuildingQuery = "SELECT DISTINCT RoomName from location";
        try {
            statement = con.createStatement();
            resBuilding = statement.executeQuery(getBuildingQuery);

            while (resBuilding.next()) {
                roomSlct.addItem(resBuilding.getString("RoomName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLecturerNamesForFilterSession() {
        Connection con = getConnection();
        Statement statement;
        ResultSet resLec;

        String getLecturerQuery = "SELECT LecturerName,Title from lecturers";
        try {
            statement = con.createStatement();
            resLec = statement.executeQuery(getLecturerQuery);

            while (resLec.next()) {
                lctrSelect.addItem(resLec.getString("Title") + "." + resLec.getString("LecturerName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGroupNamesForSet() {
        Connection con = getConnection();
        Statement statement;
        ResultSet resLec;

        String getLecturerQuery = "SELECT SubGroupId from students";
        try {
            statement = con.createStatement();
            resLec = statement.executeQuery(getLecturerQuery);

            while (resLec.next()) {
                groupSlct.addItem(resLec.getString("SubGroupId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Location> getLocationsList() {
        ArrayList<Location> locationsList = new ArrayList<Location>();
        Connection connection = getConnection();

        String query = "SELECT * FROM `location`";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Location location;
            while (rs.next()) {
                location = new Location(rs.getInt("LocationID"), rs.getString("BuildingName"), rs.getString("RoomName"), rs.getString("RoomType"));
                locationsList.add(location);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationsList;
    }

//Display location data in JTable
    public void lecTable(String lec) {

        DefaultTableModel model = (DefaultTableModel) timeTable.getModel();
        model.getDataVector().removeAllElements();
        Object[] row = new Object[8];

        Connection con = getConnection();
        Statement statement;
        ResultSet res;

        try {
            String query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=8 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "08.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=8 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "08.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=9 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "09.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=9 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "09.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=10 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "10.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5]
                    = row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=10 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "10.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=11 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "11.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=11 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "11.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=12 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "12.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=12 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "12.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=13 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "13.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=13 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "13.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=14 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "14.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=14 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "14.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=15 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "15.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=15 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "15.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=16 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "16.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=16 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "16.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=17 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "17.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=17 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "17.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=18 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "18.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=18 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "18.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=19 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "19.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=19 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "19.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=20 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "20.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where lecturer='" + lec + "' and str_hours=20 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "20.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void roomTable(String room) {

        DefaultTableModel model = (DefaultTableModel) timeTable.getModel();
        model.getDataVector().removeAllElements();
        Object[] row = new Object[8];

        Connection con = getConnection();
        Statement statement;
        ResultSet res;

        try {
            String query = "SELECT * from notavailable where room='" + room + "' and str_hours=8 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "08.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=8 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "08.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=9 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "09.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=9 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "09.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=10 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "10.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5]
                    = row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=10 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "10.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=11 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "11.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=11 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "11.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=12 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "12.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=12 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "12.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=13 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "13.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=13 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "13.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=14 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "14.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=14 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "14.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=15 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "15.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=15 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "15.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=16 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "16.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=16 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "16.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=17 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "17.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=17 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "17.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=18 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "18.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=18 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "18.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=19 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "19.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=19 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "19.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=20 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "20.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where room='" + room + "' and str_hours=20 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "20.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void groupTable(String grp) {

        DefaultTableModel model = (DefaultTableModel) timeTable.getModel();
        model.getDataVector().removeAllElements();
        Object[] row = new Object[8];

        Connection con = getConnection();
        Statement statement;
        ResultSet res;

        try {
            String query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=8 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "08.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=8 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "08.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=9 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "09.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=9 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "09.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=10 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "10.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5]
                    = row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=10 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "10.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=11 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "11.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=11 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "11.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=12 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "12.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=12 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "12.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=13 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "13.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=13 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "13.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=14 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "14.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=14 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "14.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=15 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "15.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=15 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "15.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=16 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "16.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=16 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "16.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=17 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "17.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=17 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "17.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=18 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "18.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=18 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "18.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=19 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "19.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=19 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "19.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=20 and str_min=00";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "20.00";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

            /*------------------------------------------------------------*/
            query = "SELECT * from notavailable where groupName='" + grp + "' and str_hours=20 and str_min=30";
            statement = con.createStatement();
            res = statement.executeQuery(query);

            row[0] = "20.30";
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";
            row[7] = "";

            while (res.next()) {
                if (res.getString("day").equals("Monday")) {
                    row[1] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Tuesday")) {
                    row[2] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Wednesday")) {
                    row[3] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Thursday")) {
                    row[4] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Friday")) {
                    row[5] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Saturday")) {
                    row[6] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                } else if (res.getString("day").equals("Sunday")) {
                    row[7] = res.getString("groupName") + " " + res.getString("session") + " " + res.getString("room");
                }
            }

            model.addRow(row);

        } catch (Exception e) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        timeTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
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
        jButton1 = new javax.swing.JButton();
        lctrSelect = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        roomSlct = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        groupSlct = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblConSession1 = new java.awt.Label();
        lblRoomAllocate1 = new java.awt.Label();
        lblSession1 = new java.awt.Label();
        lblParallelSe1 = new java.awt.Label();
        lblBotOverlappingSession1 = new java.awt.Label();
        lblNotAvTime1 = new java.awt.Label();
        lblGenTimeTables1 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        timeTable.setBackground(new java.awt.Color(153, 153, 255));
        timeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
            }
        ));
        timeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(timeTable);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Generate Timetable");

        jPanel4.setBackground(new java.awt.Color(0, 0, 153));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("TIME TABLE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(613, 613, 613)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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
                .addContainerGap(20, Short.MAX_VALUE))
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

        jButton1.setText("Save PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lctrSelect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lctrSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Lecturer" }));
        lctrSelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lctrSelectItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Lecturer");

        roomSlct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        roomSlct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Room" }));
        roomSlct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                roomSlctItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Room");

        groupSlct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        groupSlct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Group" }));
        groupSlct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupSlctItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Group");

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
        lblGenTimeTables1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGenTimeTables1MouseClicked(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(621, 621, 621))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                    .addComponent(lctrSelect, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(152, 152, 152)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roomSlct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(groupSlct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(552, 552, 552)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lctrSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(roomSlct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(groupSlct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void timeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTableMouseClicked

    }//GEN-LAST:event_timeTableMouseClicked

    private void lblDayHourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDayHourMouseClicked

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
        generateTimetable locationDetails = new generateTimetable();
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(!(timeTable.getRowCount()==0)){
            Document doc= new Document();

            try{
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
                Calendar cal = Calendar.getInstance();
                String name=dateFormat.format(cal.getTime());
                
                PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream("pdf\\"+name+".pdf"));
                doc.open();

                Paragraph paragraph= new Paragraph();
                
                String table_name="";
                
                if(roomSlct.getSelectedIndex()!=0){
                    table_name=roomSlct.getSelectedItem().toString();
                }else if(groupSlct.getSelectedIndex()!=0){
                    table_name=groupSlct.getSelectedItem().toString();
                }else if(lctrSelect.getSelectedIndex()!=0){
                    table_name=lctrSelect.getSelectedItem().toString();
                }

                paragraph.setAlignment(Element.ALIGN_CENTER);
                paragraph.add("Timetable "+table_name);
                doc.add(paragraph);
                paragraph.clear();
                
                doc.add(new Paragraph(" "));
                
                PdfPTable table = new PdfPTable(8);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                float[] colWidth={1f,2f,2f,2f,2f,2f,2f,2f};
                table.setWidths(colWidth);
                PdfPCell c1= new PdfPCell(new Paragraph("Time"));
                PdfPCell c2= new PdfPCell(new Paragraph("Monday"));
                PdfPCell c3= new PdfPCell(new Paragraph("Tuesday"));
                PdfPCell c4= new PdfPCell(new Paragraph("Wednesday"));
                PdfPCell c5= new PdfPCell(new Paragraph("Thursday"));
                PdfPCell c6= new PdfPCell(new Paragraph("Friday"));
                PdfPCell c7= new PdfPCell(new Paragraph("Saturday"));
                PdfPCell c8= new PdfPCell(new Paragraph("Sunday"));

                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);
                table.addCell(c5);
                table.addCell(c6);
                table.addCell(c7);
                table.addCell(c8);

                for(int i=0;i<timeTable.getRowCount();i++){

                    PdfPCell p1= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 0).toString()));
                    PdfPCell p2= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 1).toString()));
                    PdfPCell p3= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 2).toString()));
                    PdfPCell p4= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 3).toString()));
                    PdfPCell p5= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 4).toString()));
                    PdfPCell p6= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 5).toString()));
                    PdfPCell p7= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 6).toString()));
                    PdfPCell p8= new PdfPCell(new Paragraph(timeTable.getModel().getValueAt(i, 7).toString()));

                    table.addCell(p1);
                    table.addCell(p2);
                    table.addCell(p3);
                    table.addCell(p4);
                    table.addCell(p5);
                    table.addCell(p6);
                    table.addCell(p7);
                    table.addCell(p8);

                }

                doc.add(table);
                
                doc.close();
                writer.close();

                JOptionPane.showMessageDialog(null, "Success!! PDF Generated Succssful!!");
                
            } catch (FileNotFoundException ex) {
                
            } catch (DocumentException ex) {
                
            }
        }else{
            JOptionPane.showMessageDialog(null, "Warning!! No Records!!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lctrSelectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lctrSelectItemStateChanged
        roomSlct.setSelectedIndex(0);
        groupSlct.setSelectedIndex(0);
        lecTable(lctrSelect.getSelectedItem().toString());
    }//GEN-LAST:event_lctrSelectItemStateChanged

    private void roomSlctItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_roomSlctItemStateChanged
        groupSlct.setSelectedIndex(0);
        lctrSelect.setSelectedIndex(0);
        roomTable(roomSlct.getSelectedItem().toString());
    }//GEN-LAST:event_roomSlctItemStateChanged

    private void groupSlctItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupSlctItemStateChanged
        roomSlct.setSelectedIndex(0);
        lctrSelect.setSelectedIndex(0);
        groupTable(groupSlct.getSelectedItem().toString());
    }//GEN-LAST:event_groupSlctItemStateChanged

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

    private void lblGenTimeTables1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenTimeTables1MouseClicked
        // TODO add your handling code here:

        generateTimetable gt = new generateTimetable();
        gt.setSize(2000, 2000);
        gt.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblGenTimeTables1MouseClicked

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
            java.util.logging.Logger.getLogger(generateTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(generateTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(generateTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(generateTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new generateTimetable().setVisible(true);
            }
        });

        JFrame.setDefaultLookAndFeelDecorated(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> groupSlct;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lblBotOverlappingSession1;
    private java.awt.Label lblConSession1;
    private java.awt.Label lblDayHour;
    private java.awt.Label lblGenTimeTables1;
    private java.awt.Label lblHome;
    private java.awt.Label lblLec;
    private java.awt.Label lblLocation;
    private java.awt.Label lblNotAvTime1;
    private java.awt.Label lblParallelSe1;
    private java.awt.Label lblPrgrm;
    private java.awt.Label lblRoomAllocate1;
    private java.awt.Label lblSession1;
    private java.awt.Label lblStat;
    private java.awt.Label lblStdGrp;
    private java.awt.Label lblStudents;
    private java.awt.Label lblSub;
    private java.awt.Label lblTags;
    private java.awt.Label lblYS;
    private javax.swing.JComboBox<String> lctrSelect;
    private javax.swing.JComboBox<String> roomSlct;
    private javax.swing.JTable timeTable;
    // End of variables declaration//GEN-END:variables
}
