package golabi.validation;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class JNumberTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    public JNumberTextField(int i) {
        super(i);
    }

    @Override
    public void processKeyEvent(KeyEvent ev) {
        if (Character.isDigit(ev.getKeyChar()) || ev.getKeyCode() == 8) {
            super.processKeyEvent(ev);
        }
        ev.consume();
        return;
    }

    /**
     * As the user is not even able to enter a dot ("."), only integers (whole numbers) may be entered.
     */
    public Long getNumber() {
        Long result = null;
        String text = getText();
        if (text != null && !"".equals(text)) {
            result = Long.valueOf(text);
        }
        return result;
    }
}