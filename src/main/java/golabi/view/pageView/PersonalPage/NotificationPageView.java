package golabi.view.pageView.PersonalPage;

import golabi.Commons;
import golabi.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class NotificationPageView extends JPanel implements ActionListener {
    private final JButton receivedRequestsBtn = new JButton("receivedRequests");
    private final JButton sentRequestsBtn = new JButton("sentRequests");
    private final JButton systemNotificationsBtn = new JButton("systemNotifications");
    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    public NotificationPageView() {
        this.setLayout(new GridLayout());
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Notification page", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 25), Color.cyan));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        this.add(receivedRequestsBtn);
        receivedRequestsBtn.addActionListener(this);
        this.add(systemNotificationsBtn);
        systemNotificationsBtn.addActionListener(this);
        this.add(sentRequestsBtn);
        sentRequestsBtn.addActionListener(this);
    }

    public void addStringListener(StringListener stringListener) {
        this.stringListeners.add(stringListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(receivedRequestsBtn))
            stringListeners.forEach(s->s.stringEventOccurred("receivedRequestsPage"));
        if (e.getSource().equals(systemNotificationsBtn))
            stringListeners.forEach(s->s.stringEventOccurred("systemNotificationsPage"));
        if (e.getSource().equals(sentRequestsBtn))
            stringListeners.forEach(s->s.stringEventOccurred("sentRequestsPage"));
    }
}
