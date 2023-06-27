package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public Object[] getHeaders(final float width) {
        return new Object[] {
            "#",
            .05f * width,
            L10n.commodity("HEADER_PROFIT"),
            .17f * width,
            L10n.commodity("HEADER_BUY_LOCATION"),
            .22f * width,
            L10n.commodity("HEADER_BUY_EXPENSE"),
            .12f * width,
            L10n.commodity("HEADER_SELL_LOCATION"),
            .22f * width,
            L10n.commodity("HEADER_SELL_REVENUE"),
            .12f * width,
            L10n.commodity("HEADER_TRIP"),
            .1f * width,
        };
    }

    @Override
    public List<ProfitTableRow> getRows() {
        final List<ProfitTableRow> rows = createRows();
        Collections.sort(rows);
        injectNumbers(rows);
        final int limit = Math.min(MAX_ROWS, rows.size());
        return rows.subList(0, limit);
    }

    private List<ProfitTableRow> createRows() {
        final List<ProfitTableRow> rows = new LinkedList<>();
        for (final MarketAPI buyMarket : buyMarkets) {
            final List<ProfitTableRow> sellMarketTableRows = createSellMarketRows(buyMarket, sellMarkets);
            rows.addAll(sellMarketTableRows);
        }
        return rows;
    }

    private List<ProfitTableRow> createSellMarketRows(final MarketAPI buyMarket, final List<MarketAPI> sellMarkets) {
        final List<ProfitTableRow> rows = new LinkedList<>();
        for (final MarketAPI sellMarket : sellMarkets) {
            final ProfitTableRow row = new ProfitTableRow(buyMarket, sellMarket, commodityId);
            if (row.getProfit() >= MINIMUM_PROFIT_VALUE) {
                rows.add(row);
            }
        }
        return rows;
    }

    private void injectNumbers(final List<ProfitTableRow> rows) {
        int i = 1;
        for (final ProfitTableRow row : rows) {
            row.addNumber(i++);
        }
    }
}
