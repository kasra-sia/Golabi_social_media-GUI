package golabi.view.pageView.myTweetsPageView;

import golabi.listener.CreatTweetEventListener;
import golabi.listener.CreatTweetPanelListener;
import golabi.listener.StringListener;
import golabi.view.ComponentsScrollPanel;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MyTweetsPageView extends JPanel {
    private CreatTweetPanel creatTweetPanel = new CreatTweetPanel();
    private ComponentsScrollPanel componentsScrollPanel = new ComponentsScrollPanel();
    private LinkedList<StringListener> stringListener = new LinkedList<>();
    private CreatTweetPanelListener creatTweetPanelListener;
    private Container container;
    private CardLayout cardLayout;
    public MyTweetsPageView(Container container,CardLayout cardLayout) {
        this.container = container;
        this.cardLayout = cardLayout;
        this.setLayout(new BorderLayout());
        this.add(creatTweetPanel, BorderLayout.SOUTH);
        creatTweetPanelListener = new CreatTweetPanelListener(creatTweetPanel);
        creatTweetPanel.addStringListener(creatTweetPanelListener);
        creatTweetPanel.setEventListener(new CreatTweetEventListener(creatTweetPanel));
        this.add(componentsScrollPanel, BorderLayout.CENTER);
    }

    public void addStringListener(StringListener stringListener) {
        this.stringListener.add(stringListener);
    }

    public ComponentsScrollPanel getTweetsScrollPanel() {
        return componentsScrollPanel;
    }

    public Container getContainer() {
        return container;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}

