package stelnet.commodity.view;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.IntelTracker;
import stelnet.ui.Renderable;
import stelnet.ui.HorizontalGroup;

public class IntelSelectionFactory {

    private List<MarketAPI> markets;
    private IntelTracker tracker;

    public IntelSelectionFactory() {
        markets = Collections.<MarketAPI>emptyList();
        tracker = new IntelTracker();
    }

    public Renderable get(String commodityId, CommodityTab actionTab, float width) {
        int buttonsOnScreen = (int) Math.floor(width / 28f);
        int maxButtons = markets.size();
        int numberOfButtons = Math.min(buttonsOnScreen, maxButtons);
        List<Renderable> buttons = new LinkedList<>();
        for (int i = 0; i < numberOfButtons; i++) {
            MarketAPI market = markets.get(i);
            buttons.add((Renderable) new IntelButton(i + 1, actionTab, commodityId, market, tracker));
        }
        return new HorizontalGroup(buttons);
    }

    public void setMarkets(List<MarketAPI> markets) {
        this.markets = markets;
    }
}
