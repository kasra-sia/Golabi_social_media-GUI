package golabi.event;

import java.util.EventObject;

public class EditMessageEvent extends EventObject {

    private int id;
    private String message;

    public EditMessageEvent(Object source, int id , String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
