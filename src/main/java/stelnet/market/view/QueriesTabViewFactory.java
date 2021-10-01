package stelnet.market.view;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import lombok.RequiredArgsConstructor;
import stelnet.market.QueryTab;
import stelnet.ui.Button;
import stelnet.ui.DynamicGroup;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.Spacer;
import stelnet.ui.TabViewContainer;
import stelnet.ui.VerticalViewContainer;
import stelnet.ui.property.Size;

@RequiredArgsConstructor
public class QueriesTabViewFactory {

    private final QueryTab activeTab;

    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 10;
        float height = size.getHeight();
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);

        tabViewContainer.addTab(
                getTabButton(QueryTab.LIST, Keyboard.KEY_L),
                getTab(width, height),
                isActive(QueryTab.LIST)
        );

        tabViewContainer.addTab(
                getTabButton(QueryTab.NEW, Keyboard.KEY_N),
                getTab(width, height),
                isActive(QueryTab.NEW)
        );

        return tabViewContainer;
    }

    private Renderable getTab(float width, float tableHeight) {
        // TODO: This is a demo, implement actual tab
        Paragraph paragraph = new Paragraph("Manufacturer", width);
        List<Renderable> elements = new LinkedList<>();
        int numberOfButtons = (int) (Math.random() * 50) + 20;
        // int numberOfButtons = 10;
        for (int i = 0; i < numberOfButtons; i++) {
            float bwidth = 50 + (float) (Math.random() * 100);
            // float bheight = 10 + (float) (Math.random() * 10);
            // float bwidth = 154;
            elements.add(new Button(new Size(bwidth, 20), "Button", false, Misc.getButtonTextColor()));
        }
        DynamicGroup dynamicGroup = new DynamicGroup(width, null, elements);
        Spacer spacer = new Spacer(20);
        VerticalViewContainer container = new VerticalViewContainer(
                paragraph, dynamicGroup, spacer,
                paragraph, dynamicGroup, spacer,
                paragraph, dynamicGroup, spacer,
                paragraph, dynamicGroup, spacer,
                paragraph, dynamicGroup, spacer,
                paragraph, dynamicGroup);
        return container;
    }

    private QueryTabButton getTabButton(QueryTab currentTab, int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(QueryTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
