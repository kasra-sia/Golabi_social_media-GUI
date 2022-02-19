package golabi.controller.pageController;

import golabi.controller.Controller;
import golabi.event.ChangePasswordFormEvent;
import golabi.model.LastSeen;
import golabi.model.chat.ChatRoom;

import java.util.Arrays;
import java.util.Collections;

public class SettingsPageController extends Controller {

    public void setAccountPrivate(){
        owner.getProfile().setPrivate(true);
        logger.info("@"+owner.getUsername()+" set his account public");
        context.Users.update(owner);
    }
    public void setAccountPublic(){
        owner.getProfile().setPrivate(false);
        logger.info("@"+owner.getUsername()+" set his account private ");
        context.Users.update(owner);
    }
    public void setLastSeenNoBody(){
        owner.getProfile().setLastSeen(LastSeen.NOBODY);
        logger.info("@"+owner.getUsername()+" set his lastSeen "+LastSeen.NOBODY.toString());
        context.Users.update(owner);
    }
    public void setLastSeenEveryBody(){
        owner.getProfile().setLastSeen(LastSeen.EVERYONE);
        logger.info("@"+owner.getUsername()+" set his lastSeen "+LastSeen.EVERYONE.toString());
        context.Users.update(owner);
    }
    public void setLastSeenFollowings(){
        owner.getProfile().setLastSeen(LastSeen.FOLLOWINGS);
        logger.info("@"+owner.getUsername()+" set his lastSeen "+LastSeen.FOLLOWINGS.toString());
        context.Users.update(owner);
    }

    public void changePassword(ChangePasswordFormEvent e) throws Exception {
        if (e.getOldPassword().equals(owner.getPassword())) {
            owner.setPassword(e.getNewPassword());
            context.Users.update(owner);
            logger.info("@"+owner.getUsername()+"changed his password");
        }else throw new Exception("wrongPassword");
    }


}
