package stelnet.widget.viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.lwjgl.input.Keyboard;
import stelnet.util.L10n;
import uilib.Group;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.TabButton;
import uilib.TabViewContainer;
import uilib.property.Size;

public class MarketView implements RenderableFactory {

    private final FilteringButtons buttonManager;
    private final ContentRenderer activeTab;
    private final DisplayStrategy activeView;
    private final MarketViewState state;

    public MarketView(MarketViewState state) {
        buttonManager = state.getFilteringButtons();
        activeTab = state.getContentRenderer();
        activeView = state.getDisplayStrategy();
        this.state = state;
    }

    @Override
    public List<Renderable> create(Size size) {
        return Collections.<Renderable>singletonList(getTabViewContainer(size));
    }

    private Renderable getTabViewContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 40;
        Size contentSize = new Size(width, height);

        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);
        tabViewContainer.addTab(
            getTabButton(ContentRenderer.ITEMS, Keyboard.KEY_I),
            getTabPane(size, contentSize, buttonManager.getItemButtons()),
            isActive(ContentRenderer.ITEMS)
        );
        tabViewContainer.addTab(
            getTabButton(ContentRenderer.SHIPS, Keyboard.KEY_S),
            getTabPane(size, contentSize, buttonManager.getShipButtons()),
            isActive(ContentRenderer.SHIPS)
        );

        return tabViewContainer;
    }

    protected boolean isActive(ContentRenderer currentTab) {
        return currentTab.equals(activeTab);
    }

    protected TabButton getTabButton(ContentRenderer currentTab, int keyboardShortcut) {
        return new ViewerTabButton(currentTab, state, isActive(currentTab), keyboardShortcut);
    }

    private Renderable getTabPane(Size size, Size contentSize, Renderable[] buttons) {
        List<Renderable> elements = new ArrayList<>();
        List<LocationContent> storageData = activeView.getData(buttonManager);
        addEmptyData(elements, storageData, contentSize.getWidth());
        addStorageData(elements, storageData);
        Group contentContainer = new Group(elements);
        contentContainer.setSize(contentSize);
        Group buttonContainer = new Group(buttons);
        buttonContainer.setSize(new Size(size.reduce(contentSize).getWidth(), contentSize.getHeight()));
        // buttonContainer.setOffset(new Position(10, 0)); // Could replace magic numbers with actual calculations
        return new HorizontalViewContainer(contentContainer, buttonContainer);
    }

    private void addEmptyData(List<Renderable> elements, List<LocationContent> storageData, float width) {
        if (storageData.isEmpty()) {
            elements.add(new Paragraph(L10n.get("storageNoStorages"), width));
        }
    }

    private void addStorageData(List<Renderable> elements, List<LocationContent> storageData) {
        for (LocationContent data : storageData) {
            LocationInfo locationData = data.getLocationInfo();
            elements.add(new Heading(locationData.getName(), locationData.getFgColor(), locationData.getBgColor()));
            elements.add(activeTab.getStorageRenderer(data));
            elements.add(new Spacer(8));
        }
        removeLastElement(elements);
    }

    private void removeLastElement(List<Renderable> elements) {
        if (elements.size() > 0) {
            elements.remove(elements.size() - 1);
        }
    }
}
