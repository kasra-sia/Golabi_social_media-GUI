package golabi.listener;

import golabi.controller.ChatController;
import golabi.event.CreatGroupEvent;
import golabi.event.OpenChatRoomEvent;
import golabi.model.chat.Message;
import golabi.util.Loop;
import golabi.view.MainPanel;
import golabi.view.modelView.MessageView;
import golabi.view.pageView.messagingPage.chatRoom.ChatRoomView;

import java.util.LinkedList;

public class MessagingPageEventListener implements EventListener {
    private ChatController controller = new ChatController();
    private ChatRoomView chatRoomView;
    private int currentChatroomID;
    private boolean isGroup;

    @Override
    public <T> void eventOccurred(T event) throws Exception {
        if (event instanceof CreatGroupEvent) {
            ((CreatGroupEvent) event).setOwnerID(controller.getOwner().getID());
            controller.creatGroup((CreatGroupEvent) event);
        }
        if (event instanceof OpenChatRoomEvent) {
            currentChatroomID = ((OpenChatRoomEvent) event).getChatroomID();
            isGroup = ((OpenChatRoomEvent) event).isGroup();
            chatRoomView = new ChatRoomView(currentChatroomID);
            if (isGroup)
                chatRoomView.setGroupListener(new GroupListener(controller.getContext().GroupChats.get(currentChatroomID)));
            MainPanel.loop = new Loop(1, this::setChatRoomPage);
            MainPanel.loop.start();
            MainPanel.container.add(chatRoomView);
            MainPanel.cardLayout.next(MainPanel.container);
        }
    }

    public void setChatRoomPage() {
        LinkedList<MessageView> messageViews = new LinkedList<>();
        if (controller.getContext().Messages.all() != null)
            controller.getContext().Messages.all().forEach(
                    message -> {
                        if (message.getChatRoomID() == currentChatroomID) {
                            MessageView messageView = new MessageView(
                                    controller.getContext().Users.get(message.getSenderID()).getUsername(),
                                    controller.getContext().Users.getPhoto(message.getSenderID()),
                                    controller.getContext().Messages.getPhoto(message.getID()),
                                    message.getText(),
                                    message.getTime(),
                                    message.getSenderID() == controller.getOwner().getID(),
                                    message.getID()
                            );
                            messageView.setMessageListener(new MessageListener(message));
                            messageViews.add(messageView);
                            if (!message.getUsersIDSeen().contains(controller.getOwner().getID())) {
                                message.getUsersIDSeen().add(controller.getOwner().getID());
                                controller.getContext().Messages.update(message);
                            }
                        }
                    }
            );
        chatRoomView.setPage(messageViews,isGroup);
    }
}
