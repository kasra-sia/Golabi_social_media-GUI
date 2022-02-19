package golabi.event;

public class CreatGroupEvent {
    private int ownerID;
    private String groupName;

    public CreatGroupEvent(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
