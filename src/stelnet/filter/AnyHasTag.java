package stelnet.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@EqualsAndHashCode(callSuper = false)
@Log4j
@RequiredArgsConstructor
public final class AnyHasTag extends Filter {

    private final String tag;

    @Override
    public boolean accept(Object object) {
        if (object instanceof CommDirectoryEntryAPI) {
            return acceptCommDirectoryEntry((CommDirectoryEntryAPI) object);
        }
        if (object instanceof CommoditySpecAPI) {
            return acceptCommodity((CommoditySpecAPI) object);
        }
        if (object instanceof ContactIntel) {
            return acceptContact((ContactIntel) object);
        }
        if (object instanceof FighterWingSpecAPI) {
            return acceptFighterWing((FighterWingSpecAPI) object);
        }
        if (object instanceof HullModSpecAPI) {
            return acceptHullMod((HullModSpecAPI) object);
        }
        if (object instanceof IntelInfoPlugin) {
            return acceptIntel((IntelInfoPlugin) object);
        }
        if (object instanceof MarketAPI) {
            return acceptMarket((MarketAPI) object);
        }
        if (object instanceof PersonAPI) {
            return acceptPerson((PersonAPI) object);
        }
        if (object instanceof SkillSpecAPI) {
            return acceptSkill((SkillSpecAPI) object);
        }
        if (object instanceof WeaponSpecAPI) {
            return acceptWeapon((WeaponSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptCommDirectoryEntry(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return acceptPerson(person);
    }

    protected boolean acceptCommodity(CommoditySpecAPI commodity) {
        return commodity.hasTag(tag);
    }

    protected boolean acceptContact(ContactIntel intel) {
        return acceptPerson(intel.getPerson());
    }

    protected boolean acceptFighterWing(FighterWingSpecAPI fighterWing) {
        return fighterWing.getTags().contains(tag);
    }

    protected boolean acceptHullMod(HullModSpecAPI hullMod) {
        return hullMod.getTags().contains(tag);
    }

    protected boolean acceptIntel(IntelInfoPlugin intel) {
        log.info("Trying intel " + intel.toString());
        Set<String> tags = intel.getIntelTags(null);
        if (tags == null) {
            log.warn("Intel " + intel.toString() + " has null tags, returning false");
            return false;
        }
        return tags.contains(tag);
    }

    protected boolean acceptMarket(MarketAPI market) {
        if (market.hasTag(tag)) {
            return true;
        }
        StarSystemAPI starSystem = market.getStarSystem();
        if (starSystem != null) {
            return starSystem.hasTag(tag);
        }
        return false;
    }

    protected boolean acceptPerson(PersonAPI person) {
        if (person == null) {
            log.warn("Person is null, returning false");
            return false;
        }
        Set<String> tags = person.getTags();
        if (tags == null) {
            log.warn("Person " + person.toString() + " has null tags, returning false");
            return false;
        }
        return tags.contains(tag);
    }

    protected boolean acceptSkill(SkillSpecAPI skill) {
        return skill.getTags().contains(tag);
    }

    protected boolean acceptWeapon(WeaponSpecAPI weapon) {
        return weapon.getTags().contains(tag);
    }
}
