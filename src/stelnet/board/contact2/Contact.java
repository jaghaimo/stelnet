package stelnet.board.contact2;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;

@Getter
@RequiredArgsConstructor
public class Contact implements Drawable {

    private final ContactIntel intel;
    private final ContactIntel.ContactState state;
    private final PersonAPI person;
    private final MarketAPI market;
    private final boolean hasActiveMissions;

    @Getter
    private final Set<ContactActions> actions = new HashSet<>();

    public Contact(final ContactIntel intel, final boolean hasActiveMissions) {
        this(intel, intel.getState(), intel.getPerson(), intel.getMapLocation(null).getMarket(), hasActiveMissions);
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

    public Color getBaseColor() {
        return person.getFaction().getBaseUIColor();
    }

    public Color getDarkColor() {
        return person.getFaction().getDarkUIColor();
    }

    public String getName() {
        return String.format("%s [%s]", person.getNameString(), person.getImportance().getDisplayName());
    }

    public String getMissionTypeText() {
        return L10n.contacts(
            "DISPLAY_MISSION_TEXT",
            Misc.ucFirst(person.getFaction().getDisplayName()),
            Misc.getAndJoined(getTagStrings(person))
        );
    }

    public String getLocationText() {
        return L10n.contacts(
            "DISPLAY_LOCATION_TEXT",
            Misc.ucFirst(person.getHeOrShe()),
            market.getName(),
            StelnetHelper.getStarSystemName(market.getStarSystem(), true)
        );
    }

    private String[] getTagStrings(final PersonAPI person) {
        final String[] tagStrings = person.getSortedContactTagStrings().toArray(new String[] {});
        for (int i = 0; i < tagStrings.length; i++) {
            tagStrings[i] = tagStrings[i].toLowerCase();
        }
        return tagStrings;
    }

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        return new DrawableContact(this).draw(tooltip);
    }
}
