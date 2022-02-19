package golabi.listener.newMessageListeners;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.controller.ChatController;
import golabi.event.CreatTweetEvent;
import golabi.event.NewMessageEvent;
import golabi.listener.CreatTweetEventListener;
import golabi.listener.StringListener;
import golabi.validation.ImageFilter;
import golabi.view.pageView.messagingPage.chatRoom.NewMessagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewMessageStringListener implements StringListener {
    private ChatController controller = new ChatController();
    private NewMessagePanel newMessagePanel;
    private String imageFilePath = null;
    private int currentChatRoom = 0;
    public NewMessageStringListener(NewMessagePanel newMessagePanel,int currentChatRoom) {
        this.newMessagePanel = newMessagePanel;
        this.currentChatRoom = currentChatRoom;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("selectImage")) {
            JFileChooser imageChooser = new JFileChooser();
            imageChooser.addChoosableFileFilter(new ImageFilter());
            imageChooser.setAcceptAllFileFilterUsed(false);
            int ans = imageChooser.showOpenDialog(newMessagePanel);
            if (ans == 0) {
                imageFilePath = imageChooser.getSelectedFile().getPath();
                newMessagePanel.getShowImagePath().setText(imageFilePath);
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
        if (string.equals("send")) {
            try {
                NewMessageEventListener newMessageEventListener = new NewMessageEventListener();
                newMessagePanel.setEventListener(newMessageEventListener);
                newMessageEventListener.eventOccurred(new NewMessageEvent(
                        newMessagePanel,
                        newMessagePanel.getMessageText(),
                        imageFilePath,
                        currentChatRoom
                        ));

                newMessagePanel.reset();
                imageFilePath = null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (string.equals("showImage")) {
            if (imageFilePath != null) {
                int a = JOptionPane.showOptionDialog(null, "delete this photo ?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, ImageResizer.reSizeImage(Commons.TWEET_IMAGE_WIDTH, Commons.TWEET_IMAGE_HEIGHT, imageFilePath), null, null);
                if (a==0){
                    newMessagePanel.getShowImagePath().setText("");
                    imageFilePath = null;
                }
            }
        }
    }
}
