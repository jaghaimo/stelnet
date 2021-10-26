package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.Builder;
import lombok.Data;
import stelnet.board.commodity.market.price.Price;
import stelnet.util.DistanceCalculator;
import stelnet.util.TableCellHelper;

@Data
@Builder
public class MarketApiWrapper {

    private Price price;
    private MarketAPI marketAPI;

    public float getPriceAmount() {
        return price.getPriceAmount(marketAPI);
    }

    public float getPriceAmount(int quantity) {
        return price.getPriceAmount(marketAPI, quantity);
    }

    public CommodityOnMarketAPI getCommodityData(String commodityId) {
        return marketAPI.getCommodityData(commodityId);
    }

    public int getAvailable(String commodityId) {
        CommodityOnMarketAPI commodityData = getCommodityData(commodityId);
        return TableCellHelper.getAvailable(commodityData);
    }

    public int getDemand(String commodityId) {
        CommodityOnMarketAPI commodityData = getCommodityData(commodityId);
        return TableCellHelper.getDemand(marketAPI, commodityData);
    }

    public int getExcessQuantity(String commodityId) {
        CommodityOnMarketAPI commodityData = getCommodityData(commodityId);
        return commodityData.getExcessQuantity();
    }

    public int getDeficitQuantity(String commodityId) {
        CommodityOnMarketAPI commodityData = getCommodityData(commodityId);
        return commodityData.getDeficitQuantity();
    }

    public SectorEntityToken getPrimaryEntity() {
        return marketAPI.getPrimaryEntity();
    }

    public String getStarSystem() {
        StarSystemAPI starSystemAPI = marketAPI.getStarSystem();
        if (starSystemAPI == null) {
            // TODO : L18n this, also duplicate code
            return "Hyperspace";
        }
        return starSystemAPI.getBaseName();
    }

    public float getDistanceToPlayer() {
        return DistanceCalculator.getDistanceToPlayerLY(marketAPI.getPrimaryEntity());
    }

    public String getMarketAndFactionDisplayName() {
        return (marketAPI.getName() + " - " + marketAPI.getFaction().getDisplayName());
    }

    public String getName() {
        return marketAPI.getName();
    }

    public FactionAPI getFaction() {
        return marketAPI.getFaction();
    }
}
