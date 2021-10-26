package stelnet.board.commodity.view;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import stelnet.board.commodity.CommodityState;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.board.commodity.IntelTracker;
import stelnet.board.commodity.market.MarketApiWrapper;
import stelnet.board.commodity.market.MarketRepository;
import stelnet.board.commodity.view.button.IntelButton;
import uilib.HorizontalViewContainer;
import uilib.Renderable;
import uilib.ViewContainerFactory;
import uilib.property.Size;

@AllArgsConstructor
public class IntelViewFactory implements ViewContainerFactory {

    private final String commodityId;
    private final CommodityTab actionTab;
    private final IntelTracker tracker;

    public IntelViewFactory(CommodityState commodityState) {
        this(commodityState.getCommodityId(), commodityState.getActiveTab(), commodityState.getIntelTracker());
    }

    @Override
    public Renderable createContainer(Size size) {
        MarketRepository marketRepository = new MarketRepository(commodityId);
        List<MarketApiWrapper> markets = marketRepository.getMarketsByType(actionTab);
        int numberOfButtons = calcNumberOfButtons(markets, size);

        List<Renderable> buttons = new LinkedList<>();
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
