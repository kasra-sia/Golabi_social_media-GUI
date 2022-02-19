package golabi.view.pageView;

import golabi.ImageResizer;
import golabi.event.SearchUserEvent;
import golabi.listener.SearchUserListener;
import golabi.listener.StringListener;
import golabi.view.ComponentsScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class UsersListPageView extends JPanel {
    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    private ComponentsScrollPanel<JPanel> componentsScrollPanel ;
    public UsersListPageView() {
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel<>();
        this.add(componentsScrollPanel,BorderLayout.CENTER);
    }

    public void setPage(LinkedHashMap<String,ImageIcon> users) {
        LinkedList<JPanel> temp = new LinkedList<>();
        users.forEach( (username,image) -> {
            JPanel userPanel = new JPanel();
            userPanel.setPreferredSize(new Dimension(100,30));
            userPanel.setLayout(new BorderLayout());
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(image);
            JButton userBtn = new JButton("@"+username);
            userBtn.setBorder(null);
            userBtn.setBackground(Color.WHITE);
            userPanel.add(imageLabel,BorderLayout.WEST);
            userPanel.add(userBtn,BorderLayout.CENTER);
            userPanel.add(new JLabel("   "),BorderLayout.EAST);
            userPanel.setBackground(Color.WHITE);
            temp.add(userPanel);
            userBtn.addActionListener(e ->
                    stringListeners.forEach(stringListener -> {
                                if (stringListener instanceof SearchUserListener) {
                                    try {
                                        ((SearchUserListener) stringListener).eventOccurred(new SearchUserEvent(this, username));
                                        stringListener.stringEventOccurred("openProfilePage");
                                    } catch (Exception exception) {
                                        System.out.println("not found");
                                    }
                                }

                            }
                    ));
        });
        componentsScrollPanel.setComponentsList(temp);
        componentsScrollPanel.revalidate();
        componentsScrollPanel.repaint();
    }


    public void addStringListener(StringListener stringListener) {
        stringListeners.add(stringListener);
    }
}
