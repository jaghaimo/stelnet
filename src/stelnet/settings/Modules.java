package stelnet.settings;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Modules {
    COMMODITIES(BooleanSettings.COMMODITIES, false),
    CONTACTS(BooleanSettings.CONTACTS, false),
    EXPLORATION(BooleanSettings.EXPLORATION, true),
    MARKET(BooleanSettings.MARKET, false),
    STORAGE(BooleanSettings.STORAGE, true);

    private final BooleanSettings setting;
    private final boolean alwaysVisible;

    public boolean has() {
        if (hasOverride()) {
            return false;
        }
        return setting.get();
    }

    public boolean isHidden() {
        if (alwaysVisible) {
            return false;
        }
        if (!BooleanSettings.REQUIRE_CIR.get()) {
            return false;
        }
        return !Global.getSector().getIntelManager().isPlayerInRangeOfCommRelay();
    }

    private boolean hasOverride() {
        boolean isTutorial = TutorialMissionIntel.isTutorialInProgress();
        boolean isUninstall = BooleanSettings.UNINSTALL.get();
        return isTutorial || isUninstall;
    }
}
