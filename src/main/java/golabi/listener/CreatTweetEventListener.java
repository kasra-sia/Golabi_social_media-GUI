package golabi.listener;

import golabi.controller.TweetController;
import golabi.controller.pageController.PersonalPageController;
import golabi.event.CreatTweetEvent;
import golabi.model.Tweet;
import golabi.view.pageView.myTweetsPageView.CreatTweetPanel;

public class CreatTweetEventListener implements EventListener{
    private CreatTweetPanel view;
    private TweetController controller = new TweetController();
    public CreatTweetEventListener(CreatTweetPanel view) {
        this.view = view;
        }

    @Override
    public <T> void eventOccurred(T event) throws Exception {
      controller.creatTweet((CreatTweetEvent)event);
    }
}
