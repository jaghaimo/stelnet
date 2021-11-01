package uilib;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import uilib.property.Size;

public class People extends AbstractRenderable {

    private final List<PersonAPI> people;
    private final String emptyDescription;

    public People(List<PersonAPI> people, String emptyDesription, Size size) {
        this.people = people;
        this.emptyDescription = emptyDesription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (people.isEmpty()) {
            tooltip.addPara(emptyDescription, 0);
        }
        for (PersonAPI person : people) {
            tooltip.addPara(person.getNameString(), 0);
        }
    }
}
