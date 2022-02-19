package golabi.view.modelView;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.event.EditMessageEvent;
import golabi.listener.MessageListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageView extends JPanel implements ActionListener {
    private JTextArea textArea;
    private JLabel timeLabel;
    private JButton deleteBtn = new JButton("Delete");
    private JButton forwardBtn = new JButton("forward");
    private JButton saveMessageBtn = new JButton("saveMessage");
    private JLabel userLabel = new JLabel("@username");
    private JButton editBtn = new JButton("edit");
    private JLabel imageLabel;
    private int messageID;
    private Color backGroundColor;
    private MessageListener messageListener;
    public MessageView(String username, String profilePhotoPath, String messageImagePath, String text, String time, boolean isOwnersMessage, int messageID) {
        this.messageID = messageID;
        if (isOwnersMessage)
        backGroundColor = Commons.MY_MESSAGE_COLOR;
        else
        backGroundColor = Commons.OTHERS_MESSAGE_COLOR;
        this.setBackground(backGroundColor);
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "message", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 10), Color.lightGray));
        Border outerBoarder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx =0.1;
        gc.weighty =0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 40);
        gc.anchor = GridBagConstraints.NORTH;
        userLabel.setForeground(Color.white);
        this.userLabel.setText("@"+username);
        if (profilePhotoPath != null)
            this.userLabel.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH,Commons.PROFILE_SMALL_IMG_HEIGHT,profilePhotoPath));
        this.add(userLabel,gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        imageLabel = new JLabel();
        imageLabel.setBackground(Color.black);
        imageLabel.setOpaque(true);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.white,10));
        if (messageImagePath!=null)
            this.imageLabel.setIcon(ImageResizer.reSizeImage(Commons.TWEET_IMAGE_WIDTH,Commons.TWEET_IMAGE_HEIGHT,messageImagePath));
        this.add(imageLabel,gc);


        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        textArea = new JTextArea(4,22);
        textArea.setBackground(Color.white);
        textArea.setCaretColor(Color.black);
        textArea.setForeground(Color.black);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.white,10));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.textArea.setText(text);
        this.add(scrollPane,gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        deleteBtn.setBackground(backGroundColor);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.addActionListener(this);
        deleteBtn.setFocusable(false);
        deleteBtn.setBorderPainted(false);
        if (isOwnersMessage)
        this.add(deleteBtn,gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.SOUTH;
        editBtn.setBackground(backGroundColor);
        editBtn.setBorderPainted(false);
        editBtn.setFocusable(false);
        editBtn.setForeground(Color.white);
        editBtn.addActionListener(this);
        if (isOwnersMessage)
        this.add(editBtn,gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.SOUTH;
        forwardBtn.setBackground(backGroundColor);
        forwardBtn.setBorderPainted(false);
        forwardBtn.setFocusable(false);
        forwardBtn.setForeground(Color.white);
        forwardBtn.addActionListener(this);
        this.add(forwardBtn,gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        saveMessageBtn.setBackground(backGroundColor);
        saveMessageBtn.setBorderPainted(false);
        saveMessageBtn.setFocusable(false);
        saveMessageBtn.setForeground(Color.white);
        saveMessageBtn.addActionListener(this);
        this.add(saveMessageBtn,gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.gray);
        timeLabel.setText(time);
        this.add(timeLabel, gc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == deleteBtn)
                messageListener.stringEventOccurred("delete");
            if (e.getSource()==editBtn){
                String string = JOptionPane.showInputDialog(null,"edit  : ");
                try {
                    this.messageListener.eventOccurred(new EditMessageEvent(this,messageID,string));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (e.getSource() == forwardBtn)
                messageListener.stringEventOccurred("forward");

            if (e.getSource() == saveMessageBtn)
                messageListener.stringEventOccurred("save");
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
