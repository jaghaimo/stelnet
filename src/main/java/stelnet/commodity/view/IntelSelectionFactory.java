package stelnet.commodity.view;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.commodity.CommodityTab;
import stelnet.commodity.IntelTrackerMap;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.HorizontalViewContainer;
import stelnet.ui.Size;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class IntelSelectionFactory {

    private List<MarketAPI> markets;
    private final IntelTrackerMap tracker;

    public IntelSelectionFactory() {
        markets = Collections.emptyList();
        tracker = new IntelTrackerMap();
    }

    public HorizontalViewContainer create(String commodityId, CommodityTab actionTab, Size size) {
        float width = size.getWidth() - 210;
        int buttonsOnScreen = (int) Math.floor(width / 28f);
        int maxButtons = markets.size();
        int numberOfButtons = Math.min(buttonsOnScreen, maxButtons);
        List<AbstractRenderable> buttons = new LinkedList<>();
        for (int i = 0; i < numberOfButtons; i++) {
            MarketAPI market = markets.get(i);
            buttons.add(new IntelButton(i + 1, actionTab, commodityId, market, tracker));
        }
        HorizontalViewContainer rows = new HorizontalViewContainer(buttons);
        rows.setSize(size);
        return rows;
    }

    public void setMarkets(List<MarketAPI> markets) {
        this.markets = markets;
    }
}
