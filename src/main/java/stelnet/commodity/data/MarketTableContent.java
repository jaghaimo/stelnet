package stelnet.commodity.data;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.ui.TableContent;

@Getter
public abstract class MarketTableContent implements TableContent {

    protected String commodityId;
    protected final List<MarketApiWrapper> markets;
    protected List<TableRow> rows = new ArrayList<>();

    protected MarketTableContent(String commodityId, List<MarketApiWrapper> markets) {
        this.commodityId = commodityId;
        this.markets = markets;
        createRows();
    }

    protected Object[] getHeader(float maxWidth, String availableOrDemand, String excessOrDeficit) {
        float width = maxWidth - 24;
        // @formatter:off
        Object header[] = {
                "#", .05f * width,
                "Price", .1f * width,
                availableOrDemand, .11f * width,
                excessOrDeficit, .11f * width,
                "Location", .32f * width,
                "Star system", .21f * width,
                "Dist (ly)", .1f * width
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
