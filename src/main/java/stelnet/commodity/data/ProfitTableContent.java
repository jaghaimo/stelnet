package stelnet.commodity.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.MarketRepository;
import stelnet.ui.TableContent;
import stelnet.ui.TableContentRow;

public class ProfitTableContent implements TableContent {

    // TODO: this generic needs to be parametrized
    protected List rows = new ArrayList<>();
    private final MarketRepository marketRepository;
    private final String commodityId;

    public ProfitTableContent(String commodityId, MarketRepository marketRepository) {
        this.commodityId = commodityId;
        this.marketRepository = marketRepository;
        createRows();
    }

    @Override
    public Object[] getHeaders(float width) {
        return new Object[]{
                "Buy #", .05f * width,
                "Buy Price", .1f * width,
                "Sell Price", .1f * width,
                "Avail. / Demand", .15f * width,
                "Profit", .1f * width,
                "P. Ly", .1f * width,
                "Buy Location", .2f * width,
                "Sell Location", .1f * width,
                "Total Dist (ly)", .1f * width
        };
    }

    @Override
    public List<TableContentRow> getRows() {
        return rows;
    }

    public void createRows() {
        List<MarketApiWrapper> sellMarkets = marketRepository.getSellMarketByCommodity(commodityId);
        List<MarketApiWrapper> buyMarkets = marketRepository.getBuyMarketByCommodity(commodityId);

        int i = 1;
        for (MarketApiWrapper buyMarket : buyMarkets) {
            for (MarketApiWrapper sellMarket : sellMarkets) {
                if (getPotentialProfit(buyMarket, sellMarket) <= 100000) {
                    continue;
                }

                SortableRow row = createRowData(i, buyMarket, sellMarket);
                rows.add(row);
            }
            i++;
        }
        Collections.sort(rows);
    }

    protected SortableRow createRowData(int i, MarketApiWrapper buyMarket, MarketApiWrapper sellMarket) {
        float profit = getPotentialProfit(buyMarket, sellMarket);
        float buyToSellDist = Misc.getDistanceLY(buyMarket.getPrimaryEntity(), sellMarket.getPrimaryEntity());
        float totalDist = buyMarket.getDistanceToPlayer() + buyToSellDist;
        String availDemand = Misc.getWithDGS(buyMarket.getAvailable(commodityId)) + " / " + Misc.getWithDGS(sellMarket.getDemand(commodityId));
        SortableRow sortableRow = new SortableRow(profit);
        sortableRow.addRowNumber(i);
        sortableRow.addDGSCreditsRow(buyMarket.getPriceAmount());
        sortableRow.addDGSCreditsRow(sellMarket.getPriceAmount());
        sortableRow.addRow(Misc.getHighlightColor(), availDemand);
        sortableRow.addDGSCreditsRow(profit);
        sortableRow.addDGSCreditsRow(profit / totalDist);
        sortableRow.addRow(
                TableCellHelper.getClaimingFactionColor(buyMarket.getMarketAPI()),
                TableCellHelper.getLocation(buyMarket.getMarketAPI())
        );
        sortableRow.addRow(
                TableCellHelper.getClaimingFactionColor(sellMarket.getMarketAPI()),
                sellMarket.getStarSystem()
        );
        sortableRow.addRow(
                getSystemColorForDistance(buyMarket, sellMarket),
                String.format("%.1f", totalDist)
        );
        return sortableRow;
    }

    private float getPotentialProfit(MarketApiWrapper buyFromMarket, MarketApiWrapper sellToMarket) {
        float buyPrice = buyFromMarket.getPriceAmount();
        float sellPrice = sellToMarket.getPriceAmount();

        if (buyPrice >= sellPrice) {
            return 0;
        }

        int available = buyFromMarket.getAvailable(commodityId);
        int demand = sellToMarket.getDemand(commodityId);

        if (available < 400) {
            return 0;
        }

        if (demand > available) {
            demand = available;
        }

        float bought = buyPrice * demand;
        float sold = sellPrice * demand;
        return sold - bought;
    }

    private Color getSystemColorForDistance(MarketApiWrapper buyMarket, MarketApiWrapper sellMarket) {
        String buySystemName = buyMarket.getStarSystem();
        String sellSystemName = sellMarket.getStarSystem();
        Color color = Misc.getGrayColor();
        if (buySystemName.equals(sellSystemName)) {
            color = Misc.getHighlightColor();
        }
        return color;
    }

    // TODO: consider merging rowdata with sortablerow
    @Getter
    private static class SortableRow extends RowDataElement implements Comparable<SortableRow> {
        private final float profit;

        public SortableRow(float profit) {
            this.profit = profit;
        }

        @Override
        public int compareTo(SortableRow o) {
            return compare(this.getProfit(), o.getProfit());
        }

        private int compare(float o1, float o2) {
            return (int) (o2 - o1);
        }
    }
}
