package golabi.model;

public class Request extends Model{
    private int senderID;
    private int receiverID;
    private RequestStatus requestStatus;
    public Request(int senderID, int receiverID) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        requestStatus = RequestStatus.SENT;
    }

    public Request() {
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

}

