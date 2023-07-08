package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.impl.campaign.missions.hub.HubMission;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.filters.FilterHelper;
import stelnet.filters.intel.IntelHasPerson;
import stelnet.filters.intel.IntelIsActive;
import stelnet.settings.BooleanSettings;
import stelnet.settings.Modules;
import stelnet.util.L10n;
import stelnet.util.MemoryManager;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import stelnet.widget.heading.HeadingWithButtons;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class DisplayContact extends HeadingWithButtons {

    private static final float imageHeight = 48;
    private final ContactIntel intel;
    private final MarketAPI market;
    private final PersonAPI person;

    public DisplayContact(final ContactIntel intel, final float width) {
        this(intel, intel.getMapLocation(null).getMarket(), intel.getPerson());
        setSize(new Size(width - 5, imageHeight + 3 * UiConstants.DEFAULT_SPACER));
        setWithScroller(false);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        renderHeaderAndBody(tooltip);
        renderButtons(tooltip);
    }

    private void renderHeaderAndBody(final TooltipMakerAPI tooltip) {
        final TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 40);
        inner.addSectionHeading(
            " " + person.getNameString() + " - " + person.getImportance().getDisplayName(),
            person.getFaction().getBaseUIColor(),
            person.getFaction().getDarkUIColor(),
            Alignment.LMID,
            0
        );
        inner.setForceProcessInput(true);
        inner.addSpacer(6);
        final LabelAPI missionParagraph = inner.addPara(getMissionTypeText(), 0);
        missionParagraph.setHighlight(getTagStrings(person));
        missionParagraph.setHighlightColor(person.getFaction().getBaseUIColor());
        inner.addSpacer(2);
        inner.addPara(getLocationText(), 0);
        tooltip.addImageWithText(0);
    }

    private void renderButtons(final TooltipMakerAPI tooltip) {
        final boolean isEnabled = isCallEnabled();
        final String label1 = L10n.common("CALL");
        final String label2 = L10n.common("SHOW");
        final Size buttonSize = getButtonSize(tooltip, label1, label2);
        tooltip.setButtonFontVictor14();
        final UIComponentAPI call = renderFirstButton(
            new CallContact(label1, isEnabled, buttonSize, market, person),
            getSize().getWidth() - 5,
            tooltip
        );
        call.getPosition().setYAlignOffset(imageHeight + 8);
        renderNextButton(
            new ShowContactButton(label2, buttonSize, intel, person.getFaction()),
            tooltip,
            call,
            UiConstants.DEFAULT_SPACER / 2
        );
    }

    private Size getButtonSize(final TooltipMakerAPI tooltip, final String label1, final String label2) {
        final float width = 20 + Math.max(tooltip.computeStringWidth(label1), tooltip.computeStringWidth(label2));
        return new Size(width, UiConstants.DEFAULT_ROW_HEIGHT);
    }

    private String getMissionTypeText() {
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

    private String getLocationText() {
        return L10n.contacts(
            "DISPLAY_LOCATION_TEXT",
            Misc.ucFirst(person.getHeOrShe()),
            market.getName(),
            StelnetHelper.getStarSystemName(market.getStarSystem(), true)
        );
    }

    private boolean isCallEnabled() {
        final boolean wouldBeHidden = Modules.CONTACTS.isHidden();
        final boolean hasMissions = hasActiveMission(person) && BooleanSettings.CONTACTS_MISSIONLESS.get();
        final boolean hasSubmarket = market.hasSubmarket(Submarkets.SUBMARKET_STORAGE);
        final boolean isCalling = MemoryManager.getInstance().getBoolean(ModConstants.MEMORY_IS_CALLING);
        return !wouldBeHidden && !hasMissions && hasSubmarket && !isCalling;
    }

    private boolean hasActiveMission(final PersonAPI person) {
        final List<IntelInfoPlugin> missions = Global.getSector().getIntelManager().getIntel(HubMission.class);
        FilterHelper.reduce(missions, new IntelHasPerson(person));
        FilterHelper.reduce(missions, new IntelIsActive());
        return !missions.isEmpty();
    }
}
