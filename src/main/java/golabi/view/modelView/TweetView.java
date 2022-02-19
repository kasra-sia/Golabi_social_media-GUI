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

public class TweetView extends JPanel implements ActionListener {
    private JTextArea textArea;
    private JLabel timeLabel;
    private JButton deleteBtn = new JButton("Delete");
    private JButton commentBtn = new JButton("Comments");
    private JButton reTweetBtn = new JButton("Retweet");
    private JButton forwardBtn = new JButton("forward");
    private JButton saveTweetBtn = new JButton("saveTweet");
    private JButton reportTweetBtn = new JButton("report");
    private JCheckBox likeCheckBox ;
    private JLabel userLabel = new JLabel("@username");
    private JLabel imageLabel;
    private LinkedList<StringListener> stringListeners = new LinkedList<>() ;
    public TweetView(String tweetText,String username,String profilePhotoPath,String tweetImagePath,long likes,boolean ownerLiked,String time,TweetListener tweetListener) {
        this.stringListeners.add(tweetListener);
        this.setBackground(Commons.TWEET_COLOR);
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "tweet", javax.swing.border.
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
        if (tweetImagePath!=null)
            this.imageLabel.setIcon(ImageResizer.reSizeImage(Commons.TWEET_IMAGE_WIDTH,Commons.TWEET_IMAGE_HEIGHT,tweetImagePath));
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
        this.textArea.setText(tweetText);
        this.add(scrollPane,gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        deleteBtn.setBackground(Commons.TWEET_COLOR);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.addActionListener(this);
        deleteBtn.setFocusable(false);
        deleteBtn.setBorderPainted(false);
        this.add(deleteBtn,gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        commentBtn.setBackground(Commons.TWEET_COLOR);
        commentBtn.setForeground(Color.WHITE);
        commentBtn.setFocusable(false);
        commentBtn.setBorderPainted(false);
        commentBtn.addActionListener(this);
        this.add(commentBtn,gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.SOUTH;
        reTweetBtn.setBackground(Commons.TWEET_COLOR);
        reTweetBtn.setBorderPainted(false);
        reTweetBtn.setFocusable(false);
        reTweetBtn.setForeground(Color.white);
        reTweetBtn.addActionListener(this);
        this.add(reTweetBtn,gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.SOUTH;
        forwardBtn.setBackground(Commons.TWEET_COLOR);
        forwardBtn.setBorderPainted(false);
        forwardBtn.setFocusable(false);
        forwardBtn.setForeground(Color.white);
        forwardBtn.addActionListener(this);
        this.add(forwardBtn,gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        saveTweetBtn.setBackground(Commons.TWEET_COLOR);
        saveTweetBtn.setBorderPainted(false);
        saveTweetBtn.setFocusable(false);
        saveTweetBtn.setForeground(Color.white);
        saveTweetBtn.addActionListener(this);
        this.add(saveTweetBtn,gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        reportTweetBtn.setBackground(Commons.TWEET_COLOR);
        reportTweetBtn.setBorderPainted(false);
        reportTweetBtn.setFocusable(false);
        reportTweetBtn.setForeground(Color.white);
        reportTweetBtn.addActionListener(this);
        this.add(reportTweetBtn,gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        likeCheckBox = new JCheckBox();
        likeCheckBox.setIcon(Commons.DISLIKE_IMG);
        likeCheckBox.setSelectedIcon(Commons.LIKE_IMG);
        likeCheckBox.setBackground(Commons.TWEET_COLOR);
        likeCheckBox.setBorderPainted(false);
        likeCheckBox.setFocusable(false);
        likeCheckBox.addActionListener(this);
        likeCheckBox.setForeground(Color.white);
        this.likeCheckBox.setText(String.valueOf(likes));
        if (ownerLiked)
            this.likeCheckBox.setSelected(true);
        else this.likeCheckBox.setSelected(false);
        this.add(likeCheckBox,gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.gray);
        timeLabel.setText(time);
        this.add(timeLabel, gc);
    }
    public void setOthersTweet(){
        deleteBtn.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener stringListener:stringListeners ) {
            if (e.getSource() == commentBtn)
             stringListener.stringEventOccurred("comment");
            if (e.getSource() == reTweetBtn)
                stringListener.stringEventOccurred("reTweet");
            if (e.getSource() == deleteBtn)
                stringListener.stringEventOccurred("delete");
            if (e.getSource() == forwardBtn)
                stringListener.stringEventOccurred("forward");
            if (e.getSource() == reportTweetBtn)
                stringListener.stringEventOccurred("report");
            if (e.getSource() == saveTweetBtn)
                stringListener.stringEventOccurred("save");
            if (e.getSource() == likeCheckBox) {
                if(likeCheckBox.isSelected())
                    stringListener.stringEventOccurred("like");
                 else stringListener.stringEventOccurred("unLike");
            }


        }
    }
}

