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
import stelnet.filter.IntelHasPerson;
import stelnet.filter.IntelIsActive;
import stelnet.settings.BooleanSettings;
import stelnet.settings.Modules;
import stelnet.util.CollectionUtils;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;
import stelnet.util.MemoryHelper;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import stelnet.widget.heading.HeadingWithButtons;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class DisplayContact extends HeadingWithButtons {

    private final ContactIntel intel;
    private final MarketAPI market;
    private final PersonAPI person;
    private static final float imageHeight = 48;

    public DisplayContact(ContactIntel intel, float width) {
        this(intel, intel.getMapLocation(null).getMarket(), intel.getPerson());
        setSize(new Size(width - 5, imageHeight + 3 * UiConstants.DEFAULT_SPACER));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        renderHeaderAndBody(tooltip);
        renderButtons(tooltip);
    }

    private void renderHeaderAndBody(TooltipMakerAPI tooltip) {
        TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 40);
        inner.addSectionHeading(
            " " + person.getNameString() + " - " + person.getImportance().getDisplayName(),
            person.getFaction().getBaseUIColor(),
            person.getFaction().getDarkUIColor(),
            Alignment.LMID,
            0
        );
        inner.setForceProcessInput(true);
        inner.addSpacer(6);
        LabelAPI missionParagraph = inner.addPara(getMissionTypeText(), 0);
        missionParagraph.setHighlight(getTagStrings(person));
        missionParagraph.setHighlightColor(person.getFaction().getBaseUIColor());
        inner.addSpacer(2);
        inner.addPara(getLocationText(), 0);
        tooltip.addImageWithText(0);
    }

    private void renderButtons(TooltipMakerAPI tooltip) {
        boolean isEnabled = isCallEnabled();
        String label1 = L10n.get(CommonL10n.CALL);
        String label2 = L10n.get(CommonL10n.SHOW);
        Size buttonSize = getButtonSize(tooltip, label1, label2);
        tooltip.setButtonFontVictor14();
        UIComponentAPI call = renderFirstButton(
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

    private Size getButtonSize(TooltipMakerAPI tooltip, String label1, String label2) {
        float width = 20 + Math.max(tooltip.computeStringWidth(label1), tooltip.computeStringWidth(label2));
        return new Size(width, UiConstants.DEFAULT_ROW_HEIGHT);
    }

    private String getMissionTypeText() {
        return L10n.get(
            ContactsL10n.DISPLAY_MISSION_TEXT,
            Misc.ucFirst(person.getFaction().getDisplayName()),
            Misc.getAndJoined(getTagStrings(person))
        );
    }

    private String[] getTagStrings(PersonAPI person) {
        String[] tagStrings = person.getSortedContactTagStrings().toArray(new String[] {});
        for (int i = 0; i < tagStrings.length; i++) {
            tagStrings[i] = tagStrings[i].toLowerCase();
        }
        return tagStrings;
    }

    private String getLocationText() {
        return L10n.get(
            ContactsL10n.DISPLAY_LOCATION_TEXT,
            Misc.ucFirst(person.getHeOrShe()),
            market.getName(),
            StelnetHelper.getStarSystemName(market.getStarSystem(), true)
        );
    }

    private boolean isCallEnabled() {
        boolean wouldBeHidden = Modules.CONTACTS.isHidden();
        boolean hasMissions = hasActiveMission(person) && BooleanSettings.CONTACTS_MISSIONLESS.get();
        boolean hasSubmarket = market.hasSubmarket(Submarkets.SUBMARKET_STORAGE);
        boolean isCalling = MemoryHelper.getBoolean(ModConstants.MEMORY_IS_CALLING);
        return !wouldBeHidden && !hasMissions && hasSubmarket && !isCalling;
    }

    private boolean hasActiveMission(PersonAPI person) {
        List<IntelInfoPlugin> missions = Global.getSector().getIntelManager().getIntel(HubMission.class);
        CollectionUtils.reduce(missions, new IntelHasPerson(person));
        CollectionUtils.reduce(missions, new IntelIsActive());
        return !missions.isEmpty();
    }
}
