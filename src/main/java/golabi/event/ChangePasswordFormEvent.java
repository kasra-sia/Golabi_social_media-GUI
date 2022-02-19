package golabi.event;

import jdk.jfr.Event;

import java.util.EventObject;

public class ChangePasswordFormEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    private String oldPassword;
    private String newPassword;
    public ChangePasswordFormEvent(Object source, String oldPassword, String newPassword) {
        super(source);
       this.newPassword = newPassword;
       this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
