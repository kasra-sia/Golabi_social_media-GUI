package golabi.view;

import golabi.listener.*;
import golabi.util.Loop;
import golabi.view.pageView.MainPageView;
import golabi.view.pageView.TimeLinePageView.TimeLinePageView;
import golabi.view.pageView.explorerPageView.ExplorerGlobalView;
import golabi.view.pageView.myTweetsPageView.MyTweetsPageView;


import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private ToolsBar toolsBar;
    private CenterPanel centerPanel;
    private MainPageView mainPageView;
    private CenterToToolbarStringListener centerToToolbarStringListener;
    public static Container container;
    public static CardLayout cardLayout;
    private static MainPanel instance;
    public static Loop loop;


    public MainPanel() {

        this.setLayout(new BorderLayout());
        centerPanel = new CenterPanel();

        this.add(centerPanel, BorderLayout.CENTER);
        toolsBar = new ToolsBar();

        this.add(toolsBar, BorderLayout.NORTH);
        instance = this;
        centerToToolbarStringListener = new CenterToToolbarStringListener(centerPanel);
        toolsBar.addListener(centerToToolbarStringListener);

        centerToToolbarStringListener.setStringListener(new StringListener() {
            @Override
            public void stringEventOccurred(String string) {
                if (string.equals("userLoggedIn")) {
                    centerPanel.removeAll();
                    toolsBar.update();
                    cardLayout = new CardLayout();
                    container = centerPanel;
                    container.setLayout(cardLayout);
                    mainPageView = new MainPageView();
                    container.add(mainPageView);
                    mainPageView.addListener(new SettingsPageToMainPageListener(container, cardLayout));
                    mainPageView.addListener(new PersonalPageToMainPageListener(container,cardLayout));
                    mainPageView.addListener(new ExplorerPageToMainPageListener(container,cardLayout));
                    mainPageView.addListener(new TimeLinePageToMainPageListener());
                    mainPageView.addListener(new MessagingPageToMainPageListener());
                    MainFrame.refreshFrame();
                }
            }
        });

        toolsBar.addListener(string -> {
            if (string.equals("back") && container.getComponents().length != 1) {
                cardLayout.previous(container);
                container.remove(container.getComponents().length - 1);
                container.repaint();
                container.revalidate();
                if(loop!=null){
                    if (!(container.getComponents()[container.getComponents().length-1] instanceof MyTweetsPageView
                    || container.getComponents()[container.getComponents().length-1] instanceof TimeLinePageView)
                    || container.getComponents()[container.getComponents().length-1] instanceof ExplorerGlobalView)
                    loop.stop();
                }
            }
            if (string.equals("backToMainPage")) {
                if (container.getComponents().length != 1) {
                    container.removeAll();
                    container = centerPanel;
                    container.setLayout(cardLayout);
                    container.add(mainPageView);
                    MainFrame.refreshFrame();
                }
                if(loop!=null)
                    loop.stop();
            }
        });
    }
}