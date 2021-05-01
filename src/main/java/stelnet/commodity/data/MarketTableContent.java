package stelnet.commodity.data;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.helper.StarSystemHelper;
import stelnet.ui.TableContent;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class MarketTableContent implements TableContent {

    protected String commodityId;
    protected final List<MarketApiWrapper> markets;
    protected List<RowDataElement> rows = new ArrayList<>();

    protected MarketTableContent(String commodityId, List<MarketApiWrapper> markets) {
        this.commodityId = commodityId;
        this.markets = markets;
        createRows();
    }

    protected Object[] getHeader(float width, String availableOrDemand, String excessOrDeficit) {
        Object header[] = {
                "#", .05f * width,
                "Price", .1f * width,
                availableOrDemand, .1f * width,
                excessOrDeficit, .1f * width,
                "Location", .3f * width,
                "Star system", .2f * width,
                "Dist (ly)",
                .1f * width
        };
        return header;
    }

    protected void createRows() {
        rows.clear();
        int i = 1;

        for (MarketApiWrapper market : markets) {
            RowDataElement row = createRowData(i++, market);
            rows.add(row);
        }
    }

    protected abstract RowDataElement createRowData(int i, MarketApiWrapper market);

    protected RowDataElement createRenderableRow(
            int i,
            MarketApiWrapper market,
            float price,
            int available,
            int excess
    ) {
        return new RowDataElement()
                .addRowNumber(i)
                .addDGSCreditsRow(price)
                .addDGSRow(available)
                .addExcessRow(excess)
                .addCustomRow(
                        TableCellHelper.getClaimingFactionColor(market.getMarketAPI()),
                        StarSystemHelper.getName(market.getMarketAPI().getStarSystem())
                ).addCustomRow(
                        TableCellHelper.getClaimingFactionColor(market.getMarketAPI()),
                        StarSystemHelper.getName(market.getMarketAPI().getStarSystem())
                ).addCustomRow(
                        Misc.getHighlightColor(),
                        TableCellHelper.getDistance(market.getMarketAPI())
                );
    }

}
