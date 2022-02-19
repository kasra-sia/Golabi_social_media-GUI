package golabi.listener;

import golabi.controller.pageController.PersonalPageController;
import golabi.view.pageView.PersonalPage.ShowInfoPageView;

public class ControllerToInfoListener implements StringListener{
    private PersonalPageController controller;
    private ShowInfoPageView view;

    public ControllerToInfoListener(PersonalPageController controller, ShowInfoPageView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("openPage")){
            view.setBio(controller.getOwner().getProfile().getBio());
            view.setBirthday(controller.getOwner().getProfile().getBirthday());
            view.setFirstname(controller.getOwner().getProfile().getFirstName());
            view.setLastname(controller.getOwner().getProfile().getLastName());
            view.setUsername(controller.getOwner().getUsername());
            view.setEmail(controller.getOwner().getProfile().getEmail());
            view.setPhoneNumber(controller.getOwner().getProfile().getPhoneNumber());
            view.setImageFromPath(controller.getContext().Users.getPhoto(controller.getOwner().getID()));
        }
    }
}
