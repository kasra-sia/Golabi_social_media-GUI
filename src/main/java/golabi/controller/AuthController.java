package golabi.controller;


import golabi.event.ChangePasswordFormEvent;
import golabi.event.LoginFormEvent;
import golabi.event.RegistrationFormEvent;
import golabi.model.account.Profile;
import golabi.model.account.User;

public class AuthController extends Controller {




    public void register(RegistrationFormEvent e) throws Exception {
        if (context.Users.all()!=null)
        for (User user:context.Users.all()) {
            if (e.getUsername().equals(user.getUsername())&& !e.getUsername().isEmpty()) {
                logger.warn("registration warning : duplicatedUsername");
                throw new Exception("duplicatedUsername");
            }
            if (e.getEmail().equals(user.getProfile().getEmail())&& !e.getEmail().isEmpty()) {
                logger.warn("registration warning : duplicatedEmail");
                throw new Exception("duplicatedEmail");
            }
            if (e.getPhoneNumber().equals(user.getProfile().getPhoneNumber()) && !e.getPhoneNumber().isEmpty()) {
                logger.warn("registration warning : duplicatedPhoneNumber");
                throw new Exception("duplicatedPhoneNumber");
            }
        }
        Profile profile = new Profile(e.getFirstName(), e.getLastname(), e.getEmail(), e.getBio(), e.getBirthday(), e.getPhoneNumber());
        User user = new User(e.getUsername(), e.getPassword1(), profile);
        context.Users.add(user);
        Controller.owner = user;
        logger.info("@"+owner.getUsername()+" has signup to golabi");


    }

    public void login(LoginFormEvent e) throws Exception {
        boolean flag1 = false;
        if (context.Users.all() != null)
        for (User user : context.Users.all()) {
            if (user.getUsername().equals(e.getUsername())
                    && user.getPassword().equals(e.getPassword())) {
                owner = user;
                logger.info("@"+owner.getUsername()+" has signed in to golabi");
                flag1 = true;
                break;
            }
        }
        if (!flag1) {
            logger.warn("wrong username or password");
            throw new Exception("wrongUsernameOrPassword");
        }

    }
}