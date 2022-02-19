package golabi.listener;

import golabi.controller.UserController;
import golabi.model.RequestStatus;
import golabi.util.Loop;
import golabi.view.MainPanel;
import golabi.view.pageView.PersonalPage.ReceivedRequestsPageView;
import golabi.view.pageView.PersonalPage.SentRequestsPageView;
import golabi.view.pageView.PersonalPage.SystemNotificationsPage;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class NotificationPageListener implements StringListener{
    private ReceivedRequestsPageView receivedRequestsPageView;
    private SentRequestsPageView sentRequestsPageView;
    private SystemNotificationsPage systemNotificationsPage;
    private UserController controller ;
    private Container container = MainPanel.container;
    private CardLayout cardLayout = MainPanel.cardLayout;

    public NotificationPageListener() {
        controller = new UserController();
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("receivedRequestsPage")){
            receivedRequestsPageView = new ReceivedRequestsPageView();
            container.add(receivedRequestsPageView);
            cardLayout.next(container);
            MainPanel.loop = new Loop(2,this::updateReceivedRequestPage);
            MainPanel.loop.start();
            container.revalidate();
            container.repaint();
        }
        if (string.equals("sentRequestsPage")){
            sentRequestsPageView = new SentRequestsPageView();
            container.add(sentRequestsPageView);
            cardLayout.next(container);

            if (MainPanel.loop!=null)
                MainPanel.loop.stop();
            MainPanel.loop = new Loop(2,this::updateSentRequestsPage);
            MainPanel.loop.start();
        }
        if (string.equals("systemNotificationsPage")){
            systemNotificationsPage = new SystemNotificationsPage();
            container.add(systemNotificationsPage);
            cardLayout.next(container);

            if (MainPanel.loop!=null)
                MainPanel.loop.stop();
            MainPanel.loop = new Loop(2,this::updateSystemNotificationsPage);
            MainPanel.loop.start();

        }
    }
    private void updateReceivedRequestPage(){
            LinkedList<String>temp = new LinkedList<>();
            controller.getOwner().getProfile().getReceivedRequest().forEach(
                    request -> temp.add(controller.getContext().Users.get(request.getSenderID()).getUsername())
            );
            receivedRequestsPageView.setPage(temp);
        container.repaint();
        container.repaint();
        }
    private void updateSystemNotificationsPage(){
        systemNotificationsPage.setPage(controller.getOwner().getProfile().getSystemNotifications());
        container.repaint();
        container.repaint();
    }
    private void updateSentRequestsPage(){
        LinkedHashMap<String, RequestStatus> map = new LinkedHashMap<>();
        controller.getOwner().getProfile().getSentRequest().forEach(request -> {
            map.put(controller.getContext().Users.get(request.getReceiverID()).getUsername(),request.getRequestStatus());
        });
        sentRequestsPageView.setPage(map);
        container.repaint();
        container.repaint();
    }
}
