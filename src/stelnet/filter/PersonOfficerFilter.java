package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PersonOfficerFilter extends PersonFilter {

    private final Filter isOfficerOrMercenary = new LogicalOr(
        Arrays.<Filter>asList(
            new PersonIsPostedAs(Ranks.POST_MERCENARY),
            new PersonIsPostedAs(Ranks.POST_OFFICER_FOR_HIRE)
        ),
        ""
    );

    @Override
    public boolean acceptPerson(PersonAPI person) {
        if (!isOfficerOrMercenary.accept(person)) {
            return true;
        }
        return false;
    }
}
