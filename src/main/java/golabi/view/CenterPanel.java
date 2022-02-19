package golabi.view;

import golabi.Commons;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
   private JLabel label1 = new JLabel();

    public CenterPanel() {
        this.setLayout(null);
        this.setBackground(Color.black);
        label1.setBounds(500,0,700,300);
        label1.setText("welcome to Golabi, the word's greatest social media");
        label1.setForeground(Color.white);
        label1.setFont(new Font(null, Font.BOLD, 25));
        label1.setBackground(Color.black);
        label1.setOpaque(true);

        this.add(label1);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Commons.BACKGROUND_IMG.getImage(), 0, 0, null);
    }
}