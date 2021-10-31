package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.HullMods;

public class FleetMemberIsNotCivilian extends FleetMemberFilter {

    @Override
    protected boolean acceptFleetMember(FleetMemberAPI object) {
        return !object.getVariant().hasHullMod(HullMods.CIVGRADE);
    }
}
