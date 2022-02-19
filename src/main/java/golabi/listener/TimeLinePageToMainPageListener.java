package golabi.listener;

import golabi.controller.TweetController;
import golabi.model.Tweet;
import golabi.util.Loop;
import golabi.view.MainPanel;
import golabi.view.modelView.TweetView;
import golabi.view.pageView.TimeLinePageView.TimeLinePageView;
import golabi.view.pageView.myTweetsPageView.MyTweetsPageView;

import java.util.LinkedList;

public class TimeLinePageToMainPageListener implements StringListener{
    private TweetController controller;
    private TimeLinePageView view;
    public TimeLinePageToMainPageListener() {
        controller = new TweetController();

    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("timeLinePage")){
            this.view = new TimeLinePageView();
            MainPanel.container.add(view);
            MainPanel.cardLayout.next(MainPanel.container);
            if (MainPanel.loop!=null)
                MainPanel.loop.stop();
            MainPanel.loop = new Loop(1,this::updateTimeLinePage);
            MainPanel.loop.start();
            MainPanel.container.revalidate();
            MainPanel.container.repaint();
                }
        }
        private void updateTimeLinePage() {
            LinkedList<TweetView> temp = new LinkedList<>();
            if (controller.getContext().Tweets.all() != null)
                for (Tweet tweet : controller.getContext().Tweets.all()) {
                    controller.getOwner().getProfile().getFollowing().forEach(following -> {
                        boolean flag1 = false;
                        for (Integer like : tweet.getLikes()) {
                            if (like == controller.getOwner().getID())
                                flag1 = true;
                        }
                        if (tweet.getUserID() == following) {
                            TweetView tweetView = new TweetView(tweet.getText(),
                                    controller.getContext().Users.get(tweet.getUserID()).getUsername(),
                                    controller.getContext().Users.getPhoto(tweet.getUserID()),
                                    controller.getContext().Tweets.getPhoto(tweet.getID()),
                                    tweet.getLikes().size(),
                                    flag1,
                                    tweet.getTime(),
                                    new TweetListener<TimeLinePageView>(tweet));
                            temp.add(tweetView);
                        }
                    });
                }
            view.setPage(temp);
        }
    }

