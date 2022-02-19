package golabi.listener;

import golabi.controller.UserController;
import golabi.event.FollowRequestEvent;
import golabi.event.SearchUserEvent;

public class ReceivedRequestsEventListener implements EventListener{
    private UserController controller = new UserController();


    @Override
    public <T> void eventOccurred(T event) throws Exception {
        if (event instanceof FollowRequestEvent)
        controller.manageRequest(((FollowRequestEvent) event));
    }
}
