package golabi.view.pageView.messagingPage.chatRoom;

import golabi.event.AddMemberEvent;
import golabi.listener.GroupListener;
import golabi.listener.newMessageListeners.NewMessageEventListener;
import golabi.listener.newMessageListeners.NewMessageStringListener;
import golabi.view.ComponentsScrollPanel;
import golabi.view.modelView.MessageView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ChatRoomView extends JPanel {
    private NewMessagePanel newMessagePanel;
    private NewMessageStringListener newMessageStringListener;
    private ComponentsScrollPanel<MessageView> componentsScrollPanel = new ComponentsScrollPanel();
    private JButton addMemberBtn = new JButton("addMember");
    private JButton showMembersBtn = new JButton("showMembers");
    private GroupListener groupListener;
    public ChatRoomView(int id) {
        this.setLayout(new BorderLayout());
        newMessagePanel = new NewMessagePanel();
        this.add(newMessagePanel, BorderLayout.SOUTH);
        newMessageStringListener = new NewMessageStringListener(newMessagePanel,id);
        newMessagePanel.addStringListener(newMessageStringListener);
        newMessagePanel.setEventListener(new NewMessageEventListener());
        this.add(componentsScrollPanel, BorderLayout.CENTER);
        newMessagePanel = new NewMessagePanel();

        addMemberBtn.setVisible(false);
        addMemberBtn.addActionListener(e->groupListener.stringEventOccurred("addMember"));
        this.add(addMemberBtn,BorderLayout.EAST);

        showMembersBtn.setVisible(false);
        showMembersBtn.addActionListener(e->groupListener.stringEventOccurred("showMembers"));
        this.add(showMembersBtn,BorderLayout.WEST);



    }
    public void setPage(LinkedList<MessageView> panels,boolean isGroup){
        if (isGroup){
            showMembersBtn.setVisible(true);
            addMemberBtn.setVisible(true);
        }
        componentsScrollPanel.setComponentsList(panels);
        componentsScrollPanel.repaint();
        componentsScrollPanel.revalidate();
        repaint();
        revalidate();
    }

    public void setGroupListener(GroupListener groupListener) {
        this.groupListener = groupListener;
    }
}
