package golabi.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import golabi.Commons;
import golabi.controller.Controller;
import golabi.model.Tweet;
import golabi.model.account.User;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TweetDB implements DBSet<Tweet>, Commons{
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Tweet get(int id) {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        if (TweetDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(TWEETS_DIR_PATH + "/" + id + ".json"), new TypeReference<Tweet>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList all() {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        if (TweetDirectoryListing.length != 0) {
            LinkedList<Tweet> list = new LinkedList<>();
            for (File child : TweetDirectoryListing) {
                // Do something with child
                Tweet tweet = null;
                try {
                    tweet = objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<Tweet>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(tweet);
            }
            Collections.sort(list, new Comparator<Tweet>() {
                @Override
                public int compare(Tweet o1, Tweet o2) {
                    return o2.getID()-o1.getID();
                }
            });
            return list;
        }
        return null;
    }

    @Override
    public void add(Tweet tweet) {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        assert TweetDirectoryListing != null;
        if (TweetDirectoryListing.length != 0) {
            tweet.setID(TweetDirectoryListing.length );
        } else {
            tweet.setID(0);
        }

        File file = new File(TWEETS_DIR_PATH + "/" + tweet.getID() + ".json");

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), tweet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Tweet tweet) {
        File dir = new File(TWEETS_DIR_PATH);
        File[] TweetDirectoryListing = dir.listFiles();
        File temp = null;
        assert TweetDirectoryListing != null;
        if (TweetDirectoryListing.length != 0) {
            for (File child : TweetDirectoryListing) {
                if (child.getName().equals(String.valueOf(tweet.getID() + ".json")))
                    temp = child;
            }
            assert temp != null;
            removePhoto(tweet);
            temp.delete();
        }
    }

    @Override
    public void update(Tweet tweet) {

        File file = new File(TWEETS_DIR_PATH + "/" + tweet.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), tweet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePhoto(Tweet tweet, String path) {
        int width = Commons.TWEET_IMAGE_WIDTH;    //width of the image
        int height = Commons.TWEET_IMAGE_HEIGHT;   //height of the image

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
            File output_file = new File("src/main/resources/db/tweetsImage/" + tweet.getID() + ".png");

            // Writing to file taking type and path as
            ImageIO.write(image, "png", output_file);

            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        }

    @Override
    public String getPhoto(int id) {
        File file = new File("src/main/resources/db/tweetsImage/" + id + ".png");

        if (file.exists())
            return file.getPath();
        return null;
    }

    @Override
    public void removePhoto(Tweet tweet) {

        File dir = new File(TWEETS_IMG_DIR_PATH);
        File[] TweetIMageDirectoryListing = dir.listFiles();
        File temp = null;
        assert TweetIMageDirectoryListing != null;
        if (TweetIMageDirectoryListing.length != 0) {
            for (File child : TweetIMageDirectoryListing) {
                if (child.getName().equals(String.valueOf(tweet.getID() + ".png")))
                    temp = child;
            }
            assert temp != null;
            if (temp != null)
            temp.delete();

        }
    }

    @Override
    public void report(Tweet tweet,User user) {
        if (!tweet.getReportCounter().contains(user.getID())) {
            FileWriter fileWriter = null;
            tweet.getReportCounter().add(user.getID());
            update(tweet);
            try {
                File file = new File("src/main/resources/db/reportedTweets/" + tweet.getID() + ".txt");
                file.createNewFile();
                fileWriter = new FileWriter("src/main/resources/db/reportedTweets/" + tweet.getID() + ".txt", true);
                fileWriter.write(user.getUsername() + " has reported this tweet");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (tweet.getReportCounter().size() == 3)
                remove(tweet);
        }
    }
    }

