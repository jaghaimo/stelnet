package stelnet.filter.person;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;

public class HasPersonality implements PersonFilter {

    private String personality;

    public HasPersonality(String p) {
        personality = p;
    }

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return personality.equals(person.getPersonalityAPI().getId());
    }
}
