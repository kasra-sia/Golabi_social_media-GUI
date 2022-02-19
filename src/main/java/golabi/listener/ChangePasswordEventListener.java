package golabi.listener;

import golabi.controller.AuthController;
import golabi.controller.pageController.SettingsPageController;
import golabi.event.ChangePasswordFormEvent;

public class ChangePasswordEventListener implements EventListener {
    SettingsPageController controller ;

    public ChangePasswordEventListener(SettingsPageController controller) {
        this.controller = controller;
    }

    @Override
    public <T> void eventOccurred(T event) throws Exception {
        controller.changePassword((ChangePasswordFormEvent) event);
    }
}
