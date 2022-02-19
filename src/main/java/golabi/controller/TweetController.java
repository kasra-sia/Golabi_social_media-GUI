package golabi.controller;

import golabi.event.CreatCommentEvent;
import golabi.event.CreatTweetEvent;
import golabi.model.Comment;
import golabi.model.Tweet;

public class TweetController extends Controller{
    public void creatTweet(CreatTweetEvent e){

        if (!e.getText().isEmpty() && e.getImageFilePath() == null){
            Tweet tweet = new Tweet(e.getText(),owner.getID());
            logger.info("@"+owner.getUsername()+" posted a new tweet ");
            context.Tweets.add(tweet);
        }
        if (e.getImageFilePath() != null && !e.getText().isEmpty()){
            Tweet tweet = new Tweet(e.getText(),owner.getID());
            context.Tweets.add(tweet);
            logger.info("@"+owner.getUsername()+" posted a new tweet ");
            context.Tweets.updatePhoto(tweet,e.getImageFilePath());
        }
        if (!e.getText().isEmpty() && e.getImageFilePath() != null){
        }
    }
    public void deleteTweet(Tweet tweet){
        logger.info("@"+owner.getUsername()+" delete a tweet ");
        context.Tweets.remove(tweet);
    }
    public void addComment(Comment parent, CreatCommentEvent event) {
        if (!event.getText().isEmpty()) {
            parent.getComments().add(new Comment(owner.getID(), event.getText()));
            logger.info("@"+owner.getUsername()+" add a comment ");
        }
    }
    public void report(Tweet tweet) {
        UserController userController = new UserController();
        if (tweet.getID() != owner.getID()) {
            userController.block(context.Users.get(tweet.getUserID()));
            context.Tweets.report(tweet, owner);
            logger.info("@" + owner.getUsername() + " reported a tweet :"+tweet.getID());
        }
    }
}
