package golabi.listener;

import golabi.controller.TweetController;
import golabi.model.Tweet;
import golabi.util.Loop;
import golabi.view.MainPanel;
import golabi.view.modelView.TweetView;
import golabi.view.pageView.TimeLinePageView.TimeLinePageView;
import golabi.view.pageView.explorerPageView.ExplorerGlobalView;
import golabi.view.pageView.explorerPageView.ExplorerSearchView;

import java.util.LinkedList;

public class ExplorerPageListener implements StringListener {
    private ExplorerGlobalView explorerGlobalView;
    private TweetController controller = new TweetController();

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("find")) {
            ExplorerSearchView explorerSearchView = new ExplorerSearchView();
            SearchUserListener searchUserListener = new SearchUserListener();
            explorerSearchView.setEventListener(searchUserListener);
            explorerSearchView.addStringListeners(searchUserListener);
            MainPanel.container.add(explorerSearchView);
            MainPanel.cardLayout.next(MainPanel.container);
        }
        if (string.equals("tweetExplorer")) {
            explorerGlobalView = new ExplorerGlobalView();
            MainPanel.container.add(explorerGlobalView);
            MainPanel.cardLayout.next(MainPanel.container);
            if (MainPanel.loop!=null)
                MainPanel.loop.stop();
            MainPanel.loop = new Loop(1,this::updateGlobalPage);
            MainPanel.loop.start();
        }
    }

    private void updateGlobalPage() {
        LinkedList<TweetView> temp = new LinkedList<>();
        if (controller.getContext().Tweets.all() != null)
            for (Tweet tweet : controller.getContext().Tweets.all()) {
                boolean flag1 = false;
                for (Integer like : tweet.getLikes()) {
                    if (like == controller.getOwner().getID())
                        flag1 = true;
                }
                if (!controller.getOwner().getProfile().getBlacklist().contains(tweet.getUserID())) {
                    TweetView tweetView = new TweetView(tweet.getText(),
                            controller.getContext().Users.get(tweet.getUserID()).getUsername(),
                            controller.getContext().Users.getPhoto(tweet.getUserID()),
                            controller.getContext().Tweets.getPhoto(tweet.getID()),
                            tweet.getLikes().size(),
                            flag1,
                            tweet.getTime(),
                            new TweetListener<TimeLinePageView>(tweet));
                    if(tweet.getUserID()!=controller.getOwner().getID())
                        tweetView.setOthersTweet();
                    temp.add(tweetView);
                }
            }
        explorerGlobalView.setPage(temp);
    }


}
