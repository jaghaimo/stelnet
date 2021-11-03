package uilib;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipLocation;
import java.util.List;
import uilib.property.Size;
import uilib.tooltip.PersonTooltip;

public class People extends RenderableComponent {

    private final List<PersonAPI> people;
    private final String emptyDescription;

    public People(List<PersonAPI> people, String emptyDescription, Size size) {
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
            TooltipMakerAPI inner = tooltip.beginImageWithText(person.getPortraitSprite(), 20);
            inner.addPara(person.getNameString(), 0);
            tooltip.addImageWithText(2);
            tooltip.addTooltipToPrevious(new PersonTooltip(person), TooltipLocation.LEFT);
        }
    }
}
