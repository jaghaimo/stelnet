package stelnet.storage.view;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import lombok.RequiredArgsConstructor;
import stelnet.L10n;
import stelnet.helper.StorageHelper;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.StorageTab;
import stelnet.storage.data.DataProvider;
import stelnet.storage.data.LocationData;
import stelnet.storage.data.StorageData;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Group;
import stelnet.ui.Heading;
import stelnet.ui.HorizontalViewContainer;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.Spacer;
import stelnet.ui.TabButton;
import stelnet.ui.TabViewContainer;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

@RequiredArgsConstructor
public class StorageTabViewFactory {

    private final ButtonManager buttonManager;
    private final FilterManager filterManager;
    private final StorageTab activeTab;
    private final DataProvider activeView;

    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 54;
        Size contentSize = new Size(width, height);

        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.addTab(
                getTabButton(StorageTab.ITEMS, Keyboard.KEY_I),
                getTabPane(size, contentSize, buttonManager.getItemButtons()),
                isActive(StorageTab.ITEMS)
        );
        tabViewContainer.addTab(
                getTabButton(StorageTab.SHIPS, Keyboard.KEY_S),
                getTabPane(size, contentSize, buttonManager.getShipButtons()),
                isActive(StorageTab.SHIPS)
        );

        return tabViewContainer;
    }

    protected boolean isActive(StorageTab currentTab) {
        return currentTab.equals(activeTab);
    }

    protected TabButton getTabButton(StorageTab currentTab, int keyboardShortcut) {
        return new StorageTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }

    protected boolean hasStorage() {
        return !StorageHelper.getAllWithAccess().isEmpty();
    }

    private AbstractRenderable getTabPane(Size size, Size contentSize, AbstractRenderable[] buttons) {
        List<AbstractRenderable> elements = new ArrayList<>();
        List<StorageData> storageData = activeView.getData(filterManager);
        addEmptyData(elements, storageData, contentSize.getWidth());
        addStorageData(elements, storageData);
        AbstractRenderable contentContainer = new Group(elements);
        contentContainer.setSize(contentSize.reduce(new Size(8, 0)));
        contentContainer.setOffset(new Position(5, 0));
        AbstractRenderable buttonContainer = new Group(buttons);
        buttonContainer.setSize(new Size(size.reduce(contentSize).getWidth(), contentSize.getHeight()));
        buttonContainer.setOffset(new Position(8, 0));
        AbstractRenderable tabContainer = new HorizontalViewContainer(contentContainer, buttonContainer);
        tabContainer.setSize(size);
        return tabContainer;
    }

    private void addEmptyData(List<AbstractRenderable> elements, List<StorageData> storageData, float width) {
        if (!hasStorage()) {
            elements.add(new Paragraph(L10n.get("storageNoStorages"), width));
        }
    }

    private void addStorageData(List<AbstractRenderable> elements, List<StorageData> storageData) {
        if (!hasStorage() || storageData.isEmpty()) {
            return;
        }
        for (StorageData data : storageData) {
            LocationData locationData = data.getLocationData();
            elements.add(new Heading(locationData.getName(), locationData.getFgColor(), locationData.getBgColor()));
            elements.add(activeTab.getStorageRenderer(data));
            elements.add(new Spacer(8));
        }
        elements.remove(elements.size() - 1);
    }
}
