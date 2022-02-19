package golabi.view.pageView.messagingPage.chatRoom;

import golabi.Commons;
import golabi.listener.CreatTweetEventListener;
import golabi.listener.StringListener;
import golabi.listener.newMessageListeners.NewMessageEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class NewMessagePanel extends JPanel implements ActionListener, MouseListener {
    private JTextArea messageTextArea;
    private JButton selectImageBtn = new JButton();
    private JButton sendBtn = new JButton("send");
    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    private JTextArea showImagePath;
    private NewMessageEventListener eventListener;

    public NewMessagePanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Commons.OTHERS_MESSAGE_COLOR, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.WEST;
        showImagePath = new JTextArea(1, 50);
        showImagePath.setBackground(Color.lightGray);
        showImagePath.setCaretColor(Color.black);
        showImagePath.setForeground(Color.black);
        showImagePath.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));
        showImagePath.setLineWrap(true);
        showImagePath.setWrapStyleWord(true);
        showImagePath.setEditable(false);
        showImagePath.addMouseListener(this);
        JScrollPane scrollPane1 = new JScrollPane(showImagePath);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane1, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        messageTextArea = new JTextArea(1, 50);
        messageTextArea.setBackground(Color.white);
        messageTextArea.setCaretColor(Color.black);
        messageTextArea.setForeground(Color.black);
        messageTextArea.setBorder(BorderFactory.createLineBorder(Color.white, 10));
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, gc);


        gc.gridx = 3;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        sendBtn.setFocusable(false);
        sendBtn.setBorderPainted(false);
        sendBtn.setBackground(Color.lightGray);
        sendBtn.addActionListener(this);
        this.add(sendBtn, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        selectImageBtn.setFocusable(false);
        selectImageBtn.setBorderPainted(false);
        selectImageBtn.setBackground(Color.lightGray);
        selectImageBtn.setIcon(Commons.ATTACH_IMG);
        selectImageBtn.addActionListener(this);
        this.add(selectImageBtn, gc);
    }

    public void addStringListener(StringListener stringListener) {
        this.stringListeners.add(stringListener);
    }

    public void setEventListener(NewMessageEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void reset() {
        showImagePath.setText("");
        messageTextArea.setText("");
    }

    public JTextArea getShowImagePath() {
        return showImagePath;
    }

    public String getMessageText() {
        return messageTextArea.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener stringListener : stringListeners) {
            if (selectImageBtn == e.getSource())
                stringListener.stringEventOccurred("selectImage");
            if (sendBtn == e.getSource()) {
                stringListener.stringEventOccurred("send");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (StringListener stringListener : stringListeners) {
            if (showImagePath.contains(e.getPoint())) {
                stringListener.stringEventOccurred("showImage");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
