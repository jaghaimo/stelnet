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
import stelnet.ui.Group;
import stelnet.ui.Heading;
import stelnet.ui.Renderable;
import stelnet.ui.Size;
import stelnet.ui.Stack;

public abstract class SharedData {

    private final float spacer = 20;
    private final float controlWidth = 180;

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

    public Renderable getContentColumn(Size size) {
        List<Renderable> elements = new ArrayList<>();
        List<StorageData> storageData = dataProvider.getData();
        for (StorageData data : storageData) {
            SubmarketAPI submarket = data.getSubmarket();
            addTitle(elements, submarket);
            elements.add(getStorageContent(data));
        }
        Renderable group = new Group(elements);
        group.setSize(size.getDifference(new Size(spacer + controlWidth, 0)));
        group.setScroller(true);
        return group;
    }

    public Renderable getControlColumn(Size size) {
        Renderable[] common = buttonManager.getCommonButtons();
        Renderable[] buttons = getButtons();
        Renderable[] all = Arrays.copyOf(common, common.length + buttons.length);
        System.arraycopy(buttons, 0, all, common.length, buttons.length);
        Renderable stack = new Stack(all);
        stack.setSize(size);
        return stack;
    }

    public abstract SharedData getNext();

    protected Renderable getTitle(SubmarketAPI submarket) {
        MarketAPI market = submarket.getMarket();
        FactionAPI faction = market.getFaction();
        return new Heading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
    }

    protected abstract Renderable[] getButtons();

    protected abstract Renderable getStorageContent(StorageData data);

    private void addTitle(List<Renderable> elements, SubmarketAPI submarket) {
        if (submarket == null) {
            LogHelper.debug("Skipping addTitle");
            return;
        }
        elements.add(getTitle(submarket));
    }
}
