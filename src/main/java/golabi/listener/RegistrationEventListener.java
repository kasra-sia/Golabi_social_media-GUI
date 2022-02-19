package golabi.listener;

import golabi.controller.AuthController;
import golabi.event.RegistrationFormEvent;

public class RegistrationEventListener implements EventListener {
    private AuthController authController = new AuthController();
    @Override
    public <T> void eventOccurred(T event) throws Exception {
        authController.register((RegistrationFormEvent) event);
    }
}