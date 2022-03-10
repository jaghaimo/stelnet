package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib.TableContent;

@RequiredArgsConstructor
public class ProfitTableContent implements TableContent {

    private final List<MarketAPI> sellMarkets;
    private final List<MarketAPI> buyMarkets;
    private final String commodityId;

    @Override
    public Object[] getHeaders(float width) {
        return new Object[] {
            "#",
            .05f * width,
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
    public List<ProfitTableRow> getRows() {
        List<ProfitTableRow> rows = createRows();
        Collections.sort(rows);
        insertNumbers(rows);
        return rows;
    }

    private List<ProfitTableRow> createRows() {
        List<ProfitTableRow> rows = new LinkedList<>();
        for (MarketAPI buyMarket : buyMarkets) {
            List<MarketAPI> profitableMarketsToSellAt = ProfitCalculator.getProfitableSellMarkets(
                buyMarket,
                sellMarkets,
                commodityId
            );
            List<ProfitTableRow> sellMarketTableRows = createSellMarketRows(buyMarket, profitableMarketsToSellAt);
            rows.addAll(sellMarketTableRows);
        }
        return rows;
    }

    private List<ProfitTableRow> createSellMarketRows(MarketAPI buyMarket, List<MarketAPI> sellMarkets) {
        List<ProfitTableRow> rows = new ArrayList<>();
        for (MarketAPI sellMarket : sellMarkets) {
            ProfitTableRow row = new ProfitTableRow(buyMarket, sellMarket, commodityId);
            rows.add(row);
        }
        return rows;
    }

    private void insertNumbers(List<ProfitTableRow> rows) {
        int i = 1;
        for (ProfitTableRow row : rows) {
            row.addNumber(i++);
        }
    }
}
