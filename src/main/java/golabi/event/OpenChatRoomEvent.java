package golabi.event;

import java.util.EventObject;

public class OpenChatRoomEvent extends EventObject {
    private final boolean isGroup;
    private final String name;
    private final int chatroomID;

    public OpenChatRoomEvent(Object source, boolean isGroup, String name,int chatroomID) {
        super(source);
        this.isGroup = isGroup;
        this.name = name;
        this.chatroomID = chatroomID;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public String getName() {
        return name;
    }

    public int getChatroomID() {
        return chatroomID;
    }
}
