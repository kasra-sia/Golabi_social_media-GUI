package golabi.event;

import java.util.Date;
import java.util.EventObject;

public class RegistrationFormEvent extends EventObject {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password1;
    private String password2;
    private String phoneNumber;
    private Date birthday;
    private String bio;
    private String imagePath;

    public RegistrationFormEvent(Object source, String firstname,String lastname,String username, String email, String password1, String password2,String phoneNumber,Date birthday,String bio) {
        super(source);
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.bio = bio;

    }

    public String getFirstName() {
        return firstname;
    }

    public void setName(String name) {
        this.lastname = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBio() {
        return bio;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}