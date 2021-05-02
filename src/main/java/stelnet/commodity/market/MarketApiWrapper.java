package stelnet.commodity.market;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.submarkets.OpenMarketPlugin;
import com.fs.starfarer.api.util.Misc;
import lombok.Builder;
import lombok.Data;
import stelnet.commodity.market.price.Price;

@Data
@Builder
public class MarketApiWrapper {
    Price price;
    MarketAPI marketAPI;

    public float getPriceAmount() {
        return price.getPriceAmount(marketAPI);
    }

    public CommodityOnMarketAPI getCommodityData(String commodityId) {
        return marketAPI.getCommodityData(commodityId);
    }

    public int getAvailable(String commodityId) {
        CommodityOnMarketAPI commodityData = getCommodityData(commodityId);
        int available = OpenMarketPlugin.getApproximateStockpileLimit(commodityData);
        available += commodityData.getPlayerTradeNetQuantity();
        return available;
    }

    public int getDemand(String commodityId) {
        CommodityOnMarketAPI commodityData = getCommodityData(commodityId);
        int demandIcons = commodityData.getMaxDemand();
        if (!commodityData.getCommodity().isPrimary()) {
            CommodityOnMarketAPI primary = marketAPI.getCommodityData(commodityData.getCommodity().getDemandClass());
            demandIcons = primary.getMaxDemand();
        }
        int demand = (int) (commodityData.getCommodity().getEconUnit() * demandIcons);
        demand -= commodityData.getPlayerTradeNetQuantity();
        return demand;
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
            return "Hyperspace";
        }
        return starSystemAPI.getBaseName();
    }

    public float getDistanceToPlayer() {
        return Misc.getDistanceToPlayerLY(marketAPI.getPrimaryEntity());
    }

    public String getFactionDisplayName() {
        return marketAPI.getName() + " - " + marketAPI.getFaction().getDisplayName();
    }
}
