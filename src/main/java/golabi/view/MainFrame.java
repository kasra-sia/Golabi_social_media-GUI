package golabi.view;
import golabi.Commons;
import javax.swing.*;
public class MainFrame extends JFrame implements Commons {
    public static MainFrame instance = null;
    private MainPanel mainPanel = new MainPanel();
    public MainFrame() {
        super("Golabi");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);
        instance = this;
    }

    public static void refreshFrame(){
        SwingUtilities.updateComponentTreeUI(instance);
        instance.repaint();
        instance.revalidate();
    }
}




