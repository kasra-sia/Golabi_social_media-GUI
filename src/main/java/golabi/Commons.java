package golabi;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public interface Commons {
    ImageIcon BACKGROUND_IMG = new ImageIcon("build/resources/main/image/pear.png");
    ImageIcon LOGIN_IMG = new ImageIcon("build/resources/main/image/User-Interface-Login-icon.png");
    ImageIcon REGISTER_IMG = new ImageIcon("build/resources/main/image/sign-up-icon.png");
    ImageIcon SWITCH_ON_IMG = new ImageIcon("build/resources/main/image/switch-on-icon.png");
    ImageIcon SWITCH_OFF_IMG = new ImageIcon("build/resources/main/image/switch-off-icon.png");
    ImageIcon BACK_IMG = new ImageIcon("build/resources/main/image/back-icon.png");
    ImageIcon MAIN_PAGE_BTN_IMG = new ImageIcon("build/resources/main/image/home-icon.png");
    ImageIcon CHECK_ICON_IMG = new ImageIcon("build/resources/main/image/sign-check-icon.png");
    ImageIcon GALLERY_IMG = new ImageIcon("build/resources/main/image/gallery-icon.png");
    ImageIcon ATTACH_IMG = new ImageIcon("build/resources/main/image/attach.png");
    ImageIcon LIKE_IMG = new ImageIcon("build/resources/main/image/Like.png");
    ImageIcon DISLIKE_IMG = new ImageIcon("build/resources/main/image/disLike.png");
    ImageIcon EXPLORER_IMG = new ImageIcon("build/resources/main/image/Explorer-icon.png");
    ImageIcon FIND_IMG = new ImageIcon("build/resources/main/image/find-icon.png");
    ImageIcon FOLLOW_IMG = new ImageIcon("build/resources/main/image/follow_IMG.png");
    ImageIcon UNFOLLOW_IMG = new ImageIcon("build/resources/main/image/unfollow_IMG.png");
    ImageIcon BLOCK_IMG = new ImageIcon("build/resources/main/image/block.png");
    ImageIcon UNBLOCK_IMG = new ImageIcon("build/resources/main/image/unBlock.png");
    ImageIcon MUTE_IMG = new ImageIcon("build/resources/main/image/mute.png");
    ImageIcon UNMUTE_IMG = new ImageIcon("build/resources/main/image/unmute.png");
    String USERS_DIR_PATH = "src/main/resources/db/users";
    String USERS_IMG_DIR_PATH = "src/main/resources/db/usersPhoto";
    String TWEETS_DIR_PATH = "src/main/resources/db/tweets";
    String TWEETS_IMG_DIR_PATH = "src/main/resources/db/tweetsImage";
    String CHATS_DIR_PATH = "src/main/resources/db/chats";
    String GROUP_CHATS_DIR_PATH = "src/main/resources/db/groups";
    String MESSAGES_DIR_PATH = "src/main/resources/db/messages";
    String MESSAGES_IMG_DIR_PATH = "src/main/resources/db/messagesImage";

    int FRAME_WIDTH = 1250;
    int FRAME_HEIGHT = 600;
    int CENTER_PANEL_WIDTH = 1250;
    int CENTER_PANEL_HEIGHT = 550;
    int PROFILE_BIG_IMG_WIDTH = 350;
    int PROFILE_BIG_IMG_HEIGHT = 350;
    int PROFILE_SMALL_IMG_WIDTH = 30;
    int PROFILE_SMALL_IMG_HEIGHT = 30;

    int TWEET_WIDTH = 500;
    int TWEET_HEIGHT = 200;
    int TWEET_IMAGE_WIDTH = 200;
    int TWEET_IMAGE_HEIGHT = 200;
    Color TWEET_COLOR = new Color(2, 5, 56);
    Color TWEET_BACKGROUND_COLOR = new Color(34, 78, 132, 156);
    Color COMMENT_COLOR = new Color(97, 94, 94, 255);
    Color COMMENT_BACKGROUND_COLOR = new Color(40, 40, 40, 158);
    Color MY_MESSAGE_COLOR = new Color(88, 163, 82, 245);
    Color OTHERS_MESSAGE_COLOR = new Color(44, 92, 41, 245);
    Color FOLLOW_REQUEST_COLOR = Color.white;
    Color FOLLOW_REQUEST_BACKGROUND_COLOR = Color.BLACK;

}
