package golabi.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import golabi.Commons;
import golabi.model.Model;
import golabi.model.Tweet;
import golabi.model.account.User;
import golabi.model.chat.Message;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MessageDB implements DBSet<Message> , Commons {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Message get(int id) {
        File dir = new File(MESSAGES_DIR_PATH);
        File[] MessageDirectoryListing = dir.listFiles();
        if (MessageDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(MESSAGES_DIR_PATH + "/" + id + ".json"), new TypeReference<Message>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Message> all() {
        File dir = new File(MESSAGES_DIR_PATH);
        File[] MessageDirectoryListing = dir.listFiles();
        if (MessageDirectoryListing.length != 0) {
            LinkedList<Message> list = new LinkedList<>();
            for (File child : MessageDirectoryListing) {
                // Do something with child
                Message message = null;
                try {
                    message= objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<Message>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(message);
            }
            Collections.sort(list, Comparator.comparingInt(Model::getID));
            return list;
        }
        return new LinkedList<Message>();
    }

    @Override
    public void add(Message message) {

        File dir = new File(MESSAGES_DIR_PATH);
        File[] MessageDirectoryListing = dir.listFiles();
        assert MessageDirectoryListing != null;
        if (MessageDirectoryListing.length != 0) {
            int ID = Integer.parseInt(FilenameUtils.removeExtension(MessageDirectoryListing[MessageDirectoryListing.length - 1].getName()));
            message.setID(ID + 1);

        } else {
            message.setID(0);
        }

        File file = new File(MESSAGES_DIR_PATH + "/" + message.getID() + ".json");

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Message message) {
        File dir = new File(MESSAGES_DIR_PATH);
        File[] MessageDirectoryListing = dir.listFiles();
        File temp = null;
        assert MessageDirectoryListing != null;
        if (MessageDirectoryListing.length != 0) {
            for (File child : MessageDirectoryListing) {
                if (child.getName().equals(String.valueOf(message.getID() + ".json")))
                    temp = child;
            }
            assert temp != null;
            removePhoto(message);
            temp.delete();
        }
    }

    @Override
    public void update(Message message) {

        File file = new File(MESSAGES_DIR_PATH + "/" + message.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPhoto(int id) {

        File file = new File(MESSAGES_IMG_DIR_PATH+"/" + id + ".png");
        if (file.exists())
            return file.getPath();
        return null;
    }

    @Override
    public void updatePhoto(Message message, String path) {
        int width = Commons.TWEET_IMAGE_WIDTH;    //width of the image
        int height = Commons.TWEET_IMAGE_HEIGHT;   //height of the image
        BufferedImage image = null;
        try {
            File input_file = new File(path); //image file path
            image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            // Reading input file
            image = ImageIO.read(input_file);

            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        try {
            File output_file = new File(MESSAGES_IMG_DIR_PATH+"/" + message.getID() + ".png");
            ImageIO.write(image, "png", output_file);
            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void removePhoto(Message message) {
        File dir = new File(MESSAGES_IMG_DIR_PATH);
        File[] MessageIMageDirectoryListing = dir.listFiles();
        File temp = null;
        assert MessageIMageDirectoryListing != null;
        if (MessageIMageDirectoryListing.length != 0) {
            for (File child : MessageIMageDirectoryListing) {
                if (child.getName().equals(String.valueOf(message.getID() + ".png")))
                    temp = child;
            }
            assert temp != null;
            if (temp != null)
                temp.delete();
        }
    }

    @Override
    public void report(Message message, User user) {

    }
}
