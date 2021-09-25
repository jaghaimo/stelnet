package stelnet.market.view;

import org.lwjgl.input.Keyboard;

import lombok.RequiredArgsConstructor;
import stelnet.market.QueryTab;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.TabViewContainer;
import stelnet.ui.property.Size;

@RequiredArgsConstructor
public class QueriesTabViewFactory {

    private final QueryTab activeTab;

    public Renderable createContainer(Size size) {
        float width = size.getWidth();
        float height = size.getHeight();
        TabViewContainer tabViewContainer = new TabViewContainer();

        tabViewContainer.addTab(
                getTabButton(QueryTab.LIST, Keyboard.KEY_L),
                getEmpty(width, height),
                isActive(QueryTab.LIST)
        );

        tabViewContainer.addTab(
                getTabButton(QueryTab.NEW, Keyboard.KEY_N),
                getEmpty(width, height),
                isActive(QueryTab.NEW)
        );

        return tabViewContainer;
    }

    private Renderable getEmpty(float width, float tableHeight) {
        return new Paragraph("To be done.", width);
    }

    private QueryTabButton getTabButton(QueryTab currentTab, int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(QueryTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
