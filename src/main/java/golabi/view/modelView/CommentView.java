package golabi.view.modelView;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.listener.StringListener;
import golabi.listener.TweetListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CommentView extends JPanel implements ActionListener {
    private JLabel userLabel = new JLabel();
    private JLabel timeLabel ;
    private JButton commentBtn = new JButton("Comments");
    private JTextArea textArea ;
    private JCheckBox likeCheckBox ;
    private LinkedList<StringListener> stringListeners = new LinkedList<>();

    public CommentView(String commentText, String username, String profilePhotoPath ,long likes, boolean ownerLiked, String time,StringListener stringListener) {
        this.stringListeners.add(stringListener);
        this.setBackground(Commons.COMMENT_COLOR);
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "comment", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 10), Color.lightGray));
        Border outerBoarder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx =0.1;
        gc.weighty =0.1;

        /////////////1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 40);
        gc.anchor = GridBagConstraints.NORTHWEST;
        userLabel.setForeground(Color.WHITE);
        this.userLabel.setText("@"+username);
        if (profilePhotoPath != null)
            this.userLabel.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH,Commons.PROFILE_SMALL_IMG_HEIGHT,profilePhotoPath));
        this.add(userLabel,gc);
        //////////////2

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        textArea = new JTextArea(2,22);
        textArea.setBackground(Color.white);
        textArea.setCaretColor(Color.black);
        textArea.setForeground(Color.black);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.white,10));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.textArea.setText(commentText);
        this.add(scrollPane,gc);

        //////////////3
        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        commentBtn.setBackground(Commons.COMMENT_COLOR);
        commentBtn.setForeground(Color.white);
        commentBtn.setFocusable(false);
        commentBtn.setBorderPainted(false);
        commentBtn.addActionListener(this);
        this.add(commentBtn,gc);
        ///////////////4
        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(30, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        likeCheckBox = new JCheckBox();
        likeCheckBox.setIcon(Commons.DISLIKE_IMG);
        likeCheckBox.setSelectedIcon(Commons.LIKE_IMG);
        likeCheckBox.setBackground(Commons.COMMENT_COLOR);
        likeCheckBox.setBorderPainted(false);
        likeCheckBox.setFocusable(false);
        likeCheckBox.addActionListener(this);
        likeCheckBox.setForeground(Color.white);
        this.likeCheckBox.setText(String.valueOf(likes));
        if (ownerLiked)
            this.likeCheckBox.setSelected(true);
        else this.likeCheckBox.setSelected(false);
        this.add(likeCheckBox,gc);
        /////////////5
        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.white);
        timeLabel.setText(time);
        this.add(timeLabel, gc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            for (StringListener stringListener : stringListeners) {
                if (e.getSource() == commentBtn)
                    stringListener.stringEventOccurred("comment");
                if (e.getSource() == likeCheckBox) {
                if (likeCheckBox.isSelected())
                    stringListener.stringEventOccurred("like");
                else stringListener.stringEventOccurred("unLike");
            }
        }
    }

}
