package stelnet.board.commodity.view.board.profit;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.AllArgsConstructor;
import stelnet.board.commodity.CommodityAction;
import stelnet.board.commodity.IntelTracker;
import stelnet.board.commodity.table.profit.ProfitTableContent;
import stelnet.board.commodity.table.profit.TableProfitRow;
import stelnet.board.commodity.view.board.IntelButton;
import uilib.HorizontalViewContainer;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ProfitView implements RenderableFactory {
    private final String commodityId;
    private final CommodityAction commodityAction;
    private final IntelTracker tracker;

    @Override
    public List<Renderable> create(Size uiSize) {
        List<MarketAPI> buyMarkets = commodityAction.getBuyMarkets(commodityId);
        List<MarketAPI> sellMarkets = commodityAction.getSellMarkets(commodityId);
        ProfitTableContent tableContent = new ProfitTableContent(sellMarkets, buyMarkets, commodityId);
        List<TableProfitRow> profitRows = tableContent.getRows();


        int numberOfButtons = calcNumberOfButtons(profitRows.size(), uiSize);

        List<Renderable> buttons = new LinkedList<>();
        for (int i = 0; i < numberOfButtons; i++) {
            TableProfitRow profitRow = profitRows.get(i);
            buttons.add(new ProfitIntelButton(i + 1, commodityId, profitRow.getBuyMarket(), profitRow.getSellMarket(), tracker));
        }

        HorizontalViewContainer rows = new HorizontalViewContainer(buttons);
        rows.setSize(uiSize);
        return Collections.<Renderable>singletonList(rows);
    }

    private int calcNumberOfButtons(int maxButtons, Size size) {
        float width = size.getWidth() - 210;
        int buttonsOnScreen = (int) Math.floor(width / 28f);
        return Math.min(buttonsOnScreen, maxButtons);
    }
}
