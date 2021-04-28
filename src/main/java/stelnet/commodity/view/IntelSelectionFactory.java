package stelnet.commodity.view;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.IntelTracker;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Row;
import stelnet.ui.Size;

public class IntelSelectionFactory {

    private List<MarketAPI> markets;
    private IntelTracker tracker;

    public IntelSelectionFactory() {
        markets = Collections.<MarketAPI>emptyList();
        tracker = new IntelTracker();
    }

    public AbstractRenderable get(String commodityId, CommodityTab actionTab, Size size) {
        float width = size.getWidth() - 210;
        int buttonsOnScreen = (int) Math.floor(width / 28f);
        int maxButtons = markets.size();
        int numberOfButtons = Math.min(buttonsOnScreen, maxButtons);
        List<AbstractRenderable> buttons = new LinkedList<>();
        for (int i = 0; i < numberOfButtons; i++) {
            MarketAPI market = markets.get(i);
            buttons.add((AbstractRenderable) new IntelButton(i + 1, actionTab, commodityId, market, tracker));
        }
        AbstractRenderable rows = new Row(buttons);
        rows.setSize(size);
        return rows;
    }

    public void setMarkets(List<MarketAPI> markets) {
        this.markets = markets;
    }
}
