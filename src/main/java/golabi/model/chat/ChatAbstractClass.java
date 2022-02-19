package golabi.model.chat;

import golabi.model.Model;
import java.util.LinkedList;

public abstract class ChatAbstractClass extends Model {
    protected boolean isGroup;

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }
}
