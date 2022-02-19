package golabi.listener;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.event.CreatTweetEvent;
import golabi.validation.ImageFilter;
import golabi.view.pageView.myTweetsPageView.CreatTweetPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreatTweetPanelListener implements StringListener {
    private CreatTweetPanel view;
    private String imageFilePath = null;
    public CreatTweetPanelListener(CreatTweetPanel creatTweetPanel) {
        view = creatTweetPanel;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("selectImage")) {
            JFileChooser imageChooser = new JFileChooser();
            imageChooser.addChoosableFileFilter(new ImageFilter());
            imageChooser.setAcceptAllFileFilterUsed(false);
            int ans = imageChooser.showOpenDialog(view);
            if (ans == 0) {
                 imageFilePath = imageChooser.getSelectedFile().getPath();
                 view.getShowImagePath().setText(imageFilePath);
                try {
                    new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
                    BufferedImage bf;
                    bf = ImageIO.read(new File(imageFilePath));
                    bf.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (string.equals("tweet")) {
                try {
                    CreatTweetEventListener creatTweetEventListener = new CreatTweetEventListener(view);
                    view.setEventListener(creatTweetEventListener);
                    creatTweetEventListener.eventOccurred(new CreatTweetEvent(
                            view,
                            view.getTweetText(),
                            imageFilePath));

                    view.reset();
                    imageFilePath = null;

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        if (string.equals("showImage")) {
            if (imageFilePath != null) {
                int a = JOptionPane.showOptionDialog(null, "delete this photo ?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, ImageResizer.reSizeImage(Commons.TWEET_IMAGE_WIDTH, Commons.TWEET_IMAGE_HEIGHT, imageFilePath), null, null);
                if (a==0){
                    view.getShowImagePath().setText("");
                    imageFilePath = null;
                }
              }
        }
    }
}
