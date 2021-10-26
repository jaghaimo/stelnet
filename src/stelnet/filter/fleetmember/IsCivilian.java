package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.HullMods;

public class IsCivilian implements FleetMemberFilter {

    private final Boolean isCivilian;

    public IsCivilian(Boolean isCivilian) {
        this.isCivilian = isCivilian;
    }

    public boolean accept(FleetMemberAPI f) {
        if (isCivilian == null) {
            return true;
        }
        return isCivilian.equals(f.getVariant().hasHullMod(HullMods.CIVGRADE));
    }
}
