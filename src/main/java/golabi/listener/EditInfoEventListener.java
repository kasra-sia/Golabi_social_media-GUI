package golabi.listener;

import golabi.controller.pageController.PersonalPageController;
import golabi.event.EditInfoEvent;

public class EditInfoEventListener implements EventListener{
    private PersonalPageController controller = new PersonalPageController();
    @Override
    public <T> void eventOccurred(T event) throws Exception {
        controller.saveInfo((EditInfoEvent)event);
    }
}
