package stelnet.commodity.data;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.MarketRepository;
import stelnet.ui.TableContent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfitTableContent implements TableContent {
    protected List rows = new ArrayList<>();
    private final MarketRepository marketRepository;
    private final String commodityId;

    public ProfitTableContent(String commodityId) {
        this.commodityId = commodityId;
        marketRepository = MarketRepository.getInstance(commodityId);
        createRows();
    }

    @Override
    public Object[] getHeaders(float width) {
        return new Object[]{
            "#", .05f * width,
            "Buy Price", .1f * width,
            "Sell Price", .1f * width,
            "Avail. / Demand", .15f * width,
            "Profit", .1f * width,
            "Buy Location", .2f * width,
            "Sell Location", .2f * width,
            "Total Dist (ly)", .1f * width
        };
    }

    @Override
    public List<RowDataElement> getRows() {
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
    }

    protected SortableRow createRowData(int i, MarketApiWrapper buyMarket, MarketApiWrapper sellMarket) {
        float profit = getPotentialProfit(buyMarket, sellMarket);
        float buyToSellDist = Misc.getDistanceLY(buyMarket.getPrimaryEntity(), sellMarket.getPrimaryEntity());
        String availDemand =
            Misc.getWithDGS(buyMarket.getAvailable(commodityId))
                + " / " +
                Misc.getWithDGS(sellMarket.getDemand(commodityId));
        return (SortableRow) new SortableRow(profit)
            .addRowNumber(i)
            .addDGSCreditsRow(buyMarket.getPriceAmount())
            .addDGSCreditsRow(sellMarket.getPriceAmount())
            .addCustomRow(Misc.getHighlightColor(), availDemand)
            .addDGSCreditsRow(profit)
            .addCustomRow(
                TableCellHelper.getClaimingFactionColor(buyMarket.getMarketAPI()),
                TableCellHelper.getLocation(buyMarket.getMarketAPI())
            ).addCustomRow(
                TableCellHelper.getClaimingFactionColor(sellMarket.getMarketAPI()),
                sellMarket.getStarSystem()
            ).addCustomRow(
                getSystemColorForDistance(buyMarket, sellMarket),
                String.format("%.1f", buyMarket.getDistanceToPlayer() + buyToSellDist)
            );
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

    @Getter
    private static class SortableRow extends RowDataElement implements Comparable {
        private final float profit;

        public SortableRow(float profit) {
            this.profit = profit;
        }

        @Override
        public int compareTo(Object o) {
            return compare(this.getProfit(), ((SortableRow) o).getProfit());
        }

        private int compare(float o1, float o2) {
            return (int) (o2 - o1);
        }
    }
}