package stelnet.board.commodity.market.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;
import stelnet.util.StringUtils;
import stelnet.util.TableCellHelper;
import uilib.TableContent;

@RequiredArgsConstructor
@Getter
public abstract class MarketTableContent implements TableContent {

    protected final String commodityId;
    protected final List<MarketAPI> markets;
    protected List<TableRow> rows = new ArrayList<>();

    protected Object[] getHeader(float width, Enum<?> availableOrDemand, Enum<?> excessOrDeficit) {
        Object header[] = {
            "#",
            .05f * width,
            L10n.get(CommodityL10n.HEADER_PRICE),
            .1f * width,
            L10n.get(availableOrDemand),
            .11f * width,
            L10n.get(excessOrDeficit),
            .11f * width,
            L10n.get(CommodityL10n.HEADER_LOCATION),
            .32f * width,
            L10n.get(CommodityL10n.HEADER_SYSTEM),
            .21f * width,
            L10n.get(CommodityL10n.HEADER_DISTANCE),
            .1f * width,
        };
        return header;
    }

    protected void createRows() {
        rows.clear();
        int i = 1;

        for (MarketAPI market : markets) {
            TableRow row = createRowData(i++, market);
            rows.add(row);
        }
    }

    protected abstract TableRow createRowData(int i, MarketAPI market);

    protected abstract float getPrice(MarketAPI market);

    protected TableRow createRowData(int i, MarketAPI market, int demandOrAvailability, int excessOrDeficit) {
        String starSystem = StringUtils.getStarSystem(market);
        TableRow rowDataElement = new TableRow();
        rowDataElement.addRowNumberCell(i);
        rowDataElement.addDGSCreditsCell(getPrice(market));
        rowDataElement.addDGSCell(demandOrAvailability);
        rowDataElement.addExcessDemandCell(excessOrDeficit);
        rowDataElement.addCell(
            TableCellHelper.getFactionColor(market.getFaction()),
            StringUtils.getMarketAndFactionDisplayName(market)
        );
        rowDataElement.addCell(TableCellHelper.getClaimingFactionColor(market), starSystem);
        rowDataElement.addCell(
            Misc.getTextColor(),
            String.format("%.1f", Misc.getDistanceToPlayerLY(market.getPrimaryEntity()))
        );
        return rowDataElement;
    }

    protected CommodityOnMarketAPI getCommodityData(MarketAPI market) {
        return market.getCommodityData(commodityId);
    }
}
