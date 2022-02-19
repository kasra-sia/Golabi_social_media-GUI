package golabi.event;

import java.util.EventObject;

public class CreatCommentEvent extends EventObject {
    String text;
    public CreatCommentEvent(Object source,String text) {
        super(source);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
