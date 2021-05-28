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
import stelnet.storage.StorageView;
import stelnet.storage.data.LocationData;
import stelnet.storage.data.StorageData;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Heading;
import stelnet.ui.HorizontalViewContainer;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.Size;
import stelnet.ui.TabViewContainer;
import stelnet.ui.VerticalViewContainer;

@RequiredArgsConstructor
public class TabViewFactory {

    private final ButtonManager buttonManager;
    private final FilterManager filterManager;
    private final StorageTab activeTab;
    private final StorageView activeView;

    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 50;
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

    private StorageTabButton getTabButton(StorageTab currentTab, int keyboardShortcut) {
        return new StorageTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }

    private AbstractRenderable getTabPane(Size size, Size contentSize, AbstractRenderable[] buttons) {
        List<AbstractRenderable> elements = new ArrayList<>();
        List<StorageData> storageData = activeView.getStorageData(filterManager);
        addEmptyData(elements, storageData, contentSize.getWidth());
        addStorageData(elements, storageData);
        AbstractRenderable contentContainer = new VerticalViewContainer(elements);
        contentContainer.setSize(contentSize);
        AbstractRenderable buttonContainer = new VerticalViewContainer(buttons);
        buttonContainer.setSize(new Size(size.getDifference(contentSize).getWidth(), contentSize.getHeight()));
        AbstractRenderable tabContainer = new HorizontalViewContainer(contentContainer, buttonContainer);
        tabContainer.setSize(size);
        return tabContainer;
    }

    private boolean isActive(StorageTab currentTab) {
        return currentTab.equals(activeTab);
    }

    private void addEmptyData(List<AbstractRenderable> elements, List<StorageData> storageData, float width) {
        if (!hasStorage()) {
            elements.add(new Paragraph(L10n.get("storageNoStorages"), width));
        }
    }

    private void addStorageData(List<AbstractRenderable> elements, List<StorageData> storageData) {
        if (!hasStorage()) {
            return;
        }
        for (StorageData data : storageData) {
            LocationData locationData = data.getLocationData();
            elements.add(new Heading(locationData.getName(), locationData.getFgColor(), locationData.getBgColor()));
            elements.add(activeTab.getStorageRenderer(data));
        }
    }

    private boolean hasStorage() {
        return !StorageHelper.getAllWithAccess().isEmpty();
    }
}
