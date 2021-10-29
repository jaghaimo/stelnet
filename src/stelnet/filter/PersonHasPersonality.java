package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonHasPersonality extends PersonFilter {

    private final String personality;

    @Override
    public boolean accept(PersonAPI person) {
        String p = person.getPersonalityAPI().getId();
        return personality.equals(p);
    }
}
