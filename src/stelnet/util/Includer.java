package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides access to user/mod inclusions.
 */
public class Includer extends Reader {

    private static final String ABANDONED_STATION_IDS = "data/stelnet/include/abandoned_stations.csv";

    public static List<SubmarketAPI> getAbandonedStations() {
        List<SubmarketAPI> submarkets = new LinkedList<>();
        for (String station : getAbandonedStationIds()) {
            SubmarketAPI submarket = getStorage(station);
            if (submarket != null) {
                submarkets.add(submarket);
            }
        }
        return submarkets;
    }

    private static List<String> getAbandonedStationIds() {
        return getStrings(ABANDONED_STATION_IDS, ModConstants.STELNET);
    }

    private static SubmarketAPI getStorage(String station) {
        SectorEntityToken token = Global.getSector().getEntityById(station);
        if (token == null) {
            return null;
        }
        MarketAPI market = token.getMarket();
        if (market == null) {
            return null;
        }
        return market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
    }
}
