package stelnet.board.commodity.view;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.board.commodity.IntelTracker;
import stelnet.board.commodity.market.MarketRepository;
import stelnet.board.commodity.view.button.IntelButton;
import uilib.HorizontalViewContainer;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@AllArgsConstructor
public class IntelViewFactory implements RenderableFactory {

    private final String commodityId;
    private final CommodityTab commodityTab;
    private final IntelTracker tracker;

    @Override
    public List<Renderable> create(Size size) {
        MarketRepository marketRepository = new MarketRepository(commodityId);
        List<MarketAPI> markets = marketRepository.getMarketsByType(commodityTab);
        int numberOfButtons = calcNumberOfButtons(markets, size);

        List<Renderable> buttons = new LinkedList<>();
        for (int i = 0; i < numberOfButtons; i++) {
            MarketAPI market = markets.get(i);
            buttons.add(new IntelButton(i + 1, commodityTab, commodityId, market, tracker));
        }

        HorizontalViewContainer rows = new HorizontalViewContainer(buttons);
        rows.setSize(size);
        return Collections.<Renderable>singletonList(rows);
    }

    private int calcNumberOfButtons(List<MarketAPI> markets, Size size) {
        float width = size.getWidth() - 210;
        int buttonsOnScreen = (int) Math.floor(width / 28f);
        int maxButtons = markets.size();
        return Math.min(buttonsOnScreen, maxButtons);
    }
}
