package golabi.view.pageView.PersonalPage;

import golabi.listener.ReceivedRequestsEventListener;
import golabi.listener.SearchUserListener;
import golabi.listener.StringListener;
import golabi.view.ComponentsScrollPanel;
import golabi.view.modelView.FollowRequestView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ReceivedRequestsPageView extends JPanel {
    private ComponentsScrollPanel<FollowRequestView> componentsScrollPanel = new ComponentsScrollPanel();
    private LinkedList<StringListener> stringListener = new LinkedList<>();
    public ReceivedRequestsPageView() {
        this.setLayout(new BorderLayout());
        this.add(componentsScrollPanel, BorderLayout.CENTER);
    }
    public void setPage(LinkedList<String> usernames){
        LinkedList<FollowRequestView> temp = new LinkedList<>();
        for (String username:usernames) {
            FollowRequestView followRequestView = new FollowRequestView(username);
            temp.add(followRequestView);
            followRequestView.setEventListener(new ReceivedRequestsEventListener());
            followRequestView.addStringListener(new SearchUserListener());
        }
        componentsScrollPanel.setComponentsList(temp);
        componentsScrollPanel.revalidate();
        componentsScrollPanel.repaint();
    }
}
