package uilib;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipLocation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib.property.Size;

public class People extends AbstractRenderable {

    @RequiredArgsConstructor
    public static class PersonTooltip implements TooltipCreator {

        private final PersonAPI person;

        @Override
        public boolean isTooltipExpandable(Object tooltipParam) {
            return false;
        }

        @Override
        public float getTooltipWidth(Object tooltipParam) {
            return 200;
        }

        @Override
        public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, Object tooltipParam) {
            tooltip.addImage(person.getPortraitSprite(), 128, 4);
            PositionAPI imagePosition = tooltip.getPrev().getPosition();
            imagePosition.inTMid(4);
            tooltip.addPara(person.getPost(), 4).getPosition().inTL(0, 128 + 8);
            tooltip.addPara("Level " + String.valueOf(person.getStats().getLevel()), 2);
        }
    }

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
