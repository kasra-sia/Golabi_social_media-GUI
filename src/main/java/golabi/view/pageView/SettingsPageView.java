package golabi.view.pageView;

import golabi.Commons;
import golabi.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SettingsPageView extends JPanel implements ActionListener {
    private JButton logoutBtn = new JButton("Logout");
    private JButton deleteAccountBtn = new JButton("Delete Account");
    private JButton privacyBtn = new JButton("Privacy Settings");
    private LinkedList<StringListener> listenersList;

    public SettingsPageView() {
        listenersList = new LinkedList<>();
        this.setLayout(new GridLayout());
        this.setBounds(0, 0, Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.CENTER_PANEL_HEIGHT));

        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Settings Page", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.PINK));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        //////////////1
        logoutBtn.setBackground(Color.darkGray);
        logoutBtn.setFocusable(false);
        logoutBtn.setForeground(Color.white);
        logoutBtn.addActionListener(this);
        logoutBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(logoutBtn);
        //////////////2
        deleteAccountBtn.setBackground(Color.gray);
        deleteAccountBtn.setFocusable(false);
        deleteAccountBtn.setForeground(Color.white);
        deleteAccountBtn.addActionListener(this);
        deleteAccountBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(deleteAccountBtn);
        //////////////3
        privacyBtn.setBackground(Color.darkGray);
        privacyBtn.setFocusable(false);
        privacyBtn.setForeground(Color.white);
        privacyBtn.addActionListener(this);
        privacyBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(privacyBtn);
    }

    public void addListeners(StringListener stringListener) {
        this.listenersList.add(stringListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener listener : listenersList) {
            if (e.getSource() == logoutBtn)
                listener.stringEventOccurred("logout");
            if (e.getSource() == privacyBtn)
                listener.stringEventOccurred("privacySettings");
            if (e.getSource()==deleteAccountBtn)
                listener.stringEventOccurred("deleteAccount");
        }
    }
}
