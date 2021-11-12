package uilib;

import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.PersonalityAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Strings;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import uilib.property.Size;

/**
 * Show list of people. Mimics showCargo() and showShips() look and feel.
 */
public class ShowPeople extends RenderableComponent implements Comparator<PersonAPI> {

    private final List<PersonAPI> people;
    private final String emptyDescription;

    public ShowPeople(List<PersonAPI> people, String emptyDescription, Size size) {
        Collections.sort(people, this);
        this.people = people;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (people.isEmpty()) {
            tooltip.addPara(emptyDescription, 0);
        }
        String lastPost = null;
        for (PersonAPI person : people) {
            addPostIfNeeded(tooltip, lastPost, person.getPost());
            lastPost = person.getPost();
            addPerson(tooltip, person);
        }
    }

    private void addPostIfNeeded(TooltipMakerAPI tooltip, String previousPost, String currentPost) {
        if (currentPost.equals(previousPost)) {
            return;
        }
        if (previousPost != null) {
            tooltip.addSpacer(10);
        }
        addSectionTitle(tooltip, currentPost);
    }

    private void addPerson(TooltipMakerAPI tooltip, PersonAPI person) {
        String numberString = " 1" + Strings.X;
        TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 16);
        String nameString = getName(person);
        String personalityString = getPersonality(person);
        LabelAPI label = inner.addPara(numberString + " " + nameString + personalityString, Misc.getGrayColor(), 0);
        label.setHighlightColors(Misc.getHighlightColor(), Misc.getTextColor());
        label.setHighlight(numberString, nameString);
        tooltip.addImageWithText(7);
    }

    private String getName(PersonAPI person) {
        FullName fullName = person.getName();
        return String.format("%c.%s", fullName.getFirst().charAt(0), fullName.getLast());
    }

    private String getPersonality(PersonAPI person) {
        String postId = person.getPostId();
        if (!postId.equals(Ranks.POST_OFFICER_FOR_HIRE) && !postId.equals(Ranks.POST_MERCENARY)) {
            return "";
        }
        return String.format(" (%s)", person.getPersonalityAPI().getDisplayName());
    }

    @Override
    public int compare(PersonAPI o1, PersonAPI o2) {
        int byPostId = o1.getPostId().compareTo(o2.getPostId());
        if (byPostId != 0) {
            return byPostId;
        }
        int byPersonality = comparePersonalities(o1.getPersonalityAPI(), o2.getPersonalityAPI());
        if (byPersonality != 0) {
            return byPersonality;
        }
        return o1.getNameString().compareTo(o2.getNameString());
    }

    private int comparePersonalities(PersonalityAPI p1, PersonalityAPI p2) {
        if (p1 == p2 || p1 == null || p2 == null) {
            return 0;
        }
        return personalityToOrdinal(p2) - personalityToOrdinal(p1);
    }

    private int personalityToOrdinal(PersonalityAPI personality) {
        int i = 0;
        switch (personality.getId()) {
            case Personalities.TIMID:
                i++;
            case Personalities.CAUTIOUS:
                i++;
            case Personalities.STEADY:
                i++;
            case Personalities.AGGRESSIVE:
                i++;
            case Personalities.RECKLESS:
                i++;
        }
        return i;
    }
}
