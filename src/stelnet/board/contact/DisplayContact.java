package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;
import stelnet.widget.HeadingWithButtons;
import uilib.property.Size;

@RequiredArgsConstructor
public class DisplayContact extends HeadingWithButtons {

    private final ContactIntel intel;
    private final MarketAPI market;
    private final PersonAPI person;

    public DisplayContact(ContactIntel intel, float width) {
        this(intel, intel.getMapLocation(null).getMarket(), intel.getPerson());
        setSize(new Size(width, 50));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        renderHeading(tooltip, true, " " + person.getNameString(), person.getFaction());
        // TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 48);
        // inner.addSectionHeading(" " + person.getNameString(), Alignment.LMID, 1);
        // inner.addPara("Name: " + person.getNameString(), 2);
        // inner.addPara("Location: " + market.getName(), 2);
        // tooltip.addImageWithText(0);
        // UIComponentAPI info = tooltip.getPrev();
        tooltip.setButtonFontVictor10();
        UIComponentAPI call = renderFirst(new CallContact(market, person), getSize().getWidth(), tooltip);
        renderNext(new ShowContactButton(intel, person.getFaction()), tooltip, call);
        // tooltip.addButton("Call", new CallContact(market, person), 100, 20, 0);
        // tooltip.getPrev().getPosition().rightOfTop(info, -100);
        // tooltip.addSpacer(2);
        // tooltip.addButton("Show", new ShowContact(intel), 100, 20, 0);
        // tooltip.addSpacer(0);
        // tooltip.getPrev().getPosition().belowLeft(info, 0);
    }
}
