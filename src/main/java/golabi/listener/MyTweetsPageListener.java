package golabi.listener;

import golabi.controller.TweetController;

import golabi.model.Tweet;
import golabi.view.modelView.TweetView;
import golabi.view.pageView.myTweetsPageView.MyTweetsPageView;

import java.util.LinkedList;

public class MyTweetsPageListener implements StringListener{
    private MyTweetsPageView view;
    private TweetController controller = new TweetController();

    public MyTweetsPageListener(MyTweetsPageView view) {
        this.view = view;
    }
    @Override
    public void stringEventOccurred(String string) {
       if (string.equals("openPage")){
           LinkedList<TweetView> temp = new LinkedList<>();
           if (controller.getContext().Tweets.all()!=null)
           for (Tweet tweet:controller.getContext().Tweets.all()) {
               boolean flag1 = false;
               for (Integer like:tweet.getLikes()) {
                   if (like == controller.getOwner().getID())
                       flag1 = true;
               }
               if (tweet.getUserID()==controller.getOwner().getID()){
                   TweetView tweetView = new TweetView(tweet.getText(),
                           controller.getContext().Users.get(tweet.getUserID()).getUsername(),
                           controller.getContext().Users.getPhoto(tweet.getUserID()),
                           controller.getContext().Tweets.getPhoto(tweet.getID()),
                           tweet.getLikes().size(),
                           flag1,
                           tweet.getTime(),
                           new TweetListener<MyTweetsPageView>(tweet));
                   temp.add(tweetView);
               }
           }
           view.getTweetsScrollPanel().setComponentsList(temp);
           view.repaint();
           view.revalidate();
       }
    }
}
