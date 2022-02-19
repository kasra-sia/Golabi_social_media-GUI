package golabi.view.pageView.explorerPageView;

import golabi.view.ComponentsScrollPanel;
import golabi.view.modelView.TweetView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ExplorerGlobalView extends JPanel {
    private ComponentsScrollPanel componentsScrollPanel = new ComponentsScrollPanel();
    public ExplorerGlobalView() {
        this.setLayout(new BorderLayout());
        this.add(componentsScrollPanel,BorderLayout.CENTER);
    }

    public void setPage(LinkedList<TweetView> tweets){
        componentsScrollPanel.setComponentsList(tweets);
        repaint();
        revalidate();
    }
}