package stelnet.helper;

import com.fs.starfarer.api.campaign.StarSystemAPI;

public class StarSystemHelper {

    public static String getName(StarSystemAPI starSystem) {
        if (starSystem == null) {
            return "Hyperspace";
        }
        return starSystem.getBaseName();
    }
}
