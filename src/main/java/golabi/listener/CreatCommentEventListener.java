package golabi.listener;

import golabi.controller.TweetController;
import golabi.event.CreatCommentEvent;
import golabi.model.Comment;
import golabi.view.pageView.commentsPageView.CreatCommentPanel;
import golabi.view.pageView.myTweetsPageView.CreatTweetPanel;

public class CreatCommentEventListener implements EventListener{
     private CreatCommentPanel view;
    private TweetController controller = new TweetController();
    private static Comment parent;
    public CreatCommentEventListener(CreatCommentPanel view,Comment parent) {
        this.view = view;
        this.parent = parent;
    }

    public CreatCommentEventListener(CreatCommentPanel view) {
        this.view = view;
    }

    @Override
    public <T> void eventOccurred(T event) throws Exception {
        controller.addComment(parent,(CreatCommentEvent) event);
    }
}
