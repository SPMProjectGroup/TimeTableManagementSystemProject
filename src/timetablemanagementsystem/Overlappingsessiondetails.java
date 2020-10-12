package FireAlarmModels;

import java.io.Serializable;

/**
 *
 * @author DELL
 */

/*** model class for Desktop Client ***/
public class DesktopClient implements Serializable{

    private int Id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String userType;

    /*** default constructor ***/
    public DesktopClient() {
    }

    /*** override constructor with parameters ***/
    public DesktopClient(int Id, String firstName, String lastName,String email, String userName, String password, String userType) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }
 
    /************************* getters and setters *****************************/
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
