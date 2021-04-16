package stelnet.storage.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.GridData;
import stelnet.ui.Heading;
import stelnet.ui.Renderable;
import stelnet.ui.Size;
import stelnet.ui.Stack;
import stelnet.ui.VerticalGroup;

public abstract class SharedData implements GridData {

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

    @Override
    public Renderable getTopLeft(Size size) {
        List<Renderable> elements = new ArrayList<>();
        List<StorageData> storageData = dataProvider.getData();
        for (StorageData data : storageData) {
            SubmarketAPI submarket = data.getSubmarket();
            if (submarket != null) {
                elements.add(getTitle(submarket));
            }
            elements.add(getStorageContent(data));
        }
        return new Stack(size, elements);
    }

    @Override
    public Renderable getTopRight(Size size) {
        Renderable[] common = buttonManager.getCommonButtons();
        Renderable[] buttons = getButtons();
        Renderable[] all = Arrays.copyOf(common, common.length + buttons.length);
        System.arraycopy(buttons, 0, all, common.length, buttons.length);
        return new VerticalGroup(all);
    }

    @Override
    public Renderable getBottomLeft(Size size) {
        return null;
    }

    @Override
    public Renderable getBottomRight(Size size) {
        return null;
    }

    public abstract SharedData getNext();

    protected Renderable getTitle(SubmarketAPI submarket) {
        MarketAPI market = submarket.getMarket();
        FactionAPI faction = market.getFaction();
        return new Heading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
    }

    protected abstract Renderable[] getButtons();

    protected abstract Renderable getStorageContent(StorageData data);
}
