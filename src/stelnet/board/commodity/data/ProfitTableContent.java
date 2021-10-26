package stelnet.board.commodity.data;

import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import stelnet.board.commodity.market.MarketApiWrapper;
import stelnet.board.commodity.market.MarketRepository;
import stelnet.util.DistanceCalculator;
import stelnet.util.L10n;
import uilib.TableContent;

public class ProfitTableContent implements TableContent {

    protected List<SortableRow> rows = new ArrayList<>();
    private final MarketRepository marketRepository;
    private final String commodityId;

    public ProfitTableContent(String commodityId, MarketRepository marketRepository) {
        this.commodityId = commodityId;
        this.marketRepository = marketRepository;
        createRows();
    }

    @Override
    public Object[] getHeaders(float maxWidth) {
        float width = maxWidth - 22;
        return new Object[] {
            "#",
            .05f * width,
            L10n.get("commodityHeaderProfit"),
            .12f * width,
            L10n.get("commodityHeaderBuyLocation"),
            .2f * width,
            L10n.get("commodityHeaderSellLocation"),
            .2f * width,
            L10n.get("commodityHeaderBuyAvailable"),
            .165f * width,
            L10n.get("commodityHeaderSellDemand"),
            .165f * width,
            L10n.get("commodityHeaderTrip"),
            .1f * width,
        };
    }

    @Override
    public List<SortableRow> getRows() {
        return rows;
    }

    public void createRows() {
        List<MarketApiWrapper> buyMarkets = marketRepository.getBuyMarkets();
        List<MarketApiWrapper> sellMarkets = marketRepository.getSellMarkets();
        int i = 1;
        for (MarketApiWrapper buyMarket : buyMarkets) {
            for (MarketApiWrapper sellMarket : sellMarkets) {
                if (getPotentialProfit(buyMarket, sellMarket) <= 0) {
                    continue;
                }
                SortableRow row = createRowData(i, buyMarket, sellMarket);
                rows.add(row);
            }
            i++;
        }
        Collections.sort(rows);
        int maxRows = Math.min(rows.size(), 30);
        rows = rows.subList(0, maxRows);
    }

    protected SortableRow createRowData(int i, MarketApiWrapper buyMarket, MarketApiWrapper sellMarket) {
        Color color = getRowColor(buyMarket, sellMarket);
        float profit = getPotentialProfit(buyMarket, sellMarket);
        float buyToSellDistance = DistanceCalculator.getDistanceLY(
            buyMarket.getPrimaryEntity(),
            sellMarket.getPrimaryEntity()
        );
        float totalDistance = buyMarket.getDistanceToPlayer() + buyToSellDistance;
        SortableRow sortableRow = new SortableRow(profit);
        sortableRow.addRowNumberCell(i);
        sortableRow.addDGSCreditsCell(color, profit);
        sortableRow.addRow(
            TableCellHelper.getFactionColor(buyMarket.getMarketAPI().getFaction()),
            TableCellHelper.getLocation(buyMarket.getMarketAPI())
        );
        sortableRow.addRow(
            TableCellHelper.getFactionColor(sellMarket.getMarketAPI().getFaction()),
            TableCellHelper.getLocation(sellMarket.getMarketAPI())
        );
        sortableRow.addRow(
            color,
            Misc.getDGSCredits(buyMarket.getPriceAmount()) + " / " + buyMarket.getAvailable(commodityId)
        );
        sortableRow.addRow(
            color,
            Misc.getDGSCredits(sellMarket.getPriceAmount()) + " / " + sellMarket.getDemand(commodityId)
        );
        sortableRow.addRow(color, String.format("%.1f", totalDistance));
        return sortableRow;
    }

    private float getPotentialProfit(MarketApiWrapper buyFromMarket, MarketApiWrapper sellToMarket) {
        int available = buyFromMarket.getAvailable(commodityId);
        int demand = sellToMarket.getDemand(commodityId);
        int quantity = Math.min(available, demand);

        if (quantity <= 0) {
            return 0;
        }

        float bought = buyFromMarket.getPriceAmount(quantity);
        float sold = sellToMarket.getPriceAmount(quantity);
        return Math.max(0, sold - bought);
    }

    private Color getRowColor(MarketApiWrapper buyMarket, MarketApiWrapper sellMarket) {
        String buySystemName = buyMarket.getStarSystem();
        String sellSystemName = sellMarket.getStarSystem();
        Color color = Misc.getTextColor();
        if (buySystemName.equals(sellSystemName)) {
            color = Misc.getHighlightColor();
        }
        return color;
    }
}
