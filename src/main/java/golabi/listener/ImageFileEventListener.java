package golabi.listener;


import golabi.controller.pageController.PersonalPageController;

public class ImageFileEventListener implements EventListener {
    PersonalPageController controller = new PersonalPageController();
    public ImageFileEventListener(PersonalPageController controller) {
        this.controller = controller;
    }

    @Override
    public <T> void eventOccurred(T event) throws Exception {

    }
}
