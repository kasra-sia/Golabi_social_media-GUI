package golabi.listener;


import golabi.controller.AuthController;
import golabi.event.LoginFormEvent;

public class LoginEventListener implements EventListener {
    private AuthController authController = new AuthController();
    @Override
    public <T> void eventOccurred(T event) throws Exception {
        authController.login((LoginFormEvent) event);
    }
}
