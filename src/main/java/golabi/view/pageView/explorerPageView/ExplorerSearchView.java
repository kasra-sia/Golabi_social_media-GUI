package golabi.view.pageView.explorerPageView;

import golabi.Commons;
import golabi.ImageResizer;
import golabi.event.SearchUserEvent;
import golabi.listener.EventListener;
import golabi.listener.StringListener;
import golabi.view.modelView.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ExplorerSearchView extends JPanel implements ActionListener {
    private JTextField usernameField = new JTextField("username", 20);
    private JButton searchBtn = new JButton("search");
    private JButton foundUserBtn = new JButton();
    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    private EventListener eventListener;
    private JLabel notFoundWarning ;

    public ExplorerSearchView() {
        this.setBorder(BorderFactory.createTitledBorder("find"));
        this.setBackground(Color.gray);
        this.setLayout(new GridBagLayout());
//        this.setPreferredSize(new Dimension(400,200));
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        ////////////// 0
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        usernameField.setPreferredSize(new Dimension(200, 50));
//        usernameField.setFocusable(false);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));

        this.add(usernameField, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 20, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        searchBtn.setBackground(Color.pink);
        searchBtn.setFocusable(false);
        searchBtn.setBorderPainted(false);
        searchBtn.addActionListener(this);
        this.add(searchBtn, gc);


        ////////////// 1

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.WEST;
        this.add(new JLabel("result :"), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.EAST;
        foundUserBtn.setForeground(Color.black);
        foundUserBtn.setBackground(Color.gray);
        foundUserBtn.setFocusable(false);
        foundUserBtn.setBorderPainted(false);
        foundUserBtn.setEnabled(false);
        foundUserBtn.setVisible(false);
        foundUserBtn.addActionListener(this);
        notFoundWarning = new JLabel("<html><font color=red> user not found </font>");
        notFoundWarning.setVisible(false);
        notFoundWarning.setFont(new Font(null,Font.BOLD,20));
        foundUserBtn.setFont(new Font(null,Font.BOLD,20));
        this.add(notFoundWarning,gc);
        this.add(foundUserBtn, gc);
        //////////////2


    }

    public void setPage(String username, String imagePath) {
        foundUserBtn.setEnabled(true);
        foundUserBtn.setVisible(true);
        notFoundWarning.setVisible(false);
        if (imagePath != null)
        foundUserBtn.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH, Commons.PROFILE_SMALL_IMG_HEIGHT, imagePath));
        foundUserBtn.setText("@"+username);
        revalidate();
        repaint();
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void addStringListeners(StringListener stringListener) {
        this.stringListeners.add(stringListener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener stringListener : stringListeners) {
            if (e.getSource() == foundUserBtn)
                stringListener.stringEventOccurred("openProfilePage");

            if (e.getSource() == searchBtn) {
                stringListener.stringEventOccurred("search");
                if (!usernameField.getText().equals("") && !usernameField.getText().equals("username"))
                    try {
                        eventListener.eventOccurred(new SearchUserEvent(this,usernameField.getText()));

                    } catch (Exception exception) {
                        if (exception.getMessage().equals("not found")) {
                            notFoundWarning.setVisible(true);
                            foundUserBtn.setVisible(false);
                            foundUserBtn.setEnabled(false);
                        }else exception.printStackTrace();
                    }

            }
        }

    }

}
