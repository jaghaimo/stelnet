package stelnet.commodity.data.content;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.data.Price;
import stelnet.commodity.data.TableCellHelper;
import stelnet.helper.StarSystemHelper;
import stelnet.ui.TableContent;

public abstract class MarketTableContent implements TableContent {

    protected String commodityId;
    protected TableCellHelper helper;
    protected List<MarketAPI> markets;
    private Price price;

    protected MarketTableContent(String commodityId, List<MarketAPI> markets, Price price) {
        this.commodityId = commodityId;
        this.helper = new TableCellHelper();
        this.markets = markets;
        this.price = price;
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, "", "");
    }

    @Override
    public List<Object[]> getRows() {
        List<Object[]> content = new ArrayList<>();
        int i = 1;
        for (MarketAPI market : markets) {
            Object[] row = getRow(i++, market);
            content.add(row);
        }
        return content;
    }

    @Override
    public int getSize() {
        return markets.size();
    }

    protected Object[] getHeader(float width, String availableOrDemand, String excessOrDeficit) {
        Object header[] = {
                "#", .05f * width,
                "Price", .1f * width,
                availableOrDemand, .1f * width,
                excessOrDeficit, .1f * width,
                "Location", .3f * width,
                "Star system", .2f * width,
                "Dist (ly)", .1f * width
        };
        return header;
    }

    protected Object[] getRow(int i, MarketAPI market, CommodityOnMarketAPI commodity, float price, int available,
            int excess) {
        Object[] row = new Object[21];
        // Position
        row[0] = Alignment.MID;
        row[1] = Misc.getGrayColor();
        row[2] = String.valueOf(i) + ".";
        // Price
        row[3] = Alignment.MID;
        row[4] = Misc.getHighlightColor();
        row[5] = Misc.getDGSCredits(price);
        // Available or Demand
        row[6] = Alignment.MID;
        row[7] = Misc.getHighlightColor();
        row[8] = Misc.getWithDGS(available);
        // Excess or Deficit
        row[9] = Alignment.MID;
        row[10] = helper.getExcessColor(excess);
        row[11] = helper.getExcessValue(excess);
        // Location
        row[12] = Alignment.LMID;
        row[13] = market.getTextColorForFactionOrPlanet();
        row[14] = helper.getLocation(market);
        // Star system
        row[15] = Alignment.MID;
        row[16] = helper.getClaimingFactionColor(market);
        row[17] = StarSystemHelper.getName(market.getStarSystem());
        // Distance
        row[18] = Alignment.MID;
        row[19] = Misc.getHighlightColor();
        row[20] = helper.getDistance(market);

        return row;
    }

    protected float getPrice(MarketAPI market) {
        return price.getPrice(market);
    }

    protected abstract Object[] getRow(int i, MarketAPI market);
}
