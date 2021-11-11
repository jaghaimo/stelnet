package uilib;

import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Strings;
import com.fs.starfarer.api.ui.LabelAPI;
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
        String numberString = " 1" + Strings.X;
        float width = getSize().getWidth() - 80;
        for (PersonAPI person : people) {
            TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 16);
            String nameString = getName(person);
            String shortenedString = inner.shortenString(nameString + "  " + person.getPost(), width);
            LabelAPI label = inner.addPara(numberString + " " + shortenedString, Misc.getGrayColor(), 0);
            label.setHighlightColors(Misc.getHighlightColor(), Misc.getTextColor());
            label.setHighlight(numberString, nameString);
            tooltip.addImageWithText(7);
        }
    }

    private String getName(PersonAPI person) {
        FullName fullName = person.getName();
        return String.format("%c.%s", fullName.getFirst().charAt(0), fullName.getLast());
    }
}
