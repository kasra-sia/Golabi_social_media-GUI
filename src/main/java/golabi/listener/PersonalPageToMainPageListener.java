package golabi.listener;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.controller.pageController.PersonalPageController;
import golabi.util.Loop;
import golabi.view.MainFrame;
import golabi.view.MainPanel;
import golabi.view.pageView.UsersListPageView;
import golabi.view.pageView.PersonalPage.NotificationPageView;
import golabi.view.pageView.PersonalPage.PersonalPageView;
import golabi.view.pageView.PersonalPage.ShowInfoPageView;
import golabi.view.pageView.myTweetsPageView.MyTweetsPageView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class PersonalPageToMainPageListener implements StringListener{
    private Container container;
    private PersonalPageView personalPageView;
    private CardLayout cardLayout;
    private ShowInfoPageView showInfoPageView;
    private MyTweetsPageView myTweetsPageView;
    private MyTweetsPageListener myTweetsPageListener;
    private NotificationPageView notificationPageView;
    private UsersListPageView usersListPageView;
    private PersonalPageController controller = new PersonalPageController();
    public PersonalPageToMainPageListener(Container container,CardLayout cardLayout) {
        this.container = container;
        this.cardLayout =cardLayout;
    }
    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("personalPage")){
            personalPageView = new PersonalPageView();
            container.add(personalPageView);
            cardLayout.next(container);
            container.revalidate();
            container.repaint();
            personalPageView.addStringListener(new StringListener() {
                @Override
                public void stringEventOccurred(String string) {
                    if (string.equals("showInfo")){
                        showInfoPageView = new ShowInfoPageView();
                        container.add(showInfoPageView);
                        cardLayout.next(container);
                        container.revalidate();
                        container.repaint();
                        ControllerToInfoListener controllerToInfoListener = new ControllerToInfoListener(controller,showInfoPageView);
                        showInfoPageView.setStringListener(controllerToInfoListener);
                        controllerToInfoListener.stringEventOccurred("openPage");
                        showInfoPageView.setStringListener(string13 -> {
//                            if (string13.equals("save"));
                            if (string13.equals("removePhoto"))
                                controller.removeProfilePhoto();
                            showInfoPageView.revalidate();
                            showInfoPageView.repaint();
                        });
                        showInfoPageView.setChangeInfoEventListener(new EditInfoEventListener());
                    }
                }
            });
            personalPageView.addStringListener(string12 -> {
                if (string12.equals("myTweets")) {
                    myTweetsPageView = new MyTweetsPageView(container,cardLayout);
                    myTweetsPageListener = new MyTweetsPageListener(myTweetsPageView);
                    myTweetsPageView.addStringListener(myTweetsPageListener);
                    container.add(myTweetsPageView);
                    cardLayout.next(container);
                    if (MainPanel.loop!=null)
                        MainPanel.loop.stop();
                    MainPanel.loop = new Loop(1,this::updateMyTweetsPage);
                    MainPanel.loop.start();
                    container.revalidate();
                    container.repaint();
                }
            });
            personalPageView.addStringListener(string1 -> {
                if (string1.equals("notifications")){
                    notificationPageView = new NotificationPageView();
                    notificationPageView.addStringListener(new NotificationPageListener());
                    container.add(notificationPageView);
                    cardLayout.next(container);
                    container.revalidate();
                    container.repaint();
                }
            });
            personalPageView.addStringListener(string1 -> {
                if (string1.equals("followings")){
                    usersListPageView = new UsersListPageView();
                    usersListPageView.addStringListener(new SearchUserListener());
                    container.add(usersListPageView);
                    cardLayout.next(container);
                    if (MainPanel.loop!=null)
                        MainPanel.loop.stop();
                    MainPanel.loop = new Loop(3,this::updateFollowingsPage);
                    MainPanel.loop.start();
                    container.revalidate();
                    container.repaint();
                }
            });
            personalPageView.addStringListener(string1 -> {
                if (string1.equals("followers")){
                    usersListPageView = new UsersListPageView();
                    usersListPageView.addStringListener(new SearchUserListener());
                    container.add(usersListPageView);
                    cardLayout.next(container);
                    if (MainPanel.loop!=null)
                        MainPanel.loop.stop();
                    MainPanel.loop = new Loop(3,this::updateFollowersPage);
                    MainPanel.loop.start();
                    container.revalidate();
                    container.repaint();
                }
            });
            personalPageView.addStringListener(string1 -> {
                if (string1.equals("blackList")){
                    usersListPageView = new UsersListPageView();
                    usersListPageView.addStringListener(new SearchUserListener());
                    container.add(usersListPageView);
                    cardLayout.next(container);
                    if (MainPanel.loop!=null)
                        MainPanel.loop.stop();
                    MainPanel.loop = new Loop(3,this::updateBlackListPage);
                    MainPanel.loop.start();
                    container.revalidate();
                    container.repaint();
                }
            });
        }
        MainFrame.refreshFrame();
    }

    private void updateFollowingsPage(){
        LinkedHashMap<String, ImageIcon> temp = new LinkedHashMap<>();
        controller.getOwner().getProfile().getFollowing().forEach(following ->{
            temp.put(controller.getContext().Users.get(following).getUsername(),
                    ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH,Commons.PROFILE_SMALL_IMG_HEIGHT,controller.getContext().Users.getPhoto(following)));
        });
        usersListPageView.setPage(temp);
        usersListPageView.revalidate();
        usersListPageView.repaint();
    }

    private void updateFollowersPage(){
        LinkedHashMap<String, ImageIcon> temp = new LinkedHashMap<>();
        controller.getOwner().getProfile().getFollowers().forEach(followers ->{
            temp.put(controller.getContext().Users.get(followers).getUsername(),
                    ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH,Commons.PROFILE_SMALL_IMG_HEIGHT,controller.getContext().Users.getPhoto(followers)));
        });
        usersListPageView.setPage(temp);
        usersListPageView.revalidate();
        usersListPageView.repaint();
        container.revalidate();
        container.repaint();
    }

    private void updateBlackListPage(){
        LinkedHashMap<String, ImageIcon> temp = new LinkedHashMap<>();
        controller.getOwner().getProfile().getBlacklist().forEach(blackList ->{
            temp.put(controller.getContext().Users.get(blackList).getUsername(),
                    ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH,Commons.PROFILE_SMALL_IMG_HEIGHT,controller.getContext().Users.getPhoto(blackList)));
        });
        usersListPageView.setPage(temp);
    }

    private void updateMyTweetsPage(){
        myTweetsPageListener.stringEventOccurred("openPage");
    }
}
