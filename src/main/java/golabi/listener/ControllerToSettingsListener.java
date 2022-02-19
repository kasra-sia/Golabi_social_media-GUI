package golabi.listener;

import golabi.controller.Controller;
import golabi.controller.pageController.SettingsPageController;
import golabi.view.pageView.PrivacySettingsPageView;

import java.util.EventObject;

public class ControllerToSettingsListener  implements StringListener {
    private SettingsPageController controller ;

    private PrivacySettingsPageView view;
    public ControllerToSettingsListener(PrivacySettingsPageView view,SettingsPageController controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("private"))
            controller.setAccountPrivate();
        if (string.equals("public"))
            controller.setAccountPublic();
        if (string.equals("noBody"))
            controller.setLastSeenNoBody();
        if (string.equals("everyOne"))
            controller.setLastSeenEveryBody();
        if (string.equals("followings"))
            controller.setLastSeenFollowings();
        if (string.equals("openPage")){
            setPrivateBtn();
            setLastSeenComboBox();
        }
    }

    public void setPrivateBtn(){
        if (controller.getOwner().getProfile().isPrivate())
            view.getPrivateAccountCBox().setSelected(true);
        else view.getPrivateAccountCBox().setSelected(false);
    }

    public void setLastSeenComboBox(){
        switch (controller.getOwner().getProfile().getLastSeen()){
            case NOBODY:
                view.getLastSeenComboBox().setSelectedIndex(1);
                break;
            case EVERYONE:
                view.getLastSeenComboBox().setSelectedIndex(0);
                break;
            case FOLLOWINGS:
                view.getLastSeenComboBox().setSelectedIndex(2);
                break;
        }
    }

}
