package golabi.view;


import golabi.Commons;
import golabi.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ToolsBar extends JPanel implements ActionListener {
    private JButton registrationBtn;
    private JButton loginBtn;
    private JButton backBtn;
    private JButton MainPageBtn;
    private LinkedList<StringListener> stringListeners = new LinkedList<>();

    public ToolsBar() {
        this.setBackground(Color.GRAY);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.black);

        //---------------register----------------
        registrationBtn = new JButton("Register");
        registrationBtn.setBackground(Color.white);
        registrationBtn.setForeground(Color.black);
        registrationBtn.setFocusable(false);
        registrationBtn.setIcon(Commons.REGISTER_IMG);
        this.add(registrationBtn);
        registrationBtn.addActionListener(this);

        //--------------login------------------
        loginBtn = new JButton("Login");
        loginBtn.setBackground(Color.white);
        loginBtn.setForeground(Color.black);
        loginBtn.setFocusable(false);
        loginBtn.setIcon(Commons.LOGIN_IMG);
        this.add(loginBtn);
        loginBtn.addActionListener(this);
        //--------------back------------------
        backBtn = new JButton("Back");
        backBtn.setBackground(Color.white);
        backBtn.setForeground(Color.black);
        backBtn.setFocusable(false);
        backBtn.setIcon(Commons.BACK_IMG);
        backBtn.setEnabled(false);
        backBtn.setVisible(false);
        this.add(backBtn);
        backBtn.addActionListener(this);
        //--------------MainPage------------------
        MainPageBtn = new JButton("Main page");
        MainPageBtn.setBackground(Color.white);
        MainPageBtn.setForeground(Color.black);
        MainPageBtn.setFocusable(false);
        MainPageBtn.setIcon(Commons.MAIN_PAGE_BTN_IMG);
        MainPageBtn.setEnabled(false);
        MainPageBtn.setVisible(false);
        this.add(MainPageBtn);
        MainPageBtn.addActionListener(this);
    }

    public void addListener(StringListener stringListener) {
        stringListeners.add(stringListener);
    }
   public void update(){
        loginBtn.setEnabled(false);
        loginBtn.setVisible(false);
        registrationBtn.setEnabled(false);
        registrationBtn.setVisible(false);
        backBtn.setVisible(true);
        backBtn.setEnabled(true);
       MainPageBtn.setVisible(true);
       MainPageBtn.setEnabled(true);
   }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (StringListener stringListener : stringListeners) {
            if (registrationBtn == (JButton)e.getSource()){
                stringListener.stringEventOccurred("registration");
            }
            if (loginBtn == (JButton)e.getSource()) {
                stringListener.stringEventOccurred("login");
            }
            if (backBtn == e.getSource()){
                stringListener.stringEventOccurred("back");
            }
            if (MainPageBtn == e.getSource())
                stringListener.stringEventOccurred("backToMainPage");
        }
    }
}