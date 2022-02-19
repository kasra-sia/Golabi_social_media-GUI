package golabi.model;

import golabi.CurrentTimeToString;

public class Notification {
    private String text;
    private String time;

    public Notification() {
    }

    public Notification(String text) {
        this.text = text;
        this.time = CurrentTimeToString.get();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notification { "  +
                 " text  =  '" + text + '\'' +
                ",    time  =  '" + time + '\'' +
                '}';
    }
}
