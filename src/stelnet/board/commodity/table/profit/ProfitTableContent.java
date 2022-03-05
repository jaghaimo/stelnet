package stelnet.board.commodity.table.profit;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib.TableContent;

@RequiredArgsConstructor
public class ProfitTableContent implements TableContent {

    private final List<MarketAPI> sellmarkets;
    private final List<MarketAPI> buyMarkets;
    private final String commodityId;

    @Override
    public Object[] getHeaders(float width) {
        return new Object[] {
            "Buy Price",
            .1f * width,
            "Sell Price",
            .1f * width,
            "Avail. / Demand",
            .15f * width,
            "Profit",
            .1f * width,
            "Buy Location",
            .2f * width,
            "Sell Location",
            .2f * width,
            "Total Dist (ly)",
            .1f * width,
        };
    }

    @Override
    public List<TableProfitRow> getRows() {
        List<TableProfitRow> rows = new ArrayList<>();
        for (MarketAPI buyMarket : buyMarkets) {
            List<TableProfitRow> sellMarkets = getViableSellMarketsForBuy(buyMarket);
            rows.addAll(sellMarkets);
        }

        Collections.sort(rows);

        return rows;
    }

    private List<TableProfitRow> getViableSellMarketsForBuy(MarketAPI buyMarket) {
        List<TableProfitRow> rows = new ArrayList<>();

        for (MarketAPI sellMarket : sellmarkets) {
            if (ProfitCalculator.getPotentialProfit(buyMarket, sellMarket, commodityId) <= 100000) {
                continue;
            }

            TableProfitRow row = new TableProfitRow(buyMarket, sellMarket, commodityId);
            rows.add(row);
        }
        return rows;
    }
}
