package stelnet.commodity.data;

import java.awt.Color;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.submarkets.OpenMarketPlugin;
import com.fs.starfarer.api.util.Misc;

public class TableCellHelper {

    public static int getAvailable(CommodityOnMarketAPI commodity) {
        int available = OpenMarketPlugin.getApproximateStockpileLimit(commodity);
        available += commodity.getPlayerTradeNetQuantity();
        return available;
    }

    public static Color getClaimingFactionColor(MarketAPI market) {
        FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
        if (faction == null) {
            return Misc.getGrayColor();
        }
        return faction.getColor();
    }

    public static int getDemand(MarketAPI market, CommodityOnMarketAPI commodity) {
        int demandIcons = commodity.getMaxDemand();
        if (!commodity.getCommodity().isPrimary()) {
            CommodityOnMarketAPI primary = market.getCommodityData(commodity.getCommodity().getDemandClass());
            demandIcons = primary.getMaxDemand();
        }
        int demand = (int) (commodity.getCommodity().getEconUnit() * demandIcons);
        demand -= commodity.getPlayerTradeNetQuantity();
        return demand;
    }

    public static String getLocation(MarketAPI market) {
        return market.getName() + " - " + market.getFaction().getDisplayName();
    }
}
