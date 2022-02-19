package golabi.event;

import java.util.EventObject;

public class NewMessageEvent extends EventObject {
    private String text;
    private String imageFilePath;
    private int chatRoomID;
    public NewMessageEvent(Object source, String text, String imageFilePath, int chatRoomID) {
        super(source);
        this.text = text;
        this.imageFilePath = imageFilePath;
        this.chatRoomID = chatRoomID;
    }
    public String getText(){
        return text;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public int getChatRoomID() {
        return chatRoomID;
    }
}
