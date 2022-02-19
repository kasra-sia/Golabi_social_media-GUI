package golabi.view.pageView.messagingPage;

import golabi.model.chat.ChatAbstractClass;

public class SetMessagingPageForm {
    private ChatAbstractClass chatAbstractClass;
    private int unReads;
    private String name;

    public SetMessagingPageForm(ChatAbstractClass chatAbstractClass, int unReads, String name) {
        this.chatAbstractClass = chatAbstractClass;
        this.unReads = unReads;
        this.name = name;
    }

    public ChatAbstractClass getChatAbstractClass() {
        return chatAbstractClass;
    }

    public int getUnReads() {
        return unReads;
    }

    public String getName() {
        return name;
    }
}
