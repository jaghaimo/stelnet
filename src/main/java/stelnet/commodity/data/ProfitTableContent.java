package stelnet.commodity.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.MarketRepository;
import stelnet.helper.DistanceHelper;
import stelnet.ui.TableContent;

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
        // @formatter:off
        return new Object[]{
                "#", .05f * width,
                "Profit", .12f * width,
                "Buy location", .2f * width,
                "Sell location", .2f * width,
                "Buy / Available", .165f * width,
                "Sell / Demand", .165f * width,
                "Trip (ly)", .1f * width
        };
        // @formatter:on
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
        float buyToSellDistance = DistanceHelper.getDistanceLY(buyMarket.getPrimaryEntity(),
                sellMarket.getPrimaryEntity());
        float totalDistance = buyMarket.getDistanceToPlayer() + buyToSellDistance;
        SortableRow sortableRow = new SortableRow(profit);
        sortableRow.addRowNumberCell(i);
        // @formatter:off
        sortableRow.addDGSCreditsCell(
                color,
                profit
        );
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
        sortableRow.addRow(
                color,
                String.format("%.1f", totalDistance)
        );
        // @formatter:on
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
