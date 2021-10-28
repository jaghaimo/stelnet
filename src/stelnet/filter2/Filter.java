package stelnet.filter2;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public abstract class Filter {

    public boolean accept(Object object) {
        if (object instanceof CommDirectoryEntryAPI) {
            return accept((CommDirectoryEntryAPI) object);
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
        if (object instanceof WeaponSpecAPI) {
            return accept((WeaponSpecAPI) object);
        }
        return false;
    }

    public boolean accept(CommDirectoryEntryAPI object) {
        return false;
    }

    public boolean accept(PersonAPI object) {
        return false;
    }

    public boolean accept(ShipHullSpecAPI object) {
        return false;
    }

    public boolean accept(SkillSpecAPI object) {
        return false;
    }

    public boolean accept(WeaponSpecAPI object) {
        return false;
    }
}
