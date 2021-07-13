package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.HullMods;

public class IsNotCivilian implements FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.getVariant().hasHullMod(HullMods.CIVGRADE);
    }
}
