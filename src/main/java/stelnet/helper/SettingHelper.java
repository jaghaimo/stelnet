package stelnet.helper;

import com.fs.starfarer.api.Global;

public class SettingHelper {

    public static String getSpriteName(String sprite) {
        return Global.getSettings().getSpriteName("stelnet", sprite);
    }

    public static boolean isDevMode() {
        return Global.getSettings().isDevMode();
    }

    public static boolean isUninstall() {
        return Global.getSettings().getBoolean("stelnetUninstall");
    }

    public static boolean warnAboutEndOfMonth() {
        return Global.getSettings().getBoolean("stelnetWarnAboutEndOfMonth");
    }
}
