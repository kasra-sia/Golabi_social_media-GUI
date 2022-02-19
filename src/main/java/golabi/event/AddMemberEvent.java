package golabi.event;

import java.util.EventObject;

public class AddMemberEvent extends EventObject {
    private int userID;
    private int groupID;
    public AddMemberEvent(Object source, int userID, int groupID) {
        super(source);
        this.userID = userID;
        this.groupID = groupID;
    }

    public int getUserID() {
        return userID;
    }

    public int getGroupID() {
        return groupID;
    }
}
