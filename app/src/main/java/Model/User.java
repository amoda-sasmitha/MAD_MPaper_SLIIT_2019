package Model;

import java.io.Serializable;

public class User implements Serializable {

    private int ID;
    private String Username;
    private String Password;
    private String DOB;
    private String Gender;

    public User(int ID, String username, String password, String DOB, String gender) {
        this.ID = ID;
        Username = username;
        Password = password;
        this.DOB = DOB;
        Gender = gender;
    }

    public User() {
    }

    public User(String username, String password, String DOB, String gender) {
        Username = username;
        Password = password;
        this.DOB = DOB;
        Gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
