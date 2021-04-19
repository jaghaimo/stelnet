package stelnet.helper;

import com.fs.starfarer.api.Global;

public class SettingHelper {

    public static boolean isDevMode() {
        return Global.getSettings().isDevMode();
    }

    public static String getSpriteName(String sprite) {
        return Global.getSettings().getSpriteName("stelnet", sprite);
    }

    public static boolean isUninstall() {
        return Global.getSettings().getBoolean("stelnetUninstall");
    }
}
