package golabi.view.pageView;

import golabi.event.LoginFormEvent;
import golabi.listener.LoginEventListener;
import golabi.listener.StringListener;
import golabi.view.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageView extends JPanel implements ActionListener {
    private JTextField usernameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginBtn = new JButton("login");
    private LoginEventListener loginFormListener;
    private JLabel wrongUsernameOrPasswordWarning;
    private StringListener stringListener;


    public LoginPageView() {
        Border innerBorder = BorderFactory.createTitledBorder("login");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setPreferredSize(new Dimension(600, 400));
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        loginBtn.setBackground(Color.CYAN);
        loginBtn.addActionListener(this);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;
        //////////////// 0
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 200);
        gc.anchor = GridBagConstraints.LINE_END;
        wrongUsernameOrPasswordWarning = new JLabel("<html><font color=red>Wrong username or password </font>");
        wrongUsernameOrPasswordWarning.setVisible(false);
        this.add(wrongUsernameOrPasswordWarning,gc);
        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("username: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameField, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;

        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        passwordField.setPreferredSize(new Dimension(140,20));
        this.add(passwordField, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;

        /////////////// 3
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(loginBtn, gc);
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((JButton) e.getSource()==loginBtn) {
            resetWarnings();
            LoginFormEvent loginFormEvent = new LoginFormEvent(
                    this,
                    getUsernameField(),
                    getPasswordField());

            try {
                loginFormListener.eventOccurred(loginFormEvent);
                stringListener.stringEventOccurred("userLoggedIn");
            } catch (Exception exception) {
                if (exception.getMessage().equals("wrongUsernameOrPassword")){
                    wrongUsernameOrPasswordWarning.setVisible(true);
                    MainFrame.refreshFrame();

                }


            }
        }
    }

    public void setLoginFormListener(LoginEventListener loginFormListener) {
        this.loginFormListener = loginFormListener;
    }
    private void resetWarnings(){
        wrongUsernameOrPasswordWarning.setVisible(false);
    }
}
