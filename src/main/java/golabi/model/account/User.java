package golabi.model.account;
import golabi.model.Model;

import java.time.LocalDateTime;

public class User extends Model {
    private String username;
    private String password;
    private Profile profile;
    public User(String username, String password, Profile profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.creatAt = LocalDateTime.now();
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return this.ID;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}

