package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import stelnet.board.commodity.CommodityAction;
import stelnet.board.commodity.IntelTracker;
import uilib.HorizontalViewContainer;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@AllArgsConstructor
public class IntelViewFactory implements RenderableFactory {

    private final String commodityId;
    private final CommodityAction commodityAction;
    private final IntelTracker tracker;

    @Override
    public List<Renderable> create(final Size size) {
        final List<MarketAPI> markets = commodityAction.getMarkets(commodityId);
        final int numberOfButtons = calcNumberOfButtons(markets, size);

        final List<Renderable> buttons = new LinkedList<>();
        for (int i = 0; i < numberOfButtons; i++) {
            final MarketAPI market = markets.get(i);
            buttons.add(new IntelViewButton(i + 1, commodityId, market, tracker));
        }

        final HorizontalViewContainer rows = new HorizontalViewContainer(buttons);
        rows.setSize(size);
        return Collections.<Renderable>singletonList(rows);
    }

    private int calcNumberOfButtons(final List<MarketAPI> markets, final Size size) {
        final float width = size.getWidth() - 210;
        final int buttonsOnScreen = (int) Math.floor(width / 28f);
        final int maxButtons = markets.size();
        return Math.min(buttonsOnScreen, maxButtons);
    }
}
