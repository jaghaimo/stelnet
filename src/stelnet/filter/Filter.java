package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class Filter {

    public final boolean accept(Object object) {
        for (Class<?> supportedClass : supports()) {
            if (supportedClass.isInstance(object)) {
                return acceptImpl(object);
            }
        }
        return false;
    }

    protected <T> boolean acceptImpl(T object) {
        return false;
    }

    protected Class<?>[] supports() {
        return new Class<?>[] { Object.class };
    }

    // Deprecated
    public boolean accept(CargoStackAPI cargoStack) {
        return false;
    }

    public boolean accept(CommDirectoryEntryAPI entry) {
        return false;
    }

    public boolean accept(CommoditySpecAPI commodity) {
        return false;
    }

    public boolean accept(FactionAPI faction) {
        return false;
    }

    public boolean accept(FighterWingSpecAPI fighterWing) {
        return false;
    }

    public boolean accept(FleetMemberAPI fleetMember) {
        return false;
    }

    public boolean accept(HullModSpecAPI fleetMember) {
        return false;
    }

    public boolean accept(MarketAPI market) {
        return false;
    }

    public boolean accept(PersonAPI person) {
        return false;
    }

    public boolean accept(ShipHullSpecAPI shipHull) {
        return false;
    }

    public boolean accept(SkillSpecAPI skill) {
        return false;
    }

    public boolean accept(SubmarketAPI submarket) {
        return false;
    }

    public boolean accept(WeaponSpecAPI weapon) {
        return false;
    }
}
