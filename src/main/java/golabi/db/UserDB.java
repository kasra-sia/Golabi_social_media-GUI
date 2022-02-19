package golabi.db;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import golabi.Commons;
import golabi.model.account.User;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class UserDB implements DBSet<User>, Commons {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public User get(int id) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(USERS_DIR_PATH + "/" + id + ".json"), new TypeReference<User>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<User> all() {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing.length != 0) {
            LinkedList<User> list = new LinkedList<>();
            for (File child : UserDirectoryListing) {
                // Do something with child
                User user = null;
                try {
                    user = objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<User>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(user);
            }
            Collections.sort(list, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getID()- o2.getID();
                }
            });
            return list;
        }
        return null;
    }

    @Override
    public void add(User user) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        assert UserDirectoryListing != null;
        if (UserDirectoryListing.length != 0) {
            user.setID(UserDirectoryListing.length);
        } else {
            user.setID(0);
        }
        File file = new File(USERS_DIR_PATH + "/" + user.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        File temp = null;

        assert UserDirectoryListing != null;
        if (UserDirectoryListing.length != 0) {
            for (File child : UserDirectoryListing) {
                if (child.getName().equals(String.valueOf(user.getID() + ".json")))
                    temp = child;
            }
            temp.delete();
            removePhoto(user);
        }

    }

    @Override
    public void update(User user) {

        File file = new File(USERS_DIR_PATH + "/" + user.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPhoto(int id) {
        File file = new File("src/main/resources/db/usersPhoto/" + id + ".png");
        if (file.exists())
            return file.getPath();
        return Commons.GALLERY_IMG.getDescription();
    }

    @Override
    public void updatePhoto(User user,String path) {
        int width = Commons.PROFILE_BIG_IMG_WIDTH;    //width of the image
        int height = Commons.PROFILE_BIG_IMG_HEIGHT;   //height of the image

        // For storing image in RAM
        BufferedImage image = null;

        // READ IMAGE
        try {
            File input_file = new File(path); //image file path
            /* create an object of BufferedImage type and pass
               as parameter the width,  height and image int
               type.TYPE_INT_ARGB means that we are representing
               the Alpha, Red, Green and Blue component of the
               image pixel using 8 bit integer value. */
            image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            // Reading input file
            image = ImageIO.read(input_file);

            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        // WRITE IMAGE
        try {
            // Output file path
            File output_file = new File("src/main/resources/db/usersPhoto/" + user.getID() + ".png");

            // Writing to file taking type and path as
            ImageIO.write(image, "png", output_file);

            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void removePhoto(User user) {
        File dir = new File(USERS_IMG_DIR_PATH);
        File[] UserPhotoDirectoryListing = dir.listFiles();
        File temp = null;
        assert UserPhotoDirectoryListing != null;
        if (UserPhotoDirectoryListing.length != 0) {
            for (File child : UserPhotoDirectoryListing) {
                if (child.getName().equals(String.valueOf(user.getID() + ".png")))
                    temp = child;
            }
            if (temp != null)
                temp.delete();

        }
    }

    @Override
    public void report(User user,User user1) {

    }
}

