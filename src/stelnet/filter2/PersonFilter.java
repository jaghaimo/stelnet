package stelnet.filter2;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;

public abstract class PersonFilter extends Filter {

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return accept(person);
    }
}
