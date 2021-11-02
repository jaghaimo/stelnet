package stelnet.util;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class StringUtils {

    public static String getStarSystem(MarketAPI market) {
        return getStarSystem(market.getStarSystem());
    }

    public static String getStarSystem(SectorEntityToken entity) {
        return getStarSystem(entity.getStarSystem());
    }

    public static String getStarSystem(StarSystemAPI starSystem) {
        if (starSystem == null) {
            return L10n.get("hyperspace");
        }
        return starSystem.getBaseName();
    }
}
