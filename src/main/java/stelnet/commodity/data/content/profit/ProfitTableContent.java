package stelnet.commodity.data.content.profit;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import stelnet.commodity.data.content.MarketTableContent;
import stelnet.commodity.data.content.buy.SupplyPrice;
import stelnet.commodity.data.content.sell.SellMarketFactory;

import java.util.ArrayList;
import java.util.List;

public class ProfitTableContent extends MarketTableContent {
    private List<MarketAPI> sellmarkets;

    public ProfitTableContent(String commodityId, List<MarketAPI> buyMarkets) {
        super(commodityId, buyMarkets, new SupplyPrice(commodityId));
        this.sellmarkets = new SellMarketFactory(commodityId).getMarkets();
    }

    @Override
    public Object[] getHeaders(float width) {
        Object header[] = {
                "#", .05f * width,
                "Buy Price", .1f * width,
                "Sell Price", .1f * width,
                "Available", .1f * width,
                "Profit", .1f * width,
                "Buy Location", .2f * width,
                "Sell Location", .2f * width
        };
        return header;
    }

    @Override
    public List<Object[]> getRows() {
        List<Object[]> content = new ArrayList<>();
        int i = 1;
        for (MarketAPI buyMarket : super.markets) {
            int j = 1;
            for (MarketAPI sellMarket : sellmarkets) {
                if (j > 5) {
                    continue;
                }
                Object[] row = getRow(i, buyMarket, sellMarket);
                content.add(row);
                j++;
            }
            i++;
        }
        return content;
    }

    // We fail to comply with Interface segregation principle here.
    @Override
    protected Object[] getRow(int i, MarketAPI market) {
        return new Object[0];
    }

    protected Object[] getRow(int i, MarketAPI buyMarket, MarketAPI sellMarket) {
        CommodityOnMarketAPI commodity = buyMarket.getCommodityData(commodityId);
        float buyPrice = getPrice(buyMarket);
        float sellPrice = getPrice(sellMarket);
        int available = helper.getAvailable(commodity);
        return getRow(i, buyPrice, sellPrice, available, buyMarket, sellMarket);
    }

    protected Object[] getRow(int i, float buyPrice, float sellPrice, int available, MarketAPI buyMarket, MarketAPI sellMarket) {
        Object[] row = new Object[21];
        // Position
        row[0] = Alignment.MID;
        row[1] = Misc.getGrayColor();
        row[2] = String.valueOf(i) + ".";

        // Buy Price
        row[3] = Alignment.MID;
        row[4] = Misc.getHighlightColor();
        row[5] = Misc.getDGSCredits(buyPrice);

        // Sell Price
        row[6] = Alignment.MID;
        row[7] = Misc.getHighlightColor();
        row[8] = Misc.getDGSCredits(sellPrice);

        // Available
        row[9] = Alignment.MID;
        row[10] = Misc.getHighlightColor();
        row[11] = Misc.getWithDGS(available);

        // Profit
        row[12] = Alignment.MID;
        row[13] = Misc.getHighlightColor();
        float bought = buyPrice * available;
        float sold = sellPrice * available;
        row[14] = Misc.getDGSCredits(sold - bought);

        // Buy Location
        row[15] = Alignment.LMID;
        row[16] = buyMarket.getTextColorForFactionOrPlanet();
        row[17] = helper.getLocation(buyMarket);

        // Buy Location
        row[18] = Alignment.LMID;
        row[19] = sellMarket.getTextColorForFactionOrPlanet();
        row[20] = helper.getLocation(sellMarket);
        return row;
    }
}
