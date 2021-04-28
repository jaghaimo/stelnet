package stelnet.storage.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stelnet.helper.LogHelper;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Group;
import stelnet.ui.Heading;
import stelnet.ui.Size;
import stelnet.ui.Stack;

public abstract class SharedData {

    private final float SPACER = 20;
    private final float CONTROL_WIDTH = 180;

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
        for (StorageData data : storageData) {
            SubmarketAPI submarket = data.getSubmarket();
            addTitle(elements, submarket);
            elements.add(getStorageContent(data));
        }
        AbstractRenderable group = new Group(elements);
        group.setSize(size.getDifference(new Size(SPACER + CONTROL_WIDTH, 0)));
        // TODO this call is probably not needed - test
        group.setWithScroller(true);
        return group;
    }

    public AbstractRenderable getControlColumn(Size size) {
        AbstractRenderable[] common = buttonManager.getCommonButtons();
        AbstractRenderable[] buttons = getButtons();
        AbstractRenderable[] all = Arrays.copyOf(common, common.length + buttons.length);
        System.arraycopy(buttons, 0, all, common.length, buttons.length);
        AbstractRenderable stack = new Stack(all);
        stack.setSize(size);
        return stack;
    }

    public abstract SharedData getNext();

    protected AbstractRenderable getTitle(SubmarketAPI submarket) {
        MarketAPI market = submarket.getMarket();
        FactionAPI faction = market.getFaction();
        return new Heading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
    }

    protected abstract AbstractRenderable[] getButtons();

    protected abstract AbstractRenderable getStorageContent(StorageData data);

    private void addTitle(List<AbstractRenderable> elements, SubmarketAPI submarket) {
        if (submarket == null) {
            LogHelper.debug("Skipping addTitle");
            return;
        }
        elements.add(getTitle(submarket));
    }
}
