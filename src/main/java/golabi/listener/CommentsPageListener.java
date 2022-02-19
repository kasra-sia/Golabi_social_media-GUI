package golabi.listener;
import golabi.controller.TweetController;
import golabi.model.Comment;
import golabi.model.Tweet;
import golabi.view.MainFrame;
import golabi.view.modelView.CommentView;
import golabi.view.pageView.commentsPageView.CommentsPageView;

import java.util.LinkedList;

public class CommentsPageListener implements StringListener {
    private static Comment parent;
    private static Tweet parentTweet;
    private CommentsPageView view;
    private TweetController controller = new TweetController();

    public CommentsPageListener(Comment parent, CommentsPageView view) {
        CommentsPageListener.parent = parent;
        this.view = view;
    }
    public CommentsPageListener(CommentsPageView view) {
        this.view = view;
    }

    public  void setParentTweet(Tweet parentTweet) {
        CommentsPageListener.parentTweet = parentTweet;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("openPage")) {
            LinkedList<CommentView> temp = new LinkedList<>();
            if (parent.getComments() != null)
                for (Comment comment : parent.getComments()) {
                    boolean flag1 = false;
                    for (Integer like : comment.getLikes()) {
                        if (like == controller.getOwner().getID()) {
                            flag1 = true;
                            break;
                        }
                    }
                    if (comment.getUserID() == controller.getOwner().getID()) {
                        CommentListener<CommentsPageView> commentListener = new CommentListener<CommentsPageView>(comment, view);
                        CommentView commentView = new CommentView(comment.getText(),
                                controller.getContext().Users.get(comment.getUserID()).getUsername(),
                                controller.getContext().Users.getPhoto(comment.getUserID()),
                                comment.getLikes().size(),
                                flag1,
                                comment.getTime(),
                                 commentListener);
                        temp.add(commentView);
                    }
                }
            view.getComponentsScrollPanel().setComponentsList(temp);
            view.getCommentPanel().setEventListener(new CreatCommentEventListener(view.getCommentPanel(),parent));
                updateTweet();
            view.repaint();
            view.revalidate();
            MainFrame.refreshFrame();
        }
    }
    public void updateTweet(){
        controller.getContext().Tweets.update(parentTweet);
    }
}
