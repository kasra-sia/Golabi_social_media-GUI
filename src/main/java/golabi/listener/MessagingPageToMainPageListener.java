package golabi.listener;

import golabi.controller.ChatController;
import golabi.util.Loop;
import golabi.view.MainPanel;
import golabi.view.pageView.messagingPage.MessagingPageView;
import golabi.view.pageView.messagingPage.SetMessagingPageForm;

import java.awt.*;
import java.util.*;

public class MessagingPageToMainPageListener implements StringListener {
    private MessagingPageView messagingPageView;
    private ChatController controller = new ChatController();
    private Container container = MainPanel.container;
    private CardLayout cardLayout =MainPanel.cardLayout;
    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("messagingPage")){
            messagingPageView = new MessagingPageView();
            container.add(messagingPageView);
            controller.creatChatRooms();
            cardLayout.next(container);
            container.revalidate();
            container.repaint();
            messagingPageView.setEventListener(new MessagingPageEventListener());
            if (MainPanel.loop!=null)
                MainPanel.loop.stop();
            MainPanel.loop = new Loop(1,this::updateMessagingPage);
            MainPanel.loop.start();
        }
    }
    private void updateMessagingPage(){
        LinkedList<SetMessagingPageForm> forms = new LinkedList<>();
        if (controller.getContext().ChatRooms.all()!=null)
        controller.getContext().ChatRooms.all().forEach(chatRoom -> {
            if(Arrays.stream(chatRoom.getUsersID()).anyMatch(n->n==controller.getOwner().getID())) {
                final int[] unreadCount = {0};
                controller.getContext().Messages.all().forEach(message ->{
                    if (message.getChatRoomID() == chatRoom.getID())
                        if (!message.getUsersIDSeen().contains(controller.getOwner().getID())
                          && message.getSenderID()!=controller.getOwner().getID())
                            unreadCount[0]++;
                });
                SetMessagingPageForm temp = new SetMessagingPageForm(
                        chatRoom
                        ,unreadCount[0]
                        ,controller.getContext().Users.get(chatRoom.getOther(controller.getOwner().getID())).getUsername());
                forms.add(temp);
            }
        });
        if (controller.getContext().GroupChats.all()!=null)
            controller.getContext().GroupChats.all().forEach(groupChat -> {
            if (groupChat.getUsersID().contains(controller.getOwner().getID())){
                final int[] unreadCount = {0};
                controller.getContext().Messages.all().forEach(message ->{
                    if (message.getChatRoomID() == groupChat.getID())
                        if (!message.getUsersIDSeen().contains(controller.getOwner().getID())
                         && message.getSenderID()!=controller.getOwner().getID())
                            unreadCount[0]++;
                });
                SetMessagingPageForm temp = new SetMessagingPageForm(
                        groupChat
                        ,unreadCount[0]
                        ,groupChat.getGpName()
                        );
                forms.add(temp);
            }
        });
        forms.sort(Comparator.comparingInt(SetMessagingPageForm::getUnReads));
        Collections.reverse(forms);
        messagingPageView.setPage(forms);
    }

}
