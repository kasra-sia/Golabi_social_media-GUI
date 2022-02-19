package golabi.listener;

import golabi.controller.ChatController;
import golabi.event.EditMessageEvent;
import golabi.model.chat.Message;

public class MessageListener implements EventListener,StringListener{
    private Message message;
    private ChatController chatController = new ChatController();

    public MessageListener(Message message) {
        this.message = message;
    }

    @Override
    public <T> void eventOccurred(T event) throws Exception {
        if (event instanceof EditMessageEvent) {
            chatController.editMessage(((EditMessageEvent) event));
        }
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("delete"))
            chatController.deleteMessage(message);
        if (string.equals("save"));
    }
}
