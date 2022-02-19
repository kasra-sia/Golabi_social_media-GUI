package golabi.view.pageView.PersonalPage;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import golabi.Commons;
import golabi.ImageResizer;
import golabi.event.EditInfoEvent;
import golabi.event.FileEvent;
import golabi.event.RegistrationFormEvent;
import golabi.listener.EditInfoEventListener;
import golabi.listener.ImageFileEventListener;
import golabi.listener.StringListener;
import golabi.validation.ImageFilter;
import golabi.validation.JNumberTextField;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ShowInfoPageView extends JPanel implements ActionListener {
    private JTextField firstnameField = new JTextField(15);
    private JTextField lastnameField = new JTextField(15);
    private JTextField usernameField = new JTextField(15);
    private JTextField emailField = new JTextField(15);
    private JPasswordField password1Field = new JPasswordField(15);
    private JPasswordField password2Field = new JPasswordField(15);
    private JTextArea bioField = new JTextArea(5, 15);
    private JNumberTextField phoneNumberField = new JNumberTextField(15);
    private JDateChooser birthdayChooser = new JDateChooser();
    private JButton saveBtn = new JButton("save");
    private EditInfoEventListener editInfoEventListener;
    private JLabel firstNameRequiredWarning;
    private JLabel lastNameRequiredWarning;
    private JLabel userNameRequiredWarning;
    private JLabel emailRequiredWarning;
    private JLabel duplicateUsernameWarning;
    private JLabel duplicateEmailWarning;
    private JLabel duplicatePhoneNumberWarning;
    private JButton selectImageBtn = new JButton("Select Image");
    private JLabel imageLabel = new JLabel();
    private JPanel panel = new JPanel();
    private JButton removePhotoBtn = new JButton("remove photo");
    private JFileChooser imageChooser;
    private String imageFilePath ;
    private StringListener stringListener;
    public ShowInfoPageView() {
        this.add(panel);
        this.setBackground(Color.gray);
        panel.setPreferredSize(new Dimension(Commons.CENTER_PANEL_WIDTH*2/3, Commons.CENTER_PANEL_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.gray);
        Border innerBorder = BorderFactory.createTitledBorder(javax.swing.BorderFactory.
                createTitledBorder(null, "Info", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.yellow));
        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;

        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("<html><font color=red> * </font>first name: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(firstnameField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        firstNameRequiredWarning = new JLabel("<html><font color=red>panel filed is required </font>");
        firstNameRequiredWarning.setVisible(false);
        panel.add(firstNameRequiredWarning, gc);
        //////////////// 2
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("<html><font color=red> * </font>last name: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(lastnameField, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        lastNameRequiredWarning = new JLabel("<html><font color=red>panel filed is required </font>");
        lastNameRequiredWarning.setVisible(false);
        panel.add(lastNameRequiredWarning, gc);
        //////////////// 3
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("<html><font color=red> * </font> username: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        userNameRequiredWarning = new JLabel("<html><font color=red>panel filed is required </font>");
        userNameRequiredWarning.setVisible(false);
        panel.add(userNameRequiredWarning, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        duplicateUsernameWarning = new JLabel("<html><font color=red>panel username has been already taken</font>");
        duplicateUsernameWarning.setVisible(false);
        panel.add(duplicateUsernameWarning, gc);
        /////////////// 4
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("<html><font color=red> * </font>email: "), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(emailField, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        emailRequiredWarning = new JLabel("<html><font color=red>panel filed is required </font>");
        emailRequiredWarning.setVisible(false);
        panel.add(emailRequiredWarning, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        duplicateEmailWarning = new JLabel("<html><font color=red>panel Email has been already taken</font>");
        duplicateEmailWarning.setVisible(false);
        panel.add(duplicateEmailWarning, gc);
        /////////////// 5
        gc.gridx = 0;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("birthDay : "), gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        birthdayChooser.setPreferredSize(new Dimension(115, 20));
        JTextFieldDateEditor editor = (JTextFieldDateEditor) birthdayChooser.getDateEditor();
        editor.setEditable(false);
        panel.add(birthdayChooser, gc);

        //////////////// 6
        gc.gridx = 0;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("phone number: "), gc);

        gc.gridx = 1;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(phoneNumberField, gc);

        gc.gridx = 2;
        gc.gridy = 5;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        duplicatePhoneNumberWarning = new JLabel("<html><font color=red>panel phone number has been already taken</font>");
        duplicatePhoneNumberWarning.setVisible(false);
        panel.add(duplicatePhoneNumberWarning, gc);
        //////////////// 7
        gc.gridx = 0;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("bio: "), gc);

        gc.gridx = 1;
        gc.gridy = 6;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(bioField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, gc);
        /////////////// 8

        gc.gridx = 1;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        selectImageBtn.addActionListener(this);
        selectImageBtn.setBackground(Color.PINK);
        panel.add(selectImageBtn, gc);
        ////////////////10
        imageLabel.setBackground(Color.white);
        ImageIcon temp = ImageResizer.reSizeImage(Commons.PROFILE_BIG_IMG_WIDTH,Commons.PROFILE_BIG_IMG_HEIGHT,Commons.GALLERY_IMG.getDescription());
        imageLabel.setIcon(temp);
        imageLabel.setPreferredSize(new Dimension(Commons.PROFILE_BIG_IMG_WIDTH,Commons.PROFILE_BIG_IMG_HEIGHT));
        imageLabel.setOpaque(true);
         this.add(imageLabel);
        /////////////// 9
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 8;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        saveBtn.setBackground(Color.pink);
        panel.add(saveBtn, gc);
        saveBtn.addActionListener(this);
        ////////////// 10
        gc.gridx = 1;
        gc.gridy = 7;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        removePhotoBtn.setBackground(Color.pink);
        removePhotoBtn.addActionListener(this);
        panel.add(removePhotoBtn, gc);

    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
    public void setFirstname(String firstname) {
        this.firstnameField.setText(firstname);
    }

    public void setLastname(String lastname) {
        this.lastnameField.setText(lastname);
    }

    public void setUsername(String  username) {
        this.usernameField.setText(username);
    }

    public void setEmail(String email) {
        this.emailField.setText(email);
    }

    public void setBio(String bio) {
        this.bioField.setText(bio);
    }

    public void setBirthday(Date birthday) {
            this.birthdayChooser.setDate(birthday);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumberField.setText(phoneNumber);
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

    public String getPhoneNumberField() {
        return phoneNumberField.getText();
    }

    public Date getBirthDay() {
        return birthdayChooser.getDate();
    }

    public String getBio() {
        return bioField.getText();
    }

    public void setImageFromPath(String path) {
        imageLabel.setIcon(null);
        if (path!= null)
        imageLabel.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_BIG_IMG_WIDTH,Commons.PROFILE_BIG_IMG_HEIGHT,path));
    }

    public void setChangeInfoEventListener(EditInfoEventListener editInfoEventListener) {
        this.editInfoEventListener = editInfoEventListener;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == selectImageBtn){
            imageChooser = new JFileChooser();
            imageChooser.addChoosableFileFilter(new ImageFilter());
            imageChooser.setAcceptAllFileFilterUsed(false);
            int ans = imageChooser.showOpenDialog(this);
            if (ans==0) {
                imageFilePath = imageChooser.getSelectedFile().getPath();
                imageLabel.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_BIG_IMG_WIDTH, Commons.PROFILE_BIG_IMG_HEIGHT, imageFilePath));
                try {
                    new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
                    BufferedImage bf;
                    bf = ImageIO.read(new File(imageFilePath));
                    imageLabel.setIcon(new ImageIcon(bf));
                    bf.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (e.getSource() == removePhotoBtn) {
            imageFilePath = null;
            imageLabel.removeAll();
            revalidate();
            repaint();
            stringListener.stringEventOccurred("removePhoto");
        }
            if (saveBtn == (JButton) e.getSource()) {
            resetWarningLabels();
            if (checkRequiredFiled()) {
                EditInfoEvent editInfoEvent =
                        new EditInfoEvent(this,
                                getFirstnameField(),
                                getLastnameField(),
                                getUsernameField(),
                                getEmailField(),
                                getPhoneNumberField(),
                                getBirthDay(),
                                getBio(),
                                imageFilePath);
                try {
                    editInfoEventListener.eventOccurred(editInfoEvent);
                    stringListener.stringEventOccurred("save");
                    JOptionPane.showMessageDialog(null,"your info updated successfully","",JOptionPane.INFORMATION_MESSAGE,Commons.CHECK_ICON_IMG);
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

        return true;
    }


    private void resetWarningLabels() {
        firstNameRequiredWarning.setVisible(false);
        lastNameRequiredWarning.setVisible(false);
        userNameRequiredWarning.setVisible(false);
        emailRequiredWarning.setVisible(false);
        duplicatePhoneNumberWarning.setVisible(false);
        duplicateEmailWarning.setVisible(false);
        duplicateUsernameWarning.setVisible(false);
    }

}

