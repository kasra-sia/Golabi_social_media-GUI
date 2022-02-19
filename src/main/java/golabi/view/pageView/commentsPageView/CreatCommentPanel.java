package golabi.view.pageView.commentsPageView;

import golabi.Commons;
import golabi.event.CreatCommentEvent;
import golabi.listener.CreatCommentEventListener;
import golabi.listener.CreatTweetEventListener;
import golabi.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CreatCommentPanel extends JPanel implements ActionListener {
    private JTextArea commentTextArea;
    private JButton addCommentBtn = new JButton("add comment");
    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    private CreatCommentEventListener eventListener;
    public CreatCommentPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Commons.TWEET_COLOR, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;


        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 500, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        commentTextArea = new JTextArea(1, 50);
        commentTextArea.setBackground(Color.white);
        commentTextArea.setCaretColor(Color.black);
        commentTextArea.setForeground(Color.black);
        commentTextArea.setBorder(BorderFactory.createLineBorder(Color.white, 10));
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, gc);


        gc.gridx = 3;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        addCommentBtn.setFocusable(false);
        addCommentBtn.setBorderPainted(false);
        addCommentBtn.setBackground(Color.lightGray);
        addCommentBtn.addActionListener(this);
        this.add(addCommentBtn, gc);
    }

    public void addStringListener(StringListener stringListener) {
        this.stringListeners.add(stringListener);
    }

    public String getCommentText() {
        return commentTextArea.getText();
    }

    public void setEventListener(CreatCommentEventListener eventListener){
        this.eventListener = eventListener;
    }

    public void reset(){
        commentTextArea.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener stringListener : stringListeners){
            if (e.getSource() == addCommentBtn){
                try {
                    stringListener.stringEventOccurred("addComment");

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                  reset();
                  revalidate();
                  repaint();
        }
        }
    }
}
