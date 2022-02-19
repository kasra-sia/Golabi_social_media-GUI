package golabi.model.chat;

import java.util.LinkedList;

public class GroupChat extends ChatAbstractClass {
    private LinkedList<Integer> usersID = new LinkedList<>();
    private String gpName;

    public GroupChat() {
    }

    public GroupChat(int ownerID, String gpName) {
        usersID.add(ownerID);
        this.gpName = gpName;
        isGroup = true;
    }

    public LinkedList<Integer> getUsersID() {
        return usersID;
    }

    public void setUsersID(LinkedList<Integer> usersID) {
        this.usersID = usersID;
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

}
