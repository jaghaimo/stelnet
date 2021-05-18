package stelnet.storage.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import stelnet.L10n;
import stelnet.helper.StorageHelper;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Group;
import stelnet.ui.Heading;
import stelnet.ui.Paragraph;
import stelnet.ui.Size;
import stelnet.ui.VerticalViewContainer;

public abstract class SharedData {

    private final float CONTROL_WIDTH = 200;

    protected ButtonManager buttonManager;
    protected FilterManager filterManager;
    protected DataProvider dataProvider;

    public SharedData(ButtonManager buttonManager, FilterManager filterManager) {
        this.buttonManager = buttonManager;
        this.filterManager = filterManager;
        this.dataProvider = new UnifiedProvider(filterManager);
    }

    public void changeDataProvider() {
        dataProvider = dataProvider.getNext();
    }

    public AbstractRenderable getContentColumn(Size size) {
        List<AbstractRenderable> elements = new ArrayList<>();
        List<StorageData> storageData = dataProvider.getData();
        addEmptyData(elements, storageData, size.getWidth() - CONTROL_WIDTH);
        addStorageData(elements, storageData);
        AbstractRenderable group = new Group(elements);
        group.setSize(size.getDifference(new Size(CONTROL_WIDTH, 0)));
        return group;
    }

    public AbstractRenderable getControlColumn(Size size) {
        AbstractRenderable[] common = buttonManager.getCommonButtons();
        AbstractRenderable[] buttons = getButtons();
        AbstractRenderable[] all = Arrays.copyOf(common, common.length + buttons.length);
        System.arraycopy(buttons, 0, all, common.length, buttons.length);
        AbstractRenderable stack = new VerticalViewContainer(all);
        stack.setSize(size);
        return stack;
    }

    public abstract SharedData getNext();

    protected abstract AbstractRenderable[] getButtons();

    protected abstract AbstractRenderable getStorageContent(StorageData data);

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
}
