package stelnet.util;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.CommonL10n;

public class StringUtils {

    public static String getStarSystem(MarketAPI market) {
        return getStarSystem(market.getStarSystem());
    }

    public static String getStarSystem(SectorEntityToken entity) {
        return getStarSystem(entity.getStarSystem());
    }

    public static String getStarSystem(StarSystemAPI starSystem) {
        if (starSystem == null) {
            return L10n.get(CommonL10n.HYPERSPACE);
        }
        return starSystem.getBaseName();
    }
}
