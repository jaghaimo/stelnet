package stelnet.helper;

import com.fs.starfarer.api.Global;

public class GlobalSettingsHelper {

    public static int getEconomyIterPerMonth() {
        return Global.getSettings().getInt("economyIterPerMonth");
    }

    public static String getSpriteName(String sprite) {
        return Global.getSettings().getSpriteName("stelnet", sprite);
    }

    public static boolean isDevMode() {
        return Global.getSettings().isDevMode();
    }
}
