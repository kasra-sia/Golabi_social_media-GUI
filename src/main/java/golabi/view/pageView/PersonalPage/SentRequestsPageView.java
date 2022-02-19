package golabi.view.pageView.PersonalPage;

import golabi.model.RequestStatus;
import golabi.view.ComponentsScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.jar.JarEntry;

public class SentRequestsPageView extends JPanel {
    private final ComponentsScrollPanel<JPanel> componentsScrollPanel;
    public SentRequestsPageView() {
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel();
        this.add(componentsScrollPanel,BorderLayout.CENTER);
    }

    public void setPage(LinkedHashMap<String , RequestStatus> sentRequests) {
        LinkedList<JPanel> temp = new LinkedList<>();
        sentRequests.forEach((username, requestStatus) ->{
            JPanel jPanel = new JPanel();
            jPanel.add(new Label("to @"+username+" --> status : "+requestStatus.toString()));
            temp.add(jPanel);
        } );
        componentsScrollPanel.setComponentsList(temp);
    }
}
