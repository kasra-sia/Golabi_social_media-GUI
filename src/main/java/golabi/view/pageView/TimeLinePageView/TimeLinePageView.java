package golabi.view.pageView.TimeLinePageView;

import golabi.listener.StringListener;
import golabi.view.ComponentsScrollPanel;
import golabi.view.modelView.TweetView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class TimeLinePageView extends JPanel {
    private ComponentsScrollPanel componentsScrollPanel = new ComponentsScrollPanel();
    public TimeLinePageView() {
        this.setLayout(new BorderLayout());
        this.add(componentsScrollPanel,BorderLayout.CENTER);
    }
    public void setPage(LinkedList<TweetView> tweets){
        componentsScrollPanel.setComponentsList(tweets);
        repaint();
        revalidate();
    }

}
