package stelnet.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnyHasTag extends Filter {

    private final String tag;

    @Override
    public boolean accept(Object object) {
        if (object instanceof CommDirectoryEntryAPI) {
            return acceptCommDirectoryEntry(object);
        }
        if (object instanceof CommoditySpecAPI) {
            return acceptCommodity(object);
        }
        if (object instanceof PersonAPI) {
            return acceptPerson(object);
        }
        return super.accept(object);
    }

    protected boolean acceptCommDirectoryEntry(Object object) {
        CommDirectoryEntryAPI entry = (CommDirectoryEntryAPI) object;
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return acceptPerson(person);
    }

    protected boolean acceptCommodity(Object object) {
        CommoditySpecAPI commodity = (CommoditySpecAPI) object;
        return commodity.hasTag(tag);
    }

    protected boolean acceptPerson(Object object) {
        PersonAPI person = (PersonAPI) object;
        return person.getTags().contains(tag);
    }
}
