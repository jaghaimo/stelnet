package stelnet.board.contact2;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.Spacer;

@Getter
@Log4j
@RequiredArgsConstructor
public class Contact implements Comparable<Contact>, Drawable {

    private static final String SORT_IDX_KEY = "$stelnetSortIdx";

    private final ContactIntel intel;
    private final ContactIntel.ContactState state;
    private final PersonAPI person;
    private final MarketAPI market;
    private final boolean hasActiveMissions;

    @Getter
    private final Set<ContactActions> actions = new HashSet<>();

    private int sortIdx = 0;

    public Contact(final ContactIntel intel, final boolean hasActiveMissions) {
        this(intel, intel.getState(), intel.getPerson(), intel.getMapLocation(null).getMarket(), hasActiveMissions);
        sortIdx = fetchSortIdx();
    }

    public int fetchSortIdx() {
        if (!isValid()) {
            return 0;
        }
        return person.getMemoryWithoutUpdate().getInt(SORT_IDX_KEY);
    }

    public boolean isValid() {
        final boolean hasPerson = person != null;
        final boolean hasMarket = market != null;
        final boolean hasIntel = intel != null;
        final boolean hasState = state != null;
        return hasPerson && hasMarket && hasIntel && hasState;
    }

    public void changeSortIndex(final int delta) {
        if (!isValid()) {
            return;
        }
        // clamp 1-9
        final int newSortIdx = Math.min(Math.max(sortIdx + delta, 1), 9);
        sortIdx = newSortIdx;
        person.getMemoryWithoutUpdate().set(SORT_IDX_KEY, newSortIdx);
    }

    public boolean canCall() {
        if (hasActiveMissions) {
            return false;
        }
        if (market == null) {
            return false;
        }
        return market.hasSubmarket(Submarkets.SUBMARKET_STORAGE);
    }

    public String getTitle() {
        return String.format("%s [%s]", person.getNameString(), person.getImportance().getDisplayName());
    }

    public String getMissionTypeText() {
        return L10n.contacts(
            "DISPLAY_MISSION_TEXT",
            Misc.ucFirst(person.getFaction().getDisplayName()),
            Misc.getAndJoined(getTagStrings(person))
        );
    }

    private String[] getTagStrings(final PersonAPI person) {
        final String[] tagStrings = person.getSortedContactTagStrings().toArray(new String[] {});
        for (int i = 0; i < tagStrings.length; i++) {
            tagStrings[i] = tagStrings[i].toLowerCase();
        }
        return tagStrings;
    }

    public String getLocationText() {
        return L10n.contacts(
            "DISPLAY_LOCATION_TEXT",
            Misc.ucFirst(person.getHeOrShe()),
            market.getName(),
            StelnetHelper.getStarSystemName(market.getStarSystem(), true)
        );
    }

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        if (isValid()) {
            return new DrawableContact(this).draw(tooltip);
        }
        log.warn("Skipping drawing of invalid contact.");
        return new Spacer(0).draw(tooltip);
    }

    @Override
    public int compareTo(final Contact o) {
        if (!isValid() || !o.isValid()) {
            return 0;
        }
        int sortDelta = getSortIdx() - o.getSortIdx();
        if (sortDelta == 0) {
            sortDelta = getPerson().getNameString().compareTo(o.getPerson().getNameString());
        }
        return sortDelta;
    }
}
