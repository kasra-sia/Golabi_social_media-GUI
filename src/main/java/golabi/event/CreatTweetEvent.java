package golabi.event;

import java.util.EventObject;

public class CreatTweetEvent extends EventObject {
    private String text;
    private String imageFilePath;

    public CreatTweetEvent(Object source, String text, String imageFilePath) {
        super(source);
        this.text = text;
        this.imageFilePath = imageFilePath;
    }
    public String getText(){
        return text;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setText(String text) {
        this.text = text;
    }
}
