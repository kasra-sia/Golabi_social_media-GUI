package golabi.listener;

import golabi.view.pageView.PrivacySettingsPageView;

import javax.swing.*;

public class PrivacySettingsToSettingsPageListener implements StringListener{
    private JLayeredPane layeredPane;
    private PrivacySettingsPageView privacySettingsPageView;

    public PrivacySettingsToSettingsPageListener(JLayeredPane layeredPane, PrivacySettingsPageView privacySettingsPageView) {
        this.layeredPane = layeredPane;
        this.privacySettingsPageView = privacySettingsPageView;
    }

    @Override
    public void stringEventOccurred(String string) {
       if (string.equals("privacySettings")){
           layeredPane.add(privacySettingsPageView,Integer.valueOf(2));
           layeredPane.repaint();
       }
    }
}
