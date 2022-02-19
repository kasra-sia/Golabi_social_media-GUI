package golabi.model.chat;

import golabi.model.Model;

import java.util.LinkedList;

public class ChatRoom extends ChatAbstractClass {
    private int[] usersID = new int[2];
    public ChatRoom(int userID1,int userID2) {
        usersID[0] = userID1;
        usersID[1] = userID2;
        isGroup = false;
    }

    public ChatRoom() {
    }

    public int[] getUsersID() {
        return usersID;
    }

    public void setUsersID(int[] usersID) {
        this.usersID = usersID;
    }

    public int getOther(int ID){
        if (ID== usersID[0])
            return usersID[1];
        else return usersID[0];
    }
    public boolean isExist(int id1 , int id2){
        return (id1 == usersID[0] && id2 == usersID[1])
                || (id2 == usersID[0] && id1 == usersID[1]);
    }
}
