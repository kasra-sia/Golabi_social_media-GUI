package golabi.event;

import java.util.EventObject;

public class LoginFormEvent extends EventObject {
    private String username;
    private String password;
    public LoginFormEvent(Object source,String username,String password) {
        super(source);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
