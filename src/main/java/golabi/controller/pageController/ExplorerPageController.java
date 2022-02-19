package golabi.controller.pageController;

import golabi.controller.Controller;
import golabi.event.SearchUserEvent;
import golabi.model.account.User;

public class ExplorerPageController extends Controller {
    public User searchUser(SearchUserEvent event) throws Exception {
        for (User user:context.Users.all()) {
            if (user.getUsername().equals(event.getUsername()) && !user.getUsername().equals(owner.getUsername())) {
                if (!user.getProfile().getBlacklist().contains(owner.getID())) {
                    return user;
                }
            }
        }
            throw new Exception("not found");
    }

}
