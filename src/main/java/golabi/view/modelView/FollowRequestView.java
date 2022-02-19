package golabi.view.modelView;

import golabi.Commons;
import golabi.event.FollowRequestEvent;
import golabi.event.SearchUserEvent;
import golabi.listener.EventListener;
import golabi.listener.SearchUserListener;
import golabi.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class FollowRequestView extends JPanel implements ActionListener {
    private final JButton acceptBtn;
    private final JButton declineBtn;
    private final JButton userBtn;
    private EventListener eventListener;
    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    private String username;
    public FollowRequestView(String username) {
        this.username = username;
        this.setBackground(Commons.FOLLOW_REQUEST_COLOR);
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "request", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 10), Color.lightGray));
        Border outerBoarder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx =0.1;
        gc.weighty =0.1;
//      1------------------
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 50, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        userBtn = new JButton("@"+username);
        userBtn.setBackground(Commons.FOLLOW_REQUEST_COLOR);
        userBtn.setFocusable(false);
        userBtn.setBorder(null);
        userBtn.addActionListener(this);
        this.add(userBtn,gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 100);
        gc.anchor = GridBagConstraints.NORTH;

        this.add(new Label(" want's to follow you"),gc);
//      2-------------------
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 40);
        gc.anchor = GridBagConstraints.LINE_START;
        acceptBtn = new JButton("accept");
        acceptBtn.addActionListener(this);
        this.add(acceptBtn,gc);

//      3--------------------
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        declineBtn = new JButton("decline");
        declineBtn.addActionListener(this);
        this.add(declineBtn,gc);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == declineBtn) {
            try {
                eventListener.eventOccurred(new FollowRequestEvent(this, username, false));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (e.getSource() == acceptBtn)
            try {
                eventListener.eventOccurred(new FollowRequestEvent(this, username, true));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        if (e.getSource() == userBtn) {
                stringListeners.forEach(stringListener -> {
                    try {
                        if (stringListener instanceof SearchUserListener)
                        ((SearchUserListener) stringListener).eventOccurred(new SearchUserEvent(this,username));
                        stringListener.stringEventOccurred("openProfilePage");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
        }

    }
    public void addStringListener(StringListener stringListener) {
        this.stringListeners.add(stringListener);
    }
}
