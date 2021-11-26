package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.util.L10n;
import stelnet.util.StringUtils;
import stelnet.widget.HeadingWithButtons;
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
        setSize(new Size(width, imageHeight + 2 * UiConstants.DEFAULT_SPACER));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), imageHeight);
        // renderHeading(inner, true, " " + person.getNameString(), person.getFaction());
        addSectionTitle(
            inner,
            person.getNameString() + " - " + person.getImportance().getDisplayName(),
            person.getFaction().getBaseUIColor(),
            getSize().getWidth() - imageHeight - UiConstants.DEFAULT_SPACER * 2
        );
        inner.addSpacer(8);
        inner.addPara(getPersonBlurb(), 0);
        tooltip.addImageWithText(0);

        String label1 = L10n.get(CommonL10n.CALL);
        String label2 = L10n.get(CommonL10n.SHOW);
        Size buttonSize = getButtonSize(tooltip, label1, label2);
        tooltip.setButtonFontVictor14();
        UIComponentAPI call = renderFirst(
            new CallContact(label1, buttonSize, market, person),
            getSize().getWidth() - 0,
            tooltip
        );
        call.getPosition().setYAlignOffset(UiConstants.DEFAULT_ROW_HEIGHT + imageHeight - buttonSize.getHeight());
        renderNext(
            new ShowContactButton(label2, buttonSize, intel, person.getFaction()),
            tooltip,
            call,
            UiConstants.DEFAULT_SPACER / 2
        );
    }

    private Size getButtonSize(TooltipMakerAPI tooltip, String label1, String label2) {
        float width = 50 + Math.max(getTextWidth(label1), getTextWidth(label2));
        float height = UiConstants.VICTOR_14_BUTTON_HEIGHT;
        return new Size(width, height);
    }

    private String getPersonBlurb() {
        return (
            Misc.ucFirst(person.getFaction().getDisplayName()) +
            " contact can offer " +
            Misc.getAndJoined(person.getSortedContactTagStrings()).toLowerCase() +
            " missions.\n" +
            Misc.ucFirst(person.getHeOrShe()) +
            " is stationed on " +
            market.getName() +
            " in " +
            StringUtils.getStarSystem(market, false) +
            "."
        );
    }
}
