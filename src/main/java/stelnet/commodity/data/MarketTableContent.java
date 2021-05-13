package stelnet.commodity.data;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.l10n.CommodityBundle;
import stelnet.ui.TableContent;

@RequiredArgsConstructor
@Getter
public abstract class MarketTableContent implements TableContent {

    protected final String commodityId;
    protected final List<MarketApiWrapper> markets;
    protected List<TableRow> rows = new ArrayList<>();

    protected Object[] getHeader(float maxWidth, String availableOrDemand, String excessOrDeficit) {
        float width = maxWidth - 24;
        CommodityBundle bundle = new CommodityBundle();
        // @formatter:off
        Object header[] = {
                "#", .05f * width,
                bundle.translateHeader("Price"), .1f * width,
                bundle.translateHeader(availableOrDemand), .11f * width,
                bundle.translateHeader(excessOrDeficit), .11f * width,
                bundle.translateHeader("Location"), .32f * width,
                bundle.translateHeader("System"), .21f * width,
                bundle.translateHeader("Distance"), .1f * width
        };
        // @formatter:on
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
        rowDataElement.addRow(TableCellHelper.getFactionColor(market.getMarketAPI().getFaction()),
                market.getMarketAndFactionDisplayName());
        rowDataElement.addRow(TableCellHelper.getClaimingFactionColor(market.getMarketAPI()), market.getStarSystem());
        rowDataElement.addRow(Misc.getTextColor(), String.format("%.1f", market.getDistanceToPlayer()));
        return rowDataElement;
    }
}
