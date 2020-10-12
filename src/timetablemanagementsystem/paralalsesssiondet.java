package fireAlarmSensor;

import FireAlarmModels.AllSensorDetails;
import FireAlarmModels.Sensors;
import FireAlarmModels.DesktopClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.json.simple.JSONArray;

/**
 *
 * @author DELL
 */

/************Fire alarm interface*****************/
public interface FireAlarm extends Remote {
    
    /******abstract methods which are implemented in server.java class******/
    DesktopClient getAllUsers(DesktopClient user) throws RemoteException;
    List<DesktopClient> allUserDetails() throws RemoteException;

    Sensors getAllSensor(Sensors sensor)throws RemoteException;
    List<Sensors> allSensorDetails() throws RemoteException;
    
    List<AllSensorDetails> allRetrieveDetails() throws RemoteException;
    public String addSensors(String floorNo,String roomNo) throws RemoteException;

}
