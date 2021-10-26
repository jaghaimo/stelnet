package stelnet.board.commodity.market.table;

import com.fs.starfarer.api.util.Misc;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.market.MarketApiWrapper;
import stelnet.util.L10n;
import stelnet.util.TableCellHelper;
import uilib.TableContent;

@RequiredArgsConstructor
@Getter
public abstract class MarketTableContent implements TableContent {

    protected final String commodityId;
    protected final List<MarketApiWrapper> markets;
    protected List<TableRow> rows = new ArrayList<>();

    protected Object[] getHeader(float maxWidth, String availableOrDemand, String excessOrDeficit) {
        float width = maxWidth - 24;
        Object header[] = {
            "#",
            .05f * width,
            L10n.get("commodityHeaderPrice"),
            .1f * width,
            L10n.get(availableOrDemand),
            .11f * width,
            L10n.get(excessOrDeficit),
            .11f * width,
            L10n.get("commodityHeaderLocation"),
            .32f * width,
            L10n.get("commodityHeaderSystem"),
            .21f * width,
            L10n.get("commodityHeaderDistance"),
            .1f * width,
        };
        return header;
    }

    protected void createRows() {
        rows.clear();
        int i = 1;

        for (MarketApiWrapper market : markets) {
            TableRow row = createRowData(i++, market);
            rows.add(row);
        }
    }

    protected abstract TableRow createRowData(int i, MarketApiWrapper market);

    protected TableRow createRowData(int i, MarketApiWrapper market, int demandOrAvailability, int excessOrDeficit) {
        TableRow rowDataElement = new TableRow();
        rowDataElement.addRowNumberCell(i);
        rowDataElement.addDGSCreditsCell(market.getPriceAmount());
        rowDataElement.addDGSCell(demandOrAvailability);
        rowDataElement.addExcessDemandCell(excessOrDeficit);
        rowDataElement.addRow(
            TableCellHelper.getFactionColor(market.getMarketAPI().getFaction()),
            market.getMarketAndFactionDisplayName()
        );
        rowDataElement.addRow(TableCellHelper.getClaimingFactionColor(market.getMarketAPI()), market.getStarSystem());
        rowDataElement.addRow(Misc.getTextColor(), String.format("%.1f", market.getDistanceToPlayer()));
        return rowDataElement;
    }
}
