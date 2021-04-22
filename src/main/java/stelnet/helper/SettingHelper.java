package stelnet.helper;

import com.fs.starfarer.api.Global;

public class SettingHelper {

    public static String getSpriteName(String sprite) {
        return Global.getSettings().getSpriteName("stelnet", sprite);
    }

    public static boolean isDevMode() {
        boolean isDevMode = Global.getSettings().isDevMode();
        boolean isStelnetDevMode = Global.getSettings().getBoolean("stelnetDevMode");
        return isDevMode || isStelnetDevMode;
    }

    public static boolean uninstallMod() {
        return Global.getSettings().getBoolean("stelnetUninstallMod");
    }

    public static boolean warnAboutEndOfMonth() {
        return Global.getSettings().getBoolean("stelnetWarnAboutEndOfMonth");
    }
}
