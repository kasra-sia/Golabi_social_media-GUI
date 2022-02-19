package golabi.listener;

import com.fasterxml.jackson.core.JsonToken;
import golabi.controller.TweetController;
import golabi.controller.pageController.PersonalPageController;
import golabi.model.Tweet;
import golabi.view.MainPanel;
import golabi.view.pageView.commentsPageView.CommentsPageView;
import golabi.view.pageView.myTweetsPageView.MyTweetsPageView;

import javax.swing.*;

public class TweetListener<View> implements StringListener {
    private TweetController controller = new TweetController();
    private Tweet tweet;
    MyTweetsPageListener myTweetsPageListener;
    public TweetListener(Tweet tweet) {
        this.tweet = tweet;
    }
    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("delete")) {
            controller.deleteTweet(tweet);
        }
        if (string.equals("comment")) {
                CommentsPageView commentsPageView =
                        new CommentsPageView();
                CommentsPageListener commentsPageListener = new CommentsPageListener(tweet,commentsPageView);
                commentsPageView.addStringListener(commentsPageListener);
                commentsPageListener.setParentTweet(tweet);
                commentsPageListener.stringEventOccurred("openPage");
                MainPanel.container.add(commentsPageView);
                MainPanel.cardLayout.next(MainPanel.container);
            }

        if (string.equals("like")) {
            tweet.getLikes().add(controller.getOwner().getID());
            controller.getContext().Tweets.update(tweet);
        }
        if (string.equals("unLike")) {
            tweet.getLikes().remove(Integer.valueOf(controller.getOwner().getID()));
            controller.getContext().Tweets.update(tweet);
        }
        if (string.equals("retweet")) {

        }
        if (string.equals("report"))
            controller.report(tweet);
    }
}
