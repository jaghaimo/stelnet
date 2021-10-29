package stelnet.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnyHasTag extends Filter {

    private final String tag;

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return accept(person);
    }

    @Override
    public boolean accept(CommoditySpecAPI commodity) {
        return commodity.hasTag(tag);
    }

    @Override
    public boolean accept(PersonAPI person) {
        return person.getTags().contains(tag);
    }
}
