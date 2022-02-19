package golabi.view.pageView.PersonalPage;

import golabi.model.Notification;
import golabi.view.ComponentsScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SystemNotificationsPage extends JPanel   {
    private final ComponentsScrollPanel<JPanel> componentsScrollPanel;
    public SystemNotificationsPage() {
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel<>();
        this.add(componentsScrollPanel,BorderLayout.CENTER);

    }
    public void setPage(LinkedList<Notification> notifications){
        LinkedList<JPanel> temp = new LinkedList<>();
        notifications.forEach(notification->{
            JPanel temp1 = new JPanel();
            temp1.add(new JLabel( notification.toString()));

            temp.add(temp1);
        });
            componentsScrollPanel.setComponentsList(temp);
    }
}
