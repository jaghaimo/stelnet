package stelnet.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;

public abstract class PersonFilter extends Filter {

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return accept(person);
    }

    @Override
    public abstract boolean accept(PersonAPI person);
}