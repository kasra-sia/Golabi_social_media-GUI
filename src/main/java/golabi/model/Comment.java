package golabi.model;

import java.util.LinkedList;

public class Comment extends Model {
    private int userID;
    private String text;
    private String time;
    private LinkedList<Integer> likes = new LinkedList<>();
    private LinkedList<Comment> comments = new LinkedList<>();
    public Comment(int userID, String text) {
        this.userID = userID;
        this.text = text;
    }

    public Comment() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public LinkedList<Integer> getLikes() {
        return likes;
    }
    public void setLikes(LinkedList<Integer> likes) {
        this.likes = likes;
    }

    public LinkedList<Comment> getComments() {
        return comments;
    }

    public void setComments(LinkedList<Comment> comments) {
        this.comments = comments;
    }
}
