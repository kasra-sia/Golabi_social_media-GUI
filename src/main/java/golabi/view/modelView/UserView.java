package golabi.view.modelView;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.event.SetUserViewEvent;
import golabi.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class UserView extends JPanel implements ActionListener {
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("@username");
    private JTextField firstnameField = new JTextField(20);
    private JTextField lastnameField = new JTextField(20);
    private JLabel lastSeenLabel = new JLabel();
    private JTextField birthDayField = new JTextField(20);
    private JTextArea bioField = new JTextArea(3, 20);
    private JCheckBox followCB = new JCheckBox();
    private JCheckBox blockCB = new JCheckBox();
    private JCheckBox muteCB = new JCheckBox();
    private JLabel isPrivateLabel = new JLabel("");
    private JButton messageBtn = new JButton("message");
    private JButton reportBtn = new JButton("report");
    private JButton tweetsBtn = new JButton("tweets");
    private LinkedList<StringListener> stringListeners = new LinkedList<>();

    public UserView() {
        Border innerBorder = BorderFactory.createTitledBorder("profile page");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setPreferredSize(new Dimension(Commons.CENTER_PANEL_WIDTH, Commons.CENTER_PANEL_HEIGHT));
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;

//        -------------------userLabel--------------------------
        userLabel.setFont(new Font(null, Font.BOLD, 20));
        userLabel.setText("@username");
        this.add(userLabel);
        this.add(panel);
//        -------------------isPrivate label-----------------
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.WEST;
        isPrivateLabel.setForeground(Color.WHITE);
        isPrivateLabel.setBackground(Color.black);
        isPrivateLabel.setOpaque(true);
        isPrivateLabel.setFont(new Font(null, Font.BOLD, 20));
        panel.add(isPrivateLabel, gc);
//        -------------------firstname------------------
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("firstname :"), gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        firstnameField.setEditable(false);
        panel.add(firstnameField, gc);
//        -------------------lastname-------------------
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("lastname :"), gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        lastnameField.setEditable(false);
        panel.add(lastnameField, gc);
//        -------------------birthDay---------------------------
        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("birth day :"), gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        birthDayField.setEditable(false);
        panel.add(birthDayField, gc);
//        -------------------bio------------------
        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("bio :"), gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        bioField.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bioField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, gc);
//        -------------------last seen label--------------------
        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("last seen :"), gc);

        gc.gridx = 2;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(lastSeenLabel, gc);
//        -------------------follow checkBox--------------------
        gc.gridx = 1;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        followCB.setIcon(Commons.FOLLOW_IMG);
        followCB.setSelectedIcon(Commons.UNFOLLOW_IMG);
        followCB.addActionListener(this);
        panel.add(followCB, gc);
//        -------------------block checkBox---------------------
        gc.gridx = 1;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        blockCB.setIcon(Commons.BLOCK_IMG);
        blockCB.setSelectedIcon(Commons.UNBLOCK_IMG);
        blockCB.addActionListener(this);
        panel.add(blockCB, gc);
//        -------------------mute checkBox---------------------
        gc.gridx = 1;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        muteCB.setIcon(Commons.UNMUTE_IMG);
        muteCB.setSelectedIcon(Commons.MUTE_IMG);
        muteCB.addActionListener(this);
        panel.add(muteCB, gc);
//        -------------------messageBtn-------------------------
        gc.gridx = 1;
        gc.gridy = 8;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        messageBtn.setFocusable(false);
        messageBtn.addActionListener(this);
        messageBtn.setVisible(false);
        messageBtn.setEnabled(false);
        panel.add(messageBtn, gc);
//        -------------------reportBtn--------------------------
        gc.gridx = 1;
        gc.gridy = 9;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        reportBtn.addActionListener(this);
        reportBtn.setFocusable(false);
        panel.add(reportBtn, gc);
//        -------------------tweetsBtn--------------------------
        gc.gridx = 1;
        gc.gridy = 10;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        tweetsBtn.addActionListener(this);
        tweetsBtn.setFocusable(false);
        panel.add(tweetsBtn, gc);
    }

    public void addStringListener(StringListener stringListener) {
        this.stringListeners.add(stringListener);
    }

    public void setPage(SetUserViewEvent event) {
        if (event.getUserImgPath()!=null)
        this.userLabel.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_BIG_IMG_WIDTH, Commons.PROFILE_BIG_IMG_HEIGHT, event.getUserImgPath()));
        this.userLabel.setText("@"+event.getUsername());
        this.lastSeenLabel.setText(event.getLastSeen());
        this.firstnameField.setText(event.getFirstname());
        this.lastnameField.setText(event.getLastName());
        this.bioField.setText(event.getBio());
        this.birthDayField.setText(event.getBirthDay());
        if (event.isBlocked()) {
            this.followCB.setVisible(false);
            this.followCB.setEnabled(false);

            this.blockCB.setSelected(true);

            this.muteCB.setVisible(false);
            this.muteCB.setEnabled(false);

            this.messageBtn.setVisible(false);
            this.messageBtn.setEnabled(false);

            this.tweetsBtn.setVisible(false);
            this.tweetsBtn.setEnabled(false);
        }else {
            this.followCB.setVisible(true);
            this.followCB.setEnabled(true);

            this.blockCB.setSelected(false);

            this.muteCB.setVisible(true);
            this.muteCB.setEnabled(true);

            this.messageBtn.setVisible(true);
            this.messageBtn.setEnabled(true);

            this.tweetsBtn.setVisible(true);
            this.tweetsBtn.setEnabled(true);
        }
        if (event.isFollowed()){
            followCB.setSelected(true);
            this.messageBtn.setVisible(true);
            this.messageBtn.setEnabled(true);
        }else {
            followCB.setSelected(false);
            this.messageBtn.setVisible(false);
            this.messageBtn.setEnabled(false);
        }
        if (event.isPrivate())
            isPrivateLabel.setText("this page is private");
        else isPrivateLabel.setText("this page is public");
        this.lastSeenLabel.setText("last seen : "+event.getLastSeen());
    }
    public void showMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener stringListener : stringListeners) {
            if (e.getSource() == messageBtn)
                stringListener.stringEventOccurred("message");
            if (e.getSource() == tweetsBtn)
                stringListener.stringEventOccurred("tweets");
            if (e.getSource() == reportBtn)
                stringListener.stringEventOccurred("report");

            if (e.getSource() == muteCB) {
                if (muteCB.isSelected()) {
//                    muteCB.setSelected(true);
                    stringListener.stringEventOccurred("mute");
                } else {
                    stringListener.stringEventOccurred("unmute");
                }
            }
            if (e.getSource() == followCB) {
                if (followCB.isSelected()) {
                    followCB.setSelected(false);
                    stringListener.stringEventOccurred("follow");
                } else {
                    followCB.setSelected(true);
                    stringListener.stringEventOccurred("unfollow");
                }
            }
            if (e.getSource() == blockCB) {
                if (blockCB.isSelected()) {
//                    blockCB.setSelected(false);/
                    stringListener.stringEventOccurred("block");
                } else {
//                    blockCB.setSelected(true);
                    stringListener.stringEventOccurred("unblock");
                }
            }
        }
    }
}
