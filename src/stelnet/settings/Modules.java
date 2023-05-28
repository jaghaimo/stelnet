package stelnet.settings;

import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;

public class Modules {

    public static boolean hasCommodities() {
        if (hasOverride()) {
            return false;
        }
        return BooleanSettings.COMMODITIES.get();
    }

    public static boolean hasContacts() {
        if (hasOverride()) {
            return false;
        }
        return BooleanSettings.CONTACTS.get();
    }

    public static boolean hasExploration() {
        if (hasOverride()) {
            return false;
        }
        return BooleanSettings.EXPLORATION.get();
    }

    public static boolean hasMarket() {
        if (hasOverride()) {
            return false;
        }
        return BooleanSettings.MARKET.get();
    }

    public static boolean hasStorage() {
        if (hasOverride()) {
            return false;
        }
        return BooleanSettings.STORAGE.get();
    }

    private static boolean hasOverride() {
        boolean isTutorial = TutorialMissionIntel.isTutorialInProgress();
        boolean isUninstall = BooleanSettings.UNINSTALL.get();
        return isTutorial || isUninstall;
    }
}
