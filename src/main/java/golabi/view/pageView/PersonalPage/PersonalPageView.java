package golabi.view.pageView.PersonalPage;

import golabi.Commons;
import golabi.listener.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PersonalPageView extends JPanel implements ActionListener {
    private JButton showInfoBtn;
    private JButton notificationsPageBtn;
    private JButton myTweetsBtn;
    private JButton followersBtn;
    private JButton followingBtn;
    private JButton blackListBtn;
    private LinkedList<StringListener> stringListeners = new LinkedList<>();

    public PersonalPageView() {
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Personal page", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 25), Color.cyan));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridLayout gd = new GridLayout(4,2 );
        this.setLayout(gd);
//        -----------------notificationsPageBtn---------------
        notificationsPageBtn = new JButton("notifications");
        notificationsPageBtn.setBackground(Color.DARK_GRAY);
        notificationsPageBtn.setFocusable(false);
        notificationsPageBtn.setForeground(Color.white);
        notificationsPageBtn.addActionListener(this);
        notificationsPageBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(notificationsPageBtn);
//        -----------------showInfoBtn---------------
        showInfoBtn = new JButton("info");
        showInfoBtn.setBackground(Color.gray);
        showInfoBtn.setFocusable(false);
        showInfoBtn.setForeground(Color.white);
        showInfoBtn.addActionListener(this);
        showInfoBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(showInfoBtn);
//        -----------------myTweetsBtn---------------
        myTweetsBtn = new JButton("my tweets");
        myTweetsBtn.setBackground(Color.gray);
        myTweetsBtn.setFocusable(false);
        myTweetsBtn.setForeground(Color.white);
        myTweetsBtn.addActionListener(this);
        myTweetsBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(myTweetsBtn);

//        -----------------followersBtn---------------
        followersBtn = new JButton("Followers");
        followersBtn.setBackground(Color.darkGray);
        followersBtn.setFocusable(false);
        followersBtn.setForeground(Color.white);
        followersBtn.addActionListener(this);
        followersBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(followersBtn);
//        -----------------followingBtn---------------
        followingBtn = new JButton("Followings");
        followingBtn.setBackground(Color.gray);
        followingBtn.setFocusable(false);
        followingBtn.setForeground(Color.white);
        followingBtn.addActionListener(this);
        followingBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(followingBtn);
//        -----------------blackListBtn---------------
        blackListBtn = new JButton("Black List");
        blackListBtn.setBackground(Color.gray);
        blackListBtn.setFocusable(false);
        blackListBtn.setForeground(Color.white);
        blackListBtn.addActionListener(this);
        blackListBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(blackListBtn);
    }
    public void addStringListener(StringListener sl){
        stringListeners.add(sl);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener listener : stringListeners) {
            if (e.getSource() == blackListBtn)
                listener.stringEventOccurred("blackList");
            if (e.getSource() == followersBtn)
                listener.stringEventOccurred("followers");
            if (e.getSource() == followingBtn)
                listener.stringEventOccurred("followings");
            if (e.getSource() == myTweetsBtn)
                listener.stringEventOccurred("myTweets");
            if (e.getSource() == notificationsPageBtn)
                listener.stringEventOccurred("notifications");
            if (e.getSource() == showInfoBtn)
                listener.stringEventOccurred("showInfo");

        }
    }
}
