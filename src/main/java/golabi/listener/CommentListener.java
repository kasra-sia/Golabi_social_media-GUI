package golabi.listener;

import golabi.controller.TweetController;
import golabi.model.Comment;
import golabi.view.pageView.commentsPageView.CommentsPageView;

public class CommentListener<View> implements StringListener {
    private TweetController controller = new TweetController();
    private Comment comment;
    private View view;
    private CommentsPageListener commentPageListener1;

    public CommentListener(Comment comment, View view) {
        this.comment = comment;
        this.view = view;
            commentPageListener1 = new CommentsPageListener((CommentsPageView) view);
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("like")) {
            comment.getLikes().add(controller.getOwner().getID());
            commentPageListener1.stringEventOccurred("openPage");
        }
        if (string.equals("unLike")) {
            comment.getLikes().remove(Integer.valueOf(controller.getOwner().getID()));
            commentPageListener1.stringEventOccurred("openPage");
        }
        if (string.equals("comment")) {
            CommentsPageView commentsPageView =
                    new CommentsPageView();
            CommentsPageListener commentsPageListener = new CommentsPageListener(comment, commentsPageView);
            commentsPageListener.stringEventOccurred("openPage");
            ((CommentsPageView) view).getContainer().add(commentsPageView);
            ((CommentsPageView) view).getCardLayout().next(((CommentsPageView) view).getContainer());
            commentsPageView.addStringListener(commentsPageListener);
        }
    }
}

