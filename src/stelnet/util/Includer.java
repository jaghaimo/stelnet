package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides access to user/mod inclusions.
 */
public class Includer extends Reader {

    private static final String ABANDONED_STATION_IDS = "data/stelnet/include/abandoned_stations.csv";
    private static final String ADDITIONAL_FACTION_IDS = "data/stelnet/include/additional_factions.csv";

    public static List<String> getAdditionalFactions() {
        return getStrings(ADDITIONAL_FACTION_IDS, ModConstants.STELNET_ID);
    }

    public static Set<SubmarketAPI> getAbandonedStations() {
        Set<SubmarketAPI> submarkets = new LinkedHashSet<>();
        for (String station : getAbandonedStationIds()) {
            SubmarketAPI submarket = getDiscoveredStorage(station);
            if (submarket != null) {
                submarkets.add(submarket);
            }
        }
        return submarkets;
    }

    private static List<String> getAbandonedStationIds() {
        return getStrings(ABANDONED_STATION_IDS, ModConstants.STELNET_ID);
    }

    private static SubmarketAPI getDiscoveredStorage(String stationId) {
        SectorEntityToken token = Global.getSector().getEntityById(stationId);
        if (token == null) {
            return null;
        }
        if (token.isDiscoverable()) {
            return null;
        }
        MarketAPI market = token.getMarket();
        if (market == null) {
            return null;
        }
        return market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
    }
}
