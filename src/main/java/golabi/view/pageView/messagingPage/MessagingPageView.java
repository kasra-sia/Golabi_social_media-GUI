package golabi.view.pageView.messagingPage;

import golabi.event.CreatGroupEvent;
import golabi.event.OpenChatRoomEvent;
import golabi.listener.EventListener;
import golabi.listener.StringListener;
import golabi.view.ComponentsScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MessagingPageView extends JPanel implements ActionListener {
    private ComponentsScrollPanel<JPanel> componentsScrollPanel;
    private LinkedList<StringListener> stringListeners  =new LinkedList<>();
    private EventListener eventListener;
    private JButton newGroup = new JButton("new group");

    public MessagingPageView() {
        setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel<>();
        this.add(componentsScrollPanel,BorderLayout.CENTER);
        newGroup.addActionListener(this);
        this.add(newGroup,BorderLayout.NORTH);
    }

    public void addStringListener(StringListener stringListener) {
        this.stringListeners.add(stringListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGroup){
            String groupName;
            groupName = JOptionPane.showInputDialog(null,"enter your group name:");
            try {
                eventListener.eventOccurred(new CreatGroupEvent(groupName));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    public void setPage(LinkedList<SetMessagingPageForm> setMessagingPageForms){
       LinkedList<JPanel> panels = new LinkedList<>();
        setMessagingPageForms.forEach(form->{
            if (!form.getChatAbstractClass().isGroup()){
                JPanel temp = new JPanel();
                JButton button = new JButton(form.getName()+" :      "+form.getUnReads());
                temp.add(button);
                button.setBackground(Color.WHITE);
                button.setBorder(null);
                temp.setBackground(Color.WHITE);
                panels.add(temp);
                button.addActionListener(e-> {
                        try {
                            eventListener.eventOccurred(new OpenChatRoomEvent(this,false, form.getName(),form.getChatAbstractClass().getID()));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                });
            }else {
                JPanel temp = new JPanel();
                JButton button = new JButton(form.getName()+" :      "+form.getUnReads());
                temp.add(button);
                button.setBackground(Color.WHITE);
                button.setBorder(null);
                temp.setBackground(Color.WHITE);
                panels.add(temp);
                button.addActionListener(e -> {
                    try {
                        eventListener.eventOccurred(new OpenChatRoomEvent(this,true, form.getName(),form.getChatAbstractClass().getID()));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }
        });
        componentsScrollPanel.setComponentsList(panels);
    }
}
