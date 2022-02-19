package golabi.model.chat;

import golabi.CurrentTimeToString;
import golabi.model.Model;

import java.util.LinkedList;

public class Message extends Model {
    private String text;
    private int senderID;
    private String time;
    private LinkedList<Integer> usersIDSeen = new LinkedList<>();
    private int chatRoomID;

    public Message() {
    }

    public Message(String text, int senderID, int chatRoomID) {
        this.text = text;
        this.senderID = senderID;
        this.chatRoomID = chatRoomID;
        time = CurrentTimeToString.get();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID){
        this.senderID = senderID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getChatRoomID() {
        return chatRoomID;
    }

    public void setChatRoomID(int chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public LinkedList<Integer> getUsersIDSeen() {
        return usersIDSeen;
    }

    public void setUsersIDSeen(LinkedList<Integer> usersIDSeen) {
        this.usersIDSeen = usersIDSeen;
    }

}
