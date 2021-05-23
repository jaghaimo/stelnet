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
import stelnet.storage.data.DataProvider;
import stelnet.storage.data.LocationData;
import stelnet.storage.data.PerLocationProvider;
import stelnet.storage.data.StorageData;
import stelnet.storage.data.UnifiedProvider;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Cargo;
import stelnet.ui.Group;
import stelnet.ui.Heading;
import stelnet.ui.HorizontalViewContainer;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.Ships;
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
                getTabButton(StorageTab.CARGO, Keyboard.KEY_C),
                getCargoPane(size, contentSize),
                isActive(StorageTab.CARGO)
        );
        tabViewContainer.addTab(
                getTabButton(StorageTab.SHIPS, Keyboard.KEY_S),
                getShipsPane(size, contentSize),
                isActive(StorageTab.SHIPS)
        );
        return tabViewContainer;
    }

    private StorageTabButton getTabButton(StorageTab currentTab, int keyboardShortcut) {
        return new StorageTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }

    private AbstractRenderable getCargoPane(Size size, Size contentSize) {
        List<AbstractRenderable> elements = new ArrayList<>();
        List<StorageData> storageData = getStorageData();
        addEmptyData(elements, storageData, contentSize.getWidth());
        addStorageData(elements, storageData);
        AbstractRenderable group = new Group(elements);
        group.setSize(contentSize);
        VerticalViewContainer buttons = new VerticalViewContainer(buttonManager.getItemButtons());
        buttons.setSize(size);

        return new HorizontalViewContainer(group, buttons);
    }

    private AbstractRenderable getShipsPane(Size size, Size contentSize) {
        VerticalViewContainer buttons = new VerticalViewContainer(buttonManager.getShipButtons());
        buttons.setSize(size);
        return new HorizontalViewContainer(null, buttons);
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
            elements.add(getStorageContent(data));
        }
    }

    private boolean hasStorage() {
        return !StorageHelper.getAllWithAccess().isEmpty();
    }

    private AbstractRenderable getStorageContent(StorageData data) {
        return StorageTab.CARGO.equals(activeTab)
                ? new Cargo(data.getItems(), L10n.get("storageNoItems"), new Size(0, 0))
                : new Ships(data.getShips(), L10n.get("storageNoShips"), new Size(0, 0));
    }

    private List<StorageData> getStorageData() {
        DataProvider provider = StorageView.PER_LOCATION.equals(activeView)
                ? new PerLocationProvider(filterManager)
                : new UnifiedProvider(filterManager);
        return provider.getData();
    }
}
