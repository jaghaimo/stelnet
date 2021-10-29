package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public abstract class Filter {

    public boolean accept(Object object) {
        if (object instanceof CargoStackAPI) {
            return accept((CargoStackAPI) object);
        }
        if (object instanceof CommDirectoryEntryAPI) {
            return accept((CommDirectoryEntryAPI) object);
        }
        if (object instanceof CommoditySpecAPI) {
            return accept((CommoditySpecAPI) object);
        }
        if (object instanceof FleetMemberAPI) {
            return accept((FleetMemberAPI) object);
        }
        if (object instanceof MarketAPI) {
            return accept((MarketAPI) object);
        }
        if (object instanceof PersonAPI) {
            return accept((PersonAPI) object);
        }
        if (object instanceof ShipHullSpecAPI) {
            return accept((ShipHullSpecAPI) object);
        }
        if (object instanceof SkillSpecAPI) {
            return accept((SkillSpecAPI) object);
        }
        if (object instanceof SubmarketAPI) {
            return accept((SubmarketAPI) object);
        }
        if (object instanceof WeaponSpecAPI) {
            return accept((WeaponSpecAPI) object);
        }
        return false;
    }

    public boolean accept(CargoStackAPI cargoStack) {
        return false;
    }

    public boolean accept(CommDirectoryEntryAPI entry) {
        return false;
    }

    public boolean accept(CommoditySpecAPI commodity) {
        return false;
    }

    public boolean accept(FleetMemberAPI fleetMember) {
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
