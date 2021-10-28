package stelnet.filter.person;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
public class IsPostedAs implements PersonFilter {

    private final String post;

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        String postId = person.getPostId();
        log.debug(String.format("Considering %s (%s)", person.getNameString(), postId));
        return postId.equals(post);
    }

    public static IsPostedAs admin() {
        return new IsPostedAs(Ranks.POST_FREELANCE_ADMIN);
    }

    public static IsPostedAs agent() {
        return new IsPostedAs(Ranks.AGENT);
    }

    public static IsPostedAs mercenary() {
        return new IsPostedAs(Ranks.POST_MERCENARY);
    }

    public static IsPostedAs officer() {
        return new IsPostedAs(Ranks.POST_OFFICER_FOR_HIRE);
    }
}
