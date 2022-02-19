package golabi.view.pageView;

import golabi.Commons;
import golabi.event.ChangePasswordFormEvent;
import golabi.listener.ChangePasswordEventListener;
import golabi.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PrivacySettingsPageView extends JPanel implements ActionListener {
    private JCheckBox privateAccountCBox = new JCheckBox("Private Account");
    private JButton changePasswordBtn = new JButton("Change Password");
    private LinkedList<StringListener> listenerList = new LinkedList<>();
    private JComboBox lastSeenComboBox;
    private JPanel lastSeen;
    private ChangePasswordEventListener changePasswordFormListener;


    public PrivacySettingsPageView() {

        this.setBounds(0, 0, Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(3, 3));
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Privacy Settings", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.PINK));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        //////////////0
        privateAccountCBox.setBackground(Color.darkGray);
        privateAccountCBox.setFocusable(false);
        privateAccountCBox.setForeground(Color.white);
        privateAccountCBox.addActionListener(this);
        privateAccountCBox.setFont(new Font(null, Font.BOLD, 25));
        privateAccountCBox.setIcon(Commons.SWITCH_OFF_IMG);
        privateAccountCBox.setSelectedIcon(Commons.SWITCH_ON_IMG);
        this.add(privateAccountCBox);
        //////////////1
        changePasswordBtn.setBackground(Color.gray);
        changePasswordBtn.setFocusable(false);
        changePasswordBtn.setForeground(Color.white);
        changePasswordBtn.addActionListener(this);
        changePasswordBtn.setFont(new Font(null, Font.BOLD, 25));

        this.add(changePasswordBtn);
        //////////////2
        lastSeen = new JPanel();
        JLabel label = new JLabel("set Last Seen");
        label.setFont(new Font(null, Font.BOLD, 25));
        label.setForeground(Color.white);
        lastSeen.add(label);
        lastSeen.setBackground(Color.DARK_GRAY);
        String[] as = {"everyOne", "noBody", "followings"};
        lastSeenComboBox = new JComboBox(as);
        lastSeenComboBox.setBackground(Color.gray);
        lastSeenComboBox.setFocusable(false);
        lastSeenComboBox.setForeground(Color.white);
        lastSeenComboBox.addActionListener(this);
        lastSeenComboBox.setFont(new Font(null, Font.BOLD, 20));
        lastSeen.add(lastSeenComboBox);
        this.add(lastSeen);


    }

    public void addStringListener(StringListener listener) {
        this.listenerList.add(listener);
    }

    public JCheckBox getPrivateAccountCBox() {
        return privateAccountCBox;
    }

    public JComboBox getLastSeenComboBox() {
        return lastSeenComboBox;
    }

    public void setChangePasswordFormListener(ChangePasswordEventListener listener) {
        changePasswordFormListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener listener : listenerList) {
            if (e.getSource() == privateAccountCBox) {
                if (privateAccountCBox.isSelected())
                    listener.stringEventOccurred("private");
                else listener.stringEventOccurred("public");
            }
            if (e.getSource() == lastSeenComboBox) {
                if (lastSeenComboBox.getSelectedItem().toString().equals("everyOne"))
                    listener.stringEventOccurred("everyOne");
                if (lastSeenComboBox.getSelectedItem().toString().equals("noBody"))
                    listener.stringEventOccurred("noBody");
                if (lastSeenComboBox.getSelectedItem().toString().equals("followings"))
                    listener.stringEventOccurred("followings");

            }
            if (e.getSource() == changePasswordBtn) {

                JPasswordField oldPasswordField = new JPasswordField();
                JPasswordField newPasswordField = new JPasswordField();
                int ans = JOptionPane.showConfirmDialog(null, changePassword(newPasswordField, oldPasswordField), "change password", JOptionPane.OK_CANCEL_OPTION);
                if (ans == 0) {
                    ChangePasswordFormEvent changePasswordFormEvent
                            = new ChangePasswordFormEvent(this,
                            oldPasswordField.getText(),
                            newPasswordField.getText());
                    try {
                        listener.stringEventOccurred("changePassword");
                        changePasswordFormListener.eventOccurred(changePasswordFormEvent);
                        JOptionPane.showMessageDialog(null, "password changed", null, JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception exception) {
                        if (exception.getMessage().equals("wrongPassword"))
                            JOptionPane.showMessageDialog(null, "wrong password", null, JOptionPane.ERROR_MESSAGE);
                        else exception.printStackTrace();
                    }
                }
            }
        }
    }
    private JPanel changePassword(JPasswordField newPasswordField, JPasswordField oldPasswordField) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;
//        /////////////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;


        oldPasswordField.setPreferredSize(new Dimension(150, 20));
        panel.add(oldPasswordField, gc);
//       /////////////////
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("new password: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;


        oldPasswordField.setPreferredSize(new Dimension(150, 20));
        newPasswordField.setPreferredSize(new Dimension(150, 20));
        panel.add(newPasswordField, gc);


        return panel;
    }
}
