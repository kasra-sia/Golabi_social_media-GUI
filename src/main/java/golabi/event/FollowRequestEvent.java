package golabi.event;

import java.util.EventObject;

public class FollowRequestEvent extends EventObject {
    private final String username;
    private final Boolean accepted;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FollowRequestEvent(Object source,String username,Boolean accepted) {
        super(source);
        this.username = username;
        this.accepted = accepted;
    }

    public String getUsername() {
        return username;
    }

    public Boolean isAccepted() {
        return accepted;
    }
}
