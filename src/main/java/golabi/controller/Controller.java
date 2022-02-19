package golabi.controller;


import golabi.CurrentTimeToString;
import golabi.db.Context;
import golabi.model.account.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    public static final Logger logger = LogManager.getLogger(Controller.class);
    protected Context context;
    protected static User owner;
    public Controller(){
        context = new Context();
    }

    public Context getContext() {
        return context;
    }

    public  User getOwner() {
        return owner;
    }

    public void setLastSeen(){
        if (owner!=null){
        owner.getProfile().setLastSeenTime(CurrentTimeToString.get());
            Controller.logger.info("logout");
        context.Users.update(owner);}
    }
}