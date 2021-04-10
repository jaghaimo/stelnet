package stelnet.filter.person;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;

import stelnet.helper.LogHelper;

public class HasPersonality implements PersonFilter {

    private String personality;

    public HasPersonality(String p) {
        personality = p;
    }

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        String p = person.getPersonalityAPI().getId();
        LogHelper.debug(String.format("Considering %s (%s)", person.getNameString(), p));
        return personality.equals(p);
    }
}
