package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.HullMods;

import stelnet.market_old.dialog.DialogOption;

public class IsCivilian implements FleetMemberFilter {

    private final DialogOption option;

    public IsCivilian(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
        case SHIP_CIVILIAN_NO:
            return !f.getVariant().hasHullMod(HullMods.CIVGRADE);

        case SHIP_CIVILIAN_ONLY:
            return f.getVariant().hasHullMod(HullMods.CIVGRADE);

        default:
            return true;
        }
    }
}
