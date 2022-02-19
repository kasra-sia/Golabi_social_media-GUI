package golabi.event;

import java.util.EventObject;

public class SearchUserEvent extends EventObject {
    private String username;

    public SearchUserEvent(Object source,String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
