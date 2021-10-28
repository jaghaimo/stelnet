package stelnet.filter2;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;

public abstract class Filter {

    public boolean accept(CommDirectoryEntryAPI object) {
        return false;
    }

    public boolean accept(PersonAPI object) {
        return false;
    }

    public boolean accept(SkillSpecAPI object) {
        return false;
    }

    public boolean accept(Object object) {
        if (object instanceof CommDirectoryEntryAPI) {
            return accept((CommDirectoryEntryAPI) object);
        }
        if (object instanceof PersonAPI) {
            return accept((PersonAPI) object);
        }
        if (object instanceof SkillSpecAPI) {
            return accept((SkillSpecAPI) object);
        }
        return false;
    }
}
