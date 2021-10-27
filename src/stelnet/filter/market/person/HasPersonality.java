package stelnet.filter.market.person;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import lombok.extern.log4j.Log4j;

@Log4j
public class HasPersonality implements PersonFilter {

    private final String personality;

    public HasPersonality(String p) {
        personality = p;
    }

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        String p = person.getPersonalityAPI().getId();
        log.debug(String.format("Considering %s (%s)", person.getNameString(), p));
        return personality.equals(p);
    }
}
