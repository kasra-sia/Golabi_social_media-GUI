package golabi.listener.newMessageListeners;

import golabi.controller.ChatController;
import golabi.event.CreatTweetEvent;
import golabi.event.NewMessageEvent;
import golabi.listener.EventListener;

public class NewMessageEventListener implements EventListener {
    private ChatController controller = new ChatController();
    @Override
    public <T> void eventOccurred(T event) throws Exception {
        controller.newMessage((NewMessageEvent) event);
    }
}
