package golabi.listener;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.controller.ChatController;
import golabi.event.AddMemberEvent;
import golabi.model.account.User;
import golabi.model.chat.GroupChat;
import golabi.view.ComponentsScrollPanel;
import golabi.view.MainPanel;
import golabi.view.pageView.UsersListPageView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class GroupListener implements EventListener ,StringListener{
    private GroupChat groupChat;
    private ChatController controller = new ChatController();
    public GroupListener(GroupChat groupChat) {
        this.groupChat = groupChat;

    }

    @Override
    public <T> void eventOccurred(T event) throws Exception {
        if(event instanceof AddMemberEvent){

        }
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("showMembers")){
            UsersListPageView usersListPageView = new UsersListPageView();
            LinkedHashMap<String, ImageIcon> map = new LinkedHashMap<>();
            groupChat.getUsersID().forEach(user->{
                User user1 =controller.getContext().Users.get(user);
                ImageIcon imageIcon = ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH,
                        Commons.PROFILE_SMALL_IMG_HEIGHT,
                        controller.getContext().Users.getPhoto(user));
                map.put(user1.getUsername(),imageIcon);
            });
            usersListPageView.setPage(map);
            usersListPageView.addStringListener(new SearchUserListener());
            MainPanel.container.add(usersListPageView);
            MainPanel.cardLayout.next(MainPanel.container);
            MainPanel.container.repaint();
            MainPanel.container.revalidate();
        }
        if (string.equals("addMember")){
            DefaultListModel<String> listModel = new DefaultListModel<>();
            controller.getOwner().getProfile().getFollowers().forEach(
                    follower->{
                        if (!groupChat.getUsersID().contains(follower))
                        listModel.addElement(controller.getContext().Users.get(follower).getUsername()); }
            );
            controller.getOwner().getProfile().getFollowing().forEach(
                    following->{
                        if (!groupChat.getUsersID().contains(following))
                        listModel.addElement(controller.getContext().Users.get(following).getUsername()); }
            );
            JFrame frame = new JFrame();
            frame.setVisible(true);
            JList<String> jList = new JList<>(listModel);
            jList.setBackground(Color.DARK_GRAY);
            jList.setBorder(BorderFactory.createLineBorder(Color.black));
            jList.setForeground(Color.WHITE);
            JButton selectBtn = new JButton("select");
            selectBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jList.getSelectedIndex()!=-1) {
                        final int[] id = new int[1];
                        controller.getContext().Users.all().forEach(user -> {
                            if (user.getUsername().equals(jList.getSelectedValue()))
                                id[0] = user.getID();
                        });
                        controller.addMemberToGroup(new AddMemberEvent(
                                this,
                                id[0],
                                groupChat.getID()
                                ));
                    }
                    frame.show(false);
                }
            });
            frame.setLayout(new BorderLayout());

            frame.add(new JLabel("select user :"),BorderLayout.NORTH);
            frame.add(selectBtn,BorderLayout.SOUTH);
            frame.add(jList,BorderLayout.CENTER);
            frame.show();
            frame.setBounds(0,0,300,300);
        }
    }
}
