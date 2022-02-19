package golabi.controller.pageController;

import golabi.Commons;
import golabi.controller.Controller;
import golabi.event.CreatTweetEvent;
import golabi.event.EditInfoEvent;
import golabi.event.RegistrationFormEvent;
import golabi.listener.EventListener;
import golabi.model.Tweet;
import golabi.model.account.User;

public class PersonalPageController extends Controller {
    public void saveInfo(EditInfoEvent e) throws Exception {
        if (context.Users.all() != null)
            for (User user : context.Users.all()) {
                if (e.getUsername().equals(user.getUsername()) && !e.getUsername().isEmpty() && !e.getUsername().equals(owner.getUsername()))
                    throw new Exception("duplicatedUsername");
                if (e.getEmail().equals(user.getProfile().getEmail()) && !e.getEmail().isEmpty() && !e.getEmail().equals(owner.getProfile().getEmail()))
                    throw new Exception("duplicatedEmail");
                if (e.getPhoneNumber().equals(user.getProfile().getPhoneNumber()) && !e.getPhoneNumber().isEmpty() && !e.getPhoneNumber().equals(owner.getProfile().getPhoneNumber())) {
                    throw new Exception("duplicatedPhoneNumber");
                }
            }
        owner.getProfile().setFirstName(e.getFirstName());
        owner.getProfile().setLastName(e.getLastname());
        owner.setUsername(e.getUsername());
        owner.getProfile().setBio(e.getBio());
        owner.getProfile().setBirthday(e.getBirthday());
        owner.getProfile().setEmail(e.getEmail());
        owner.getProfile().setPhoneNumber(e.getPhoneNumber());
        if (e.getImageFilePath()!=null)
        context.Users.updatePhoto(owner, e.getImageFilePath());
        logger.info("@"+owner.getUsername()+"updated his account info");
        context.Users.update(owner);
    }

    public void removeProfilePhoto(){
        try{
        context.Users.removePhoto(owner);
            logger.info("@"+owner.getUsername()+" removed his photo");
    }catch (Exception ignore){

        }
    }
}
