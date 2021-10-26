package stelnet.storage.view;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.StorageState;
import stelnet.storage.SubmarketDataRenderer;
import stelnet.storage.data.DisplayStrategy;
import stelnet.storage.data.LocationData;
import stelnet.storage.data.SubmarketData;
import stelnet.util.L10n;
import uilib.Group;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.Spacer;
import uilib.TabButton;
import uilib.TabViewContainer;
import uilib.ViewContainerFactory;
import uilib.property.Position;
import uilib.property.Size;

@RequiredArgsConstructor
public class StorageTabViewFactory implements ViewContainerFactory {

    private final ButtonManager buttonManager;
    private final FilterManager filterManager;
    private final SubmarketDataRenderer activeTab;
    private final DisplayStrategy activeView;

    public StorageTabViewFactory(StorageState state) {
        this(state.getButtonManager(), state.getFilterManager(), state.getActiveTab(), state.getActiveView());
    }

    @Override
    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 40;
        Size contentSize = new Size(width, height);

        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);
        tabViewContainer.addTab(
            getTabButton(SubmarketDataRenderer.ITEMS, Keyboard.KEY_I),
            getTabPane(size, contentSize, buttonManager.getItemButtons()),
            isActive(SubmarketDataRenderer.ITEMS)
        );
        tabViewContainer.addTab(
            getTabButton(SubmarketDataRenderer.SHIPS, Keyboard.KEY_S),
            getTabPane(size, contentSize, buttonManager.getShipButtons()),
            isActive(SubmarketDataRenderer.SHIPS)
        );

        return tabViewContainer;
    }

    protected boolean isActive(SubmarketDataRenderer currentTab) {
        return currentTab.equals(activeTab);
    }

    protected TabButton getTabButton(SubmarketDataRenderer currentTab, int keyboardShortcut) {
        return new StorageTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }

    private Renderable getTabPane(Size size, Size contentSize, Renderable[] buttons) {
        List<Renderable> elements = new ArrayList<>();
        List<SubmarketData> storageData = activeView.getData(filterManager);
        addEmptyData(elements, storageData, contentSize.getWidth());
        addStorageData(elements, storageData);
        Group contentContainer = new Group(elements);
        contentContainer.setSize(contentSize);
        Group buttonContainer = new Group(buttons);
        buttonContainer.setSize(new Size(size.reduce(contentSize).getWidth(), contentSize.getHeight()));
        buttonContainer.setOffset(new Position(10, 0)); // Could replace magic numbers with actual calculations
        return new HorizontalViewContainer(contentContainer, buttonContainer);
    }

    private void addEmptyData(List<Renderable> elements, List<SubmarketData> storageData, float width) {
        if (storageData.isEmpty()) {
            elements.add(new Paragraph(L10n.get("storageNoStorages"), width));
        }
    }

    private void addStorageData(List<Renderable> elements, List<SubmarketData> storageData) {
        for (SubmarketData data : storageData) {
            LocationData locationData = data.getLocationData();
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
