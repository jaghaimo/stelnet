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
            return acceptCommDirectoryEntry((CommDirectoryEntryAPI) object);
        }
        if (object instanceof CommoditySpecAPI) {
            return acceptCommodity((CommoditySpecAPI) object);
        }
        if (object instanceof PersonAPI) {
            return acceptPerson((PersonAPI) object);
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

    protected boolean acceptPerson(PersonAPI person) {
        return person.getTags().contains(tag);
    }
}
