package golabi.view;

import golabi.Commons;
import golabi.view.modelView.CommentView;
import golabi.view.modelView.FollowRequestView;
import golabi.view.modelView.TweetView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ComponentsScrollPanel<Child extends JComponent> extends JPanel {
    private LinkedList<Child> childrenList = new LinkedList<>();
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane;
    public ComponentsScrollPanel() {
        this.setBackground(Color.black);
        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel contentPane = new JPanel(new GridLayout());
        contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.add(scrollPane);
        this.add(contentPane);
        revalidate();
        repaint();
    }

    public void setComponent() {
        panel.setLayout(new GridBagLayout());
        if (!childrenList.isEmpty()) {
            if (childrenList.getFirst().getClass().equals(TweetView.class)) {
                scrollPane.setBackground(Commons.TWEET_BACKGROUND_COLOR);
                panel.setBackground(Commons.TWEET_BACKGROUND_COLOR);
            }
            if (childrenList.getFirst().getClass().equals(CommentView.class)) {
                scrollPane.setBackground(Commons.COMMENT_BACKGROUND_COLOR);
                panel.setBackground(Commons.COMMENT_BACKGROUND_COLOR);
            }
            if (childrenList.getFirst().getClass().equals(FollowRequestView.class)
            || childrenList.getFirst().getClass().equals(JPanel.class)) {
                scrollPane.setBackground(Commons.FOLLOW_REQUEST_BACKGROUND_COLOR);
                panel.setBackground(Commons.FOLLOW_REQUEST_BACKGROUND_COLOR);
            }
            int i = 0;
            for (Child child : childrenList) {
                GridBagConstraints gc = new GridBagConstraints();
                gc.gridx = 0;
                gc.gridy = i;
                gc.insets = new Insets(20, 0, 0, 0);
                gc.anchor = GridBagConstraints.SOUTH;
                panel.add(child, gc);
                i++;
            }
            revalidate();
            repaint();
        }
    }

    public void setComponentsList(LinkedList<Child> childrenList) {
        panel.removeAll();
        this.childrenList = childrenList;
        setComponent();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Commons.BACKGROUND_IMG.getImage(), 0, 0, null);
    }

}
