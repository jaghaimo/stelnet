package stelnet.util;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Iterator;
import stelnet.CommonL10n;

public class StringUtils {

    public static String getStarSystem(MarketAPI market) {
        return getStarSystem(market.getStarSystem());
    }

    public static String getStarSystem(MarketAPI market, boolean shortName) {
        return getStarSystem(market.getStarSystem(), shortName);
    }

    public static String getStarSystem(SectorEntityToken entity) {
        return getStarSystem(entity.getStarSystem());
    }

    public static String getStarSystem(StarSystemAPI starSystem) {
        return getStarSystem(starSystem, true);
    }

    public static String getStarSystem(StarSystemAPI starSystem, boolean shortName) {
        if (starSystem == null) {
            return L10n.get(CommonL10n.HYPERSPACE);
        }
        if (shortName) {
            return starSystem.getBaseName();
        }
        return starSystem.getName();
    }

    public static String join(Iterable<?> iterable, String delimiter, String empty) {
        int delimiterLength = delimiter.length();
        StringBuilder builder = new StringBuilder();
        Iterator<?> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            String nextString = iterator.next().toString().trim();
            if (nextString.isEmpty()) {
                continue;
            }
            builder.append(delimiter);
            builder.append(nextString);
        }
        if (builder.length() > delimiterLength) {
            return builder.substring(delimiterLength);
        }
        return empty;
    }
}
