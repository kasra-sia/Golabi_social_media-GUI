package golabi.listener;

import golabi.Commons;
import golabi.event.CreatCommentEvent;
import golabi.event.CreatTweetEvent;
import golabi.model.Comment;
import golabi.view.pageView.commentsPageView.CommentsPageView;
import golabi.view.pageView.commentsPageView.CreatCommentPanel;
import golabi.view.pageView.myTweetsPageView.CreatTweetPanel;
import golabi.view.pageView.myTweetsPageView.MyTweetsPageView;

public class CreatCommentPanelListener implements StringListener {
    private CreatCommentPanel view;
    private CommentsPageView commentsPageView;

    public CreatCommentPanelListener(CreatCommentPanel creatCommentPanel, CommentsPageView commentsPageView) {
        view = creatCommentPanel;
        this.commentsPageView = commentsPageView;
//        parent = commentsPageView.getParent()
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("addComment")) {
            try {
                CreatCommentEventListener creatCommentEventListener = new CreatCommentEventListener(view);
                view.setEventListener(creatCommentEventListener);
                creatCommentEventListener.eventOccurred(new CreatCommentEvent(
                        view,
                        view.getCommentText()));
                CommentsPageListener commentsPageListener = new CommentsPageListener(commentsPageView);
                commentsPageView.addStringListener(commentsPageListener);
                commentsPageListener.stringEventOccurred("openPage");
                view.reset();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
