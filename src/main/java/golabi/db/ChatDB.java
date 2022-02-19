package golabi.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import golabi.Commons;
import golabi.model.Model;
import golabi.model.Tweet;
import golabi.model.account.User;
import golabi.model.chat.ChatRoom;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public class ChatDB implements DBSet<ChatRoom>, Commons {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ChatRoom get(int id) {
        File dir = new File(CHATS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        if (TweetDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(CHATS_DIR_PATH + "/" + id + ".json"), new TypeReference<ChatRoom>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<ChatRoom> all() {

        File dir = new File(CHATS_DIR_PATH);
        File[] ChatDirectoryListing = dir.listFiles();
        if (ChatDirectoryListing.length != 0) {
            LinkedList<ChatRoom> list = new LinkedList<>();
            for (File child : ChatDirectoryListing) {
                // Do something with child
                ChatRoom  chatRoom = null;
                try {
                    chatRoom = objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<ChatRoom>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(chatRoom);
            }
            list.sort(Comparator.comparingInt(Model::getID));
            return list;
        }
        return new LinkedList<ChatRoom>();
    }

    @Override
    public void add(ChatRoom chatRoom) {
        File dir = new File(CHATS_DIR_PATH);
        File[] ChatDirectoryListing = dir.listFiles();
        assert ChatDirectoryListing != null;
        if (ChatDirectoryListing.length != 0) {
            chatRoom.setID(ChatDirectoryListing.length);
        } else {
            chatRoom.setID(0);
        }

        File file = new File(CHATS_DIR_PATH + "/" + chatRoom.getID() + ".json");

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), chatRoom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(ChatRoom chatRoom) {

        File dir = new File(CHATS_DIR_PATH);
        File[] ChatDirectoryListing = dir.listFiles();
        File temp = null;
        assert ChatDirectoryListing != null;
        if (ChatDirectoryListing.length != 0) {
            for (File child : ChatDirectoryListing) {
                if (child.getName().equals(String.valueOf(chatRoom.getID() + ".json")))
                    temp = child;
            }

            temp.delete();
        }
    }

    @Override
    public void update(ChatRoom chatRoom) {

        File file = new File(TWEETS_DIR_PATH + "/" + chatRoom.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), chatRoom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPhoto(int id) {
        return null;
    }

    @Override
    public void updatePhoto(ChatRoom chatRoom, String path) {

    }

    @Override
    public void removePhoto(ChatRoom chatRoom) {

    }

    @Override
    public void report(ChatRoom chatRoom, User user) {

    }
}
