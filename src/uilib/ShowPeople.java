package uilib;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Strings;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import uilib.property.Size;

/**
 * Show list of people. Mimics showCargo() and showShips() look and feel.
 */
public class ShowPeople extends RenderableComponent {

    private final List<PersonAPI> people;
    private final String emptyDescription;

    public ShowPeople(List<PersonAPI> people, String emptyDescription, Size size) {
        this.people = people;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (people.isEmpty()) {
            tooltip.addPara(emptyDescription, 0);
        }
        for (PersonAPI person : people) {
            TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 16);
            inner.addPara(
                " 1" + Strings.X + " %s",
                0,
                Misc.getHighlightColor(),
                Misc.getTextColor(),
                person.getNameString()
            );
            tooltip.addImageWithText(8);
        }
    }
}
