package golabi.listener;

import golabi.DateToString;
import golabi.controller.pageController.ExplorerPageController;
import golabi.event.SearchUserEvent;
import golabi.event.SetUserViewEvent;
import golabi.model.account.User;
import golabi.util.Loop;
import golabi.view.MainPanel;
import golabi.view.modelView.UserView;
import golabi.view.pageView.explorerPageView.ExplorerSearchView;

public class SearchUserListener implements EventListener, StringListener {
    private ExplorerPageController controller = new ExplorerPageController();
    private User foundUser;

    private UserView userView;


    @Override
    public <T> void eventOccurred(T event) throws Exception {
        foundUser = controller. searchUser((SearchUserEvent) event);
        if (((SearchUserEvent) event).getSource() instanceof ExplorerSearchView)
        ((ExplorerSearchView) ((SearchUserEvent) event).getSource()).setPage(foundUser.getUsername(), controller.getContext().Users.getPhoto(foundUser.getID()));
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("openProfilePage")) {
            userView = new UserView();
            UserViewListener userViewListener= new UserViewListener(foundUser,userView);

            if (MainPanel.loop!=null)
                MainPanel.loop.stop();
            MainPanel.loop = new Loop(2,()->updateProfilePage(userViewListener));
            MainPanel.loop.start();
            userView.addStringListener(userViewListener);
            MainPanel.container.add(userView);
            MainPanel.cardLayout.next(MainPanel.container);
        }
    }
    private void updateProfilePage(UserViewListener userViewListener){
        userViewListener.stringEventOccurred("openPage");
    }

}
