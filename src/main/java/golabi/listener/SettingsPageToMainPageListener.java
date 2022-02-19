package golabi.listener;

import golabi.controller.Controller;
import golabi.controller.UserController;
import golabi.controller.pageController.SettingsPageController;
import golabi.view.MainFrame;
import golabi.view.pageView.PrivacySettingsPageView;
import golabi.view.pageView.SettingsPageView;

import java.awt.*;

public class SettingsPageToMainPageListener implements StringListener {

    private SettingsPageView settingsPageView;
    private Container container;
    private PrivacySettingsPageView privacySettingsPageView;
    private SettingsPageController controller = new SettingsPageController();
    private CardLayout cardLayout;

    public SettingsPageToMainPageListener(Container container, CardLayout cardLayout) {
        this.container = container;
        this.cardLayout = cardLayout;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("settingsPage")) {
            settingsPageView = new SettingsPageView();
            container.add(settingsPageView);
            cardLayout.next(container);
            settingsPageView.addListeners(new StringListener() {
                @Override
                public void stringEventOccurred(String string) {
                    if (string.equals("logout")) {
                        controller.setLastSeen();
                        container.removeAll();
                        Controller.logger.info("logout");
                        MainFrame.instance.dispose();
                        new MainFrame();
                    }
                }
            });
            settingsPageView.addListeners(new StringListener() {
                @Override
                public void stringEventOccurred(String string) {
                    if (string.equals("deleteAccount")){
                        UserController controller = new UserController();
                        controller.deleteAccount();
                        container.removeAll();
                        MainFrame.instance.dispose();
                        new MainFrame();
                    }
                }
            });
            settingsPageView.addListeners(new StringListener() {
                @Override
                public void stringEventOccurred(String string) {
                    if (string.equals("privacySettings")) {
                        privacySettingsPageView = new PrivacySettingsPageView();
                        container.add(privacySettingsPageView);
                        cardLayout.next(container);
                        privacySettingsPageView.addStringListener(new StringListener() {
                            @Override
                            public void stringEventOccurred(String string) {
                                if (string.equals("changePassword")) {
                                    privacySettingsPageView.setChangePasswordFormListener(new ChangePasswordEventListener(controller));
                                }
                            }
                        });
                        ControllerToSettingsListener controllerToSettingsListener = new ControllerToSettingsListener(privacySettingsPageView, controller);
                        privacySettingsPageView.addStringListener(controllerToSettingsListener);
                        controllerToSettingsListener.stringEventOccurred("openPage");
                    }
                    container.repaint();
                }
            });
            MainFrame.refreshFrame();
        }
    }
}
