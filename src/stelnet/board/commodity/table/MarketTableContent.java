package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityL10n;
import stelnet.board.commodity.price.Price;
import stelnet.util.L10n;
import uilib.TableContent;

@RequiredArgsConstructor
@Getter
public abstract class MarketTableContent implements TableContent {

    protected final String commodityId;
    protected final List<MarketAPI> markets;
    protected final Price price;
    protected List<MarketTableRow> rows = new LinkedList<>();

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
            MarketTableRow row = createRowData(i++, market);
            rows.add(row);
        }
    }

    protected CommodityOnMarketAPI getCommodityData(MarketAPI market) {
        return market.getCommodityData(commodityId);
    }

    protected abstract MarketTableRow createRowData(int i, MarketAPI market);
}
