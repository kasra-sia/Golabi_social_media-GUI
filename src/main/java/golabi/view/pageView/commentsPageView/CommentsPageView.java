package golabi.view.pageView.commentsPageView;

import golabi.listener.*;
import golabi.view.ComponentsScrollPanel;
import golabi.view.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CommentsPageView extends JPanel {
    private CreatCommentPanel commentPanel ;
    private ComponentsScrollPanel componentsScrollPanel = new ComponentsScrollPanel();
    private CreatCommentPanelListener creatCommentPanelListener;
    private LinkedList<StringListener> stringListener = new LinkedList<>();
    private Container container ;
    private CardLayout cardLayout;
    public CommentsPageView() {
        commentPanel = new CreatCommentPanel();
        this.container = MainPanel.container;
        this.cardLayout = MainPanel.cardLayout;
        this.setLayout(new BorderLayout());
        this.add(commentPanel, BorderLayout.SOUTH);
        creatCommentPanelListener = new CreatCommentPanelListener(commentPanel,this);
        commentPanel.addStringListener(creatCommentPanelListener);
        this.add(componentsScrollPanel, BorderLayout.CENTER);
    }
    public void addStringListener(StringListener stringListener) {
        this.stringListener.add(stringListener);
    }

    public ComponentsScrollPanel getComponentsScrollPanel() {
        return componentsScrollPanel;
    }

    public Container getContainer() {
        return container;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public CreatCommentPanel getCommentPanel() {
        return commentPanel;
    }
}
