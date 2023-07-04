package uilib;

import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.PersonalityAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipLocation;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.Setter;
import uilib.property.Size;

/**
 * Show list of people. Mimics showCargo() and showShips() look and feel.
 */
@Setter
public class ShowPeople extends RenderableShowComponent implements Comparator<PersonAPI> {

    public static String PERSONNEL = "Personnel";
    public static String AND_X_OTHER_PEOPLE = "... and %s other people.";
    private final List<PersonAPI> people;
    private final String emptyDescription;
    private Color groupColor = Misc.getTextColor();

    public ShowPeople(final List<PersonAPI> people, final String emptyDescription, final Size size) {
        super(people.size());
        Collections.sort(people, this);
        this.people = people;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        if (people.isEmpty()) {
            addPostIfNeeded(tooltip, null, PERSONNEL);
            tooltip.addPara(emptyDescription, UiConstants.DEFAULT_SPACER);
        }
        String lastPost = null;
        int i = 0;
        final int numberOfPeople = people.size();
        for (final PersonAPI person : people) {
            i++;
            addPostIfNeeded(tooltip, lastPost, person.getPost());
            lastPost = person.getPost();
            addPerson(tooltip, person);
            if (i >= getMaxElements() && numberOfPeople != getMaxElements()) {
                tooltip.addPara(AND_X_OTHER_PEOPLE, 4, Misc.getHighlightColor(), String.valueOf(numberOfPeople - i));
                return;
            }
        }
    }

    private void addPostIfNeeded(final TooltipMakerAPI tooltip, final String previousPost, final String currentPost) {
        if (currentPost.equals(previousPost)) {
            return;
        }
        if (previousPost != null) {
            tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        }
        addSectionTitle(tooltip, currentPost, groupColor, getSize().getWidth() - UiConstants.DEFAULT_SPACER);
    }

    private void addPerson(final TooltipMakerAPI tooltip, final PersonAPI person) {
        final String numberString = " L" + person.getStats().getLevel();
        final TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 16);
        final String nameString = getName(person);
        final String personalityString = getPersonality(person);
        final LabelAPI label = inner.addPara(
            numberString + "  " + nameString + personalityString,
            Misc.getGrayColor(),
            0
        );
        label.setHighlightColors(Misc.getHighlightColor(), Misc.getTextColor());
        label.setHighlight(numberString, nameString);
        tooltip.addImageWithText(7);
        tooltip.addTooltipToPrevious(new ShowPeopleTooltip(person), TooltipLocation.LEFT);
    }

    private String getName(final PersonAPI person) {
        final FullName fullName = person.getName();
        return String.format("%c.%s", fullName.getFirst().charAt(0), fullName.getLast());
    }

    private String getPersonality(final PersonAPI person) {
        if (!isAnyOfficer(person)) {
            return "";
        }
        return String.format(" (%s)", person.getPersonalityAPI().getDisplayName());
    }

    @Override
    public int compare(final PersonAPI o1, final PersonAPI o2) {
        final int byPostId = o1.getPostId().compareTo(o2.getPostId());
        if (byPostId != 0) {
            return byPostId;
        }
        final int byPersonality = comparePersonalities(o1.getPersonalityAPI(), o2.getPersonalityAPI());
        if (byPersonality != 0) {
            return byPersonality;
        }
        return o1.getNameString().compareTo(o2.getNameString());
    }

    private int comparePersonalities(final PersonalityAPI p1, final PersonalityAPI p2) {
        if (p1 == p2 || p1 == null || p2 == null) {
            return 0;
        }
        return personalityToOrdinal(p2) - personalityToOrdinal(p1);
    }

    private int personalityToOrdinal(final PersonalityAPI personality) {
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

    private boolean isAnyOfficer(final PersonAPI person) {
        final String postId = person.getPostId();
        return postId.equals(Ranks.POST_OFFICER_FOR_HIRE) || postId.equals(Ranks.POST_MERCENARY);
    }
}
