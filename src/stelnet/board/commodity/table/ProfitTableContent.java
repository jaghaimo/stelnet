package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib.TableContent;

@RequiredArgsConstructor
public class ProfitTableContent extends ProfitCalculator implements TableContent {

    private static final int MAX_ROWS = 99;
    private static final int MINIMUM_PROFIT_VALUE = 10000;

    private final List<MarketAPI> sellMarkets;
    private final List<MarketAPI> buyMarkets;
    private final String commodityId;

    @Override
    public Object[] getHeaders(float width) {
        return new Object[] {
            "#",
            .05f * width,
            "Profit",
            .17f * width,
            "Buy Price",
            .14f * width,
            "Sell Price",
            .14f * width,
            "Buy Location",
            .2f * width,
            "Sell Location",
            .2f * width,
            "Dist (ly)",
            .1f * width,
        };
    }

    @Override
    public List<ProfitTableRow> getRows() {
        List<ProfitTableRow> rows = createRows();
        Collections.sort(rows);
        injectNumbers(rows);
        int limit = Math.min(MAX_ROWS, rows.size());
        return rows.subList(0, limit);
    }

    private List<ProfitTableRow> createRows() {
        List<ProfitTableRow> rows = new LinkedList<>();
        for (MarketAPI buyMarket : buyMarkets) {
            List<MarketAPI> profitableMarketsToSellAt = getProfitableSellMarkets(buyMarket, sellMarkets, commodityId);
            List<ProfitTableRow> sellMarketTableRows = createSellMarketRows(buyMarket, profitableMarketsToSellAt);
            rows.addAll(sellMarketTableRows);
        }
        return rows;
    }

    private List<ProfitTableRow> createSellMarketRows(MarketAPI buyMarket, List<MarketAPI> sellMarkets) {
        List<ProfitTableRow> rows = new LinkedList<>();
        for (MarketAPI sellMarket : sellMarkets) {
            ProfitTableRow row = new ProfitTableRow(buyMarket, sellMarket, commodityId);
            rows.add(row);
        }
        return rows;
    }

    private void injectNumbers(List<ProfitTableRow> rows) {
        int i = 1;
        for (ProfitTableRow row : rows) {
            row.addNumber(i++);
        }
    }

    private List<MarketAPI> getProfitableSellMarkets(
        MarketAPI buyMarket,
        List<MarketAPI> sellMarkets,
        String commodityId
    ) {
        List<MarketAPI> rows = new LinkedList<>();
        for (MarketAPI sellMarket : sellMarkets) {
            if (calculateProfit(buyMarket, sellMarket, commodityId) >= MINIMUM_PROFIT_VALUE) {
                rows.add(sellMarket);
            }
        }
        return rows;
    }
}
