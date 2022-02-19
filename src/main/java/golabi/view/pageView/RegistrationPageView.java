package golabi.view.pageView;


import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import golabi.listener.RegistrationEventListener;
import golabi.listener.StringListener;
import golabi.validation.JNumberTextField;
import golabi.event.RegistrationFormEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RegistrationPageView extends JPanel implements ActionListener {
    private JTextField firstnameField = new JTextField(15);
    private JTextField lastnameField = new JTextField(15);
    private JTextField usernameField = new JTextField(15);
    private JTextField emailField = new JTextField(15);
    private JPasswordField password1Field = new JPasswordField(15);
    private JPasswordField password2Field = new JPasswordField(15);
    private JTextArea bioField = new JTextArea(5, 15);
    private JNumberTextField phoneNumberField = new JNumberTextField(15);
    private JDateChooser birthdayChooser = new JDateChooser();
    private JButton registerBtn = new JButton("register");
    private RegistrationEventListener registrationFormListener;
    private JLabel firstNameRequiredWarning;
    private JLabel lastNameRequiredWarning;
    private JLabel userNameRequiredWarning;
    private JLabel emailRequiredWarning;
    private JLabel password1RequiredWarning;
    private JLabel password2RequiredWarning;
    private JLabel duplicateUsernameWarning;
    private JLabel duplicateEmailWarning;
    private JLabel duplicatePhoneNumberWarning;
    private JLabel passwordConfirmationWarning;
    private StringListener stringListener;

    public RegistrationPageView() {
        this.setBackground(Color.lightGray);
        registerBtn.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(600, 400));
        Border innerBorder = BorderFactory.createTitledBorder("Registration form");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;

        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red> * </font>first name: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(firstnameField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        firstNameRequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        firstNameRequiredWarning.setVisible(false);
        this.add(firstNameRequiredWarning, gc);
        //////////////// 2
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red> * </font>last name: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(lastnameField, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        lastNameRequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        lastNameRequiredWarning.setVisible(false);
        this.add(lastNameRequiredWarning, gc);
        //////////////// 3
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red> * </font> username: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameField, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        userNameRequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        userNameRequiredWarning.setVisible(false);
        this.add(userNameRequiredWarning, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        duplicateUsernameWarning = new JLabel("<html><font color=red>This username has been already taken</font>");
        duplicateUsernameWarning.setVisible(false);
        this.add(duplicateUsernameWarning, gc);
        /////////////// 4
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red> * </font>email: "), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(emailField, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        emailRequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        emailRequiredWarning.setVisible(false);
        this.add(emailRequiredWarning, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        duplicateEmailWarning = new JLabel("<html><font color=red>This Email has been already taken</font>");
        duplicateEmailWarning.setVisible(false);
        this.add(duplicateEmailWarning, gc);
        /////////////// 5
        gc.gridx = 0;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red> * </font>password: "), gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password1Field, gc);

        gc.gridx = 2;
        gc.gridy = 4;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        password1RequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        password1RequiredWarning.setVisible(false);
        this.add(password1RequiredWarning, gc);

        /////////////// 6
        gc.gridx = 0;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("<html><font color=red> * </font>confirm password: "), gc);

        gc.gridx = 1;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password2Field, gc);

        gc.gridx = 2;
        gc.gridy = 5;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        password2RequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        password2RequiredWarning.setVisible(false);
        this.add(password2RequiredWarning, gc);

        gc.gridx = 2;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        passwordConfirmationWarning = new JLabel("<html><font color=red>Wrong password confirmation</font>");
        passwordConfirmationWarning.setVisible(false);
        this.add(passwordConfirmationWarning, gc);
        /////////////// 7
        gc.gridx = 0;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("birthDay : "), gc);

        gc.gridx = 1;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        birthdayChooser.setPreferredSize(new Dimension(115, 20));
        JTextFieldDateEditor editor = (JTextFieldDateEditor) birthdayChooser.getDateEditor();
        editor.setEditable(false);
        this.add(birthdayChooser, gc);

        //////////////// 8
        gc.gridx = 0;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("phone number: "), gc);

        gc.gridx = 1;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(phoneNumberField, gc);

        gc.gridx = 2;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        duplicatePhoneNumberWarning = new JLabel("<html><font color=red>This phone number has been already taken</font>");
        duplicatePhoneNumberWarning.setVisible(false);
        this.add(duplicatePhoneNumberWarning, gc);
        //////////////// 9
        gc.gridx = 0;
        gc.gridy = 8;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("bio: "), gc);

        gc.gridx = 1;
        gc.gridy = 8;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(bioField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane, gc);
        /////////////// 10
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 9;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(registerBtn, gc);
        registerBtn.addActionListener(this);
    }

    public String getFirstnameField() {
        return firstnameField.getText();
    }

    public String getLastnameField() {
        return lastnameField.getText();
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public String getPassword1Field() {
        return password1Field.getText();
    }

    public String getPassword2Field() {
        return password2Field.getText();
    }

    public String getPhoneNumberField() {
        return phoneNumberField.getText();
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

    public Date getBirthDay() {
        return birthdayChooser.getDate();
    }

    public String getBio() {
        return bioField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (registerBtn == (JButton) e.getSource()) {
            resetWarningLabels();
            if (checkRequiredFiled() && checkPasswordConfirmation()) {
                RegistrationFormEvent registrationFormEvent =
                        new RegistrationFormEvent(this,
                                getFirstnameField(),
                                getLastnameField(),
                                getUsernameField(),
                                getEmailField(),
                                getPassword1Field(),
                                getPassword2Field(),
                                getPhoneNumberField(),
                                getBirthDay(),
                                getBio());

                try {
                    registrationFormListener.eventOccurred(registrationFormEvent);
                    stringListener.stringEventOccurred("userLoggedIn");

                } catch (Exception exception) {
                    if (exception.getMessage().equals("duplicatedUsername"))
                        duplicateUsernameWarning.setVisible(true);
                    else if (exception.getMessage().equals("duplicatedEmail"))
                        duplicateEmailWarning.setVisible(true);
                    else if (exception.getMessage().equals("duplicatedPhoneNumber"))
                    duplicatePhoneNumberWarning.setVisible(true);
                    else exception.printStackTrace();
                }
            }
        }
    }

    public void setFormListener(RegistrationEventListener registrationFormListener) {
        this.registrationFormListener = registrationFormListener;
    }

    private boolean checkRequiredFiled() {
        if (getFirstnameField().isEmpty()) {
            firstNameRequiredWarning.setVisible(true);
            return false;
        }
        if (getLastnameField().isEmpty()) {
            lastNameRequiredWarning.setVisible(true);
            return false;
        }
        if (getUsernameField().isEmpty()) {
            userNameRequiredWarning.setVisible(true);
            return false;
        }
        if (getEmailField().isEmpty()) {
            emailRequiredWarning.setVisible(true);
            return false;
        }
        if (getPassword1Field().isEmpty()) {
            password1RequiredWarning.setVisible(true);
            return false;
        }
        if (getPassword2Field().isEmpty()) {
            password1RequiredWarning.setVisible(true);
            return false;
        }
        return true;
    }

    public boolean checkPasswordConfirmation(){
        if (getPassword1Field().equals(getPassword2Field()) || getPassword1Field().isEmpty() || getPassword2Field().isEmpty())
            return true;
        passwordConfirmationWarning.setVisible(true);
        return false;
    }

    private void resetWarningLabels() {
        firstNameRequiredWarning.setVisible(false);
        lastNameRequiredWarning.setVisible(false);
        userNameRequiredWarning.setVisible(false);
        emailRequiredWarning.setVisible(false);
        password1RequiredWarning.setVisible(false);
        password2RequiredWarning.setVisible(false);
        duplicatePhoneNumberWarning.setVisible(false);
        duplicateEmailWarning.setVisible(false);
        duplicateUsernameWarning.setVisible(false);
        passwordConfirmationWarning.setVisible(false);
    }
}
