package golabi.listener;
import golabi.view.*;
import golabi.view.pageView.LoginPageView;
import golabi.view.pageView.RegistrationPageView;

import java.awt.*;

public class CenterToToolbarStringListener implements StringListener {
    private CenterPanel centerPanel;
    private RegistrationPageView registrationPageView ;
    private LoginPageView loginPageView ;
    private StringListener stringListener;



    public CenterToToolbarStringListener(CenterPanel centerPanel) { this.centerPanel = centerPanel;
        centerPanel.setLayout(new BorderLayout());}

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("registration")){
            centerPanel.removeAll();
            registrationPageView = new RegistrationPageView();
            registrationPageView.setFormListener(new RegistrationEventListener());
            centerPanel.add(registrationPageView,BorderLayout.EAST);
            registrationPageView.setStringListener(new StringListener() {
                @Override
                public void stringEventOccurred(String string) {
                    if (string.equals("userLoggedIn")){
                        stringListener.stringEventOccurred("userLoggedIn");
                    }
                }
            });
            MainFrame.refreshFrame();
        }
        if (string.equals("login")){
            centerPanel.removeAll();
            loginPageView = new LoginPageView();
            centerPanel.add(loginPageView,BorderLayout.EAST);
            loginPageView.setLoginFormListener(new LoginEventListener());
            MainFrame.refreshFrame();
            loginPageView.setStringListener(new StringListener() {
                @Override
                public void stringEventOccurred(String string) {
                    if (string.equals("userLoggedIn")){
                        stringListener.stringEventOccurred("userLoggedIn");
                    }
                }
            });
        }
    }
    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}