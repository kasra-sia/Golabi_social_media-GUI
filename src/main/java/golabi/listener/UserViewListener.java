package golabi.listener;

import golabi.DateToString;
import golabi.controller.UserController;
import golabi.event.SetUserViewEvent;
import golabi.model.account.User;
import golabi.util.Loop;
import golabi.view.MainPanel;
import golabi.view.modelView.UserView;
public class UserViewListener implements StringListener{
    private UserController controller = new UserController();
    private UserView view;
    private User foundUser;

    public UserViewListener(User user, UserView view) {
        this.foundUser = user;
        this.view = view;

    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("openPage")) {
            boolean isFollowed = false;
            boolean isBlocked = false;
            boolean isMuted = false;
            if (controller.getOwner().getProfile().getFollowing().contains(foundUser.getID())) {
                isFollowed = true;
            }
            if (controller.getOwner().getProfile().getBlacklist().contains(foundUser.getID())) {
                isBlocked = true;
            }
            if (controller.getOwner().getProfile().getMuteList().contains(foundUser.getID())) {
                isMuted = true;
            }
            String lastSeen = "";
            switch (foundUser.getProfile().getLastSeen()) {
                case NOBODY -> lastSeen = "recently";
                case EVERYONE -> lastSeen = foundUser.getProfile().getLastSeenTime();
                case FOLLOWINGS -> {
                    if (foundUser.getProfile().getFollowing().contains(controller.getOwner().getID())) {
                        lastSeen = foundUser.getProfile().getLastSeenTime();
                    } else lastSeen = "recently";
                }
            }
            view.setPage(new SetUserViewEvent(
                    foundUser.getUsername(),
                    controller.getContext().Users.getPhoto(foundUser.getID()),
                    foundUser.getProfile().getFirstName(),
                    foundUser.getProfile().getLastName(),
                    lastSeen,
                    DateToString.dateToString(foundUser.getProfile().getBirthday()),
                    foundUser.getProfile().getBio(),
                    isFollowed,
                    isBlocked,
                    isMuted,
                    foundUser.getProfile().isPrivate()
            ));
        }
        if (string.equals("follow")){
            boolean temp =controller.follow(foundUser);
            if (!temp)
                view.showMessage("follow request sent");
        }
        if (string.equals("unfollow"))
            controller.unfollow(foundUser);
        if (string.equals("block"))
            controller.block(foundUser);
        if (string.equals("unblock"))
            controller.unblock(foundUser);
    }
}
