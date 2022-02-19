package golabi.listener;

import golabi.view.pageView.explorerPageView.ExplorerPageView;
import golabi.view.pageView.explorerPageView.ExplorerSearchView;

import java.awt.*;

public class ExplorerPageToMainPageListener implements StringListener{
    private Container container;
    private CardLayout cardLayout;
    private ExplorerPageView explorerPageView;

    public ExplorerPageToMainPageListener(Container container, CardLayout cardLayout) {
        this.container = container;
        this.cardLayout = cardLayout;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("explorerPage")){
            explorerPageView = new ExplorerPageView();
            explorerPageView.addStringListener(new ExplorerPageListener());
            container.add(explorerPageView);
            cardLayout.next(container);
            container.revalidate();
            container.repaint();
        }
    }
}
