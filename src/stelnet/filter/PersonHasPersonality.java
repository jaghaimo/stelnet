package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.PersonalityAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonHasPersonality extends PersonFilter {

    private final String personalityId;

    @Override
    public boolean acceptPerson(PersonAPI person) {
        PersonalityAPI personality = person.getPersonalityAPI();
        String p = personality.getId();
        return personalityId.equals(p);
    }
}
