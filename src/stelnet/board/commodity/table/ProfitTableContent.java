package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;
import uilib.TableContent;

@RequiredArgsConstructor
public class ProfitTableContent implements TableContent {

    public static int MAX_ROWS = 50;
    public static int MINIMUM_PROFIT_VALUE = 10000;

    private final List<MarketAPI> sellMarkets;
    private final List<MarketAPI> buyMarkets;
    private final String commodityId;

    @Override
    public Object[] getHeaders(float width) {
        return new Object[] {
            "#",
            .05f * width,
            L10n.get(CommodityL10n.HEADER_PROFIT),
            .17f * width,
            L10n.get(CommodityL10n.HEADER_BUY_LOCATION),
            .22f * width,
            L10n.get(CommodityL10n.HEADER_BUY_EXPENSE),
            .12f * width,
            L10n.get(CommodityL10n.HEADER_SELL_LOCATION),
            .22f * width,
            L10n.get(CommodityL10n.HEADER_SELL_REVENUE),
            .12f * width,
            L10n.get(CommodityL10n.HEADER_TRIP),
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
            List<ProfitTableRow> sellMarketTableRows = createSellMarketRows(buyMarket, sellMarkets);
            rows.addAll(sellMarketTableRows);
        }
        return rows;
    }

    private List<ProfitTableRow> createSellMarketRows(MarketAPI buyMarket, List<MarketAPI> sellMarkets) {
        List<ProfitTableRow> rows = new LinkedList<>();
        for (MarketAPI sellMarket : sellMarkets) {
            ProfitTableRow row = new ProfitTableRow(buyMarket, sellMarket, commodityId);
            if (row.getProfit() >= MINIMUM_PROFIT_VALUE) {
                rows.add(row);
            }
        }
        return rows;
    }

    private void injectNumbers(List<ProfitTableRow> rows) {
        int i = 1;
        for (ProfitTableRow row : rows) {
            row.addNumber(i++);
        }
    }
}
