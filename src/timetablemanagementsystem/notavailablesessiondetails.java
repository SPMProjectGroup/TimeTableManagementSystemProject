package fireAlarmSensor;

import FireAlarmModels.AllSensorDetails;
import FireAlarmModels.Sensors;
import FireAlarmModels.DesktopClient;
import EmailService.Json;
import fireAlarmSensor.FireAlarm;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import org.json.simple.parser.ParseException;

/**
 *
 * @author DELL
 */
/**
 * * RMI server to call APIs to create connection between desktop client and
 * rest API **
 */
public class Server extends UnicastRemoteObject implements FireAlarm {

    private List<DesktopClient> usersList;
    private List<Sensors> sensorsList;
    private List<AllSensorDetails> fetchAllSensorDetailsList;
    //private List<JSONArray> jsonArrayList;

    public Server() throws RemoteException {
        super();
        StartInitialize();
    }

    private void StartInitialize() {
        usersList = new ArrayList<>();      //create list for store users
        sensorsList = new ArrayList<>();     //create list for store sensor details.
        fetchAllSensorDetailsList = new ArrayList<>(); //create list for all sensor details
        //jsonArrayList = new ArrayList<>();
//        ServerUpdate(); //to call the ServerUpdate() method to refresh the data in every 30 seconds.     
    }

    @Override
    public DesktopClient getAllUsers(DesktopClient user) throws RemoteException {

        Predicate<DesktopClient> predicate = x -> x.getId() == (user.getId());
        return usersList.stream().filter(predicate).findFirst().get();
    }

    /**
     * * to get the userList **
     */
    @Override
    public List<DesktopClient> allUserDetails() {
        return usersList;
    }

    @Override
    public Sensors getAllSensor(Sensors sensor) throws RemoteException {
        Predicate<Sensors> predicate = x -> x.getSensorId() == sensor.getSensorId();
        return sensorsList.stream().filter(predicate).findFirst().get();
    }

    /**
     * * to get the sensorsList **
     */
    @Override
    public List<Sensors> allSensorDetails() {
        return sensorsList;
    }

    /**
     * * to get the fetchAllSensorDetailsList **
     */
    @Override
    public List<AllSensorDetails> allRetrieveDetails() {
        try {
            retriveAllSensors();
            return fetchAllSensorDetailsList;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List<JSONArray> allRetrieveSensorDetails() {
//         return jsonArrayList;
//    }
//    
    /**
     * * to refresh data in every 30 seconds **
     */
    //for add registered sensors.
    @Override
    public String addSensors(String floorNo, String roomNo) throws RemoteException {
        try {

            //System.out.println("Floor :"+floorNo+ "room :"+roomNo);
            URL url = new URL("http://localhost:8080/firealarmrest/webapi/sensor/");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; //tempory return value.
    }

    //for update registered sensors by Admin.
    public void updateSensorInfobyAdmin(int id) {
        String updateSensor = new Json().Request("http://localhost:8080/firealarmrest/webapi/sensor/id");
    }

    //for delete registered sensors by Admin.
    public void deleteRegisteredSensor(int id) {
        String deleteSensor = new Json().Request("http://localhost:8080/firealarmrest/webapi/sensor/id");
    }

    //for update the randomly selected sensor details according to the co2 level and smoke level which is randomly generated by sensor app
    public void updateSensorInfobySensorApp() {
        String refreshSensor = new Json().Request("http://localhost:8080/firealarmrest/webapi/sensor/id");
    }

    //for add users into the system
    public String addUsers(String fName, String lName, String email, String uName, String pw) {
        String newUser = new Json().Request("http://localhost:8080/firealarmrest/webapi/user");

        try {
            URL url = new URL("http://localhost:8080/firealarmrest/webapi/user");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newUser;
    }

    //retrieve all the sensor details from database through APIs calls by JSON.
    public void retriveAllSensors() throws ParseException {

        /**
         * *****************************AllSensorDetails JSON
         * ******************************************
         */
        String AllRetrieveSensorDetails = new Json().Request("http://localhost:8080/firealarmrest/webapi/sensor");

        //System.out.println(AllRetrieveSensorDetails);
        if (AllRetrieveSensorDetails != null) {
            try {
                System.out.println("******** Get All Sensor Details ********");
                JSONParser Parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) Parser.parse(AllRetrieveSensorDetails);

                //System.out.println("Before clear:"+fetchAllSensorDetailsList);
                fetchAllSensorDetailsList.clear();

                for (Object object : jsonArray) {
                    JSONObject jsonObject = (JSONObject) object;
                    
                    AllSensorDetails sensor = new AllSensorDetails(
                            Integer.parseInt(jsonObject.get("sensorId").toString()),
                            jsonObject.get("floorNo").toString(),
                            jsonObject.get("roomNo").toString(),
                            Integer.parseInt(jsonObject.get("co2Level").toString()),
                            Integer.parseInt(jsonObject.get("smokeLevel").toString())
                            //Boolean.parseBoolean(jsonObject.get("sensorstatus").toString())    
                    );
                    
                    fetchAllSensorDetailsList.add(sensor);

                    for (AllSensorDetails sensorTest : fetchAllSensorDetailsList) {
                        System.out.println(sensorTest.getId());
                        System.out.println(sensorTest.getFloorNo());
                        System.out.println(sensorTest.getRoomNo());
                        System.out.println(sensorTest.getCo2Level());
                        System.out.println(sensorTest.getSmokeLevel());
                        System.out.println("---------------------------------");
                        //System.out.println(sensor.isSensorStatus());
                    }
                }

                System.out.println("Server List :" + fetchAllSensorDetailsList.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

//    public static void main(String[] args) throws RemoteException {
//        try {
//            Registry registry = LocateRegistry.createRegistry(9999);
//            registry.rebind("Success", new Server());
//            System.out.println("Server is running");
//
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//
//    }
}
