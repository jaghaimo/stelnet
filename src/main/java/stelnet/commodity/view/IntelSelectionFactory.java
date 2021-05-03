package stelnet.commodity.view;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import stelnet.commodity.CommodityTab;
import stelnet.commodity.IntelTracker;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.MarketRepository;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.HorizontalViewContainer;
import stelnet.ui.Size;

@AllArgsConstructor
public class IntelSelectionFactory {

    private final MarketRepository marketRepository;
    private final IntelTracker tracker;

    public HorizontalViewContainer createContainer(String commodityId, CommodityTab actionTab, Size size) {
        List<MarketApiWrapper> markets = marketRepository.getMarketByCommodityIdAndMarket(commodityId, actionTab);
        int numberOfButtons = calcNumberOfButtons(markets, size);

        List<AbstractRenderable> buttons = new LinkedList<>();
        for (int i = 0; i < numberOfButtons; i++) {
            MarketApiWrapper market = markets.get(i);
            buttons.add(new IntelButton(i + 1, actionTab, commodityId, market, tracker));
        }

        HorizontalViewContainer rows = new HorizontalViewContainer(buttons);
        rows.setSize(size);
        return rows;
    }

    private int calcNumberOfButtons(List<MarketApiWrapper> markets, Size size) {
        float width = size.getWidth() - 210;
        int buttonsOnScreen = (int) Math.floor(width / 28f);
        int maxButtons = markets.size();
        return Math.min(buttonsOnScreen, maxButtons);
    }
}
