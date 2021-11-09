package uilib;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import uilib.property.Size;

/**
 * Unified list display. Can display CargoStackAPI, FleetMemberAPI, or PersonAPI objects.
 */
@RequiredArgsConstructor
public class ShowAny extends RenderableComponent {

    private final List<ListElement> elements;
    private final String emptyDescription;

    @Getter
    @RequiredArgsConstructor
    private static class ListElement {

        private final String sprite;
        private final String firstLine;
        private final String secondLine;
    }

    public ShowAny(String emptyDescription, Size size) {
        this.elements = new LinkedList<>();
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (elements.isEmpty()) {
            tooltip.addPara(emptyDescription, 0);
        }
        for (ListElement element : elements) {
            TooltipMakerAPI inner = tooltip.beginImageWithText(element.getSprite(), 32);
            inner.addPara(element.getFirstLine(), 2);
            inner.addPara(element.getSecondLine(), Misc.getGrayColor(), 2);
            tooltip.addImageWithText(4);
        }
    }

    public static ShowAny fromCargoStacks(List<CargoStackAPI> cargoStacks, String emptyDescription, Size size) {
        ShowAny showAny = new ShowAny(emptyDescription, size);
        showAny.addCargoStacks(cargoStacks);
        return showAny;
    }

    public static ShowAny fromPeople(List<PersonAPI> people, String emptyDescription, Size size) {
        ShowAny showAny = new ShowAny(emptyDescription, size);
        showAny.addPeople(people);
        return showAny;
    }

    public static ShowAny fromShips(List<FleetMemberAPI> ships, String emptyDescription, Size size) {
        ShowAny showAny = new ShowAny(emptyDescription, size);
        showAny.addShips(ships);
        return showAny;
    }

    private void addCargoStacks(List<CargoStackAPI> cargoStacks) {
        for (CargoStackAPI cargoStack : cargoStacks) {
            elements.add(
                new ListElement(
                    getSpriteFromCargoStack(cargoStack),
                    cargoStack.getDisplayName(),
                    L10n.get(cargoStack.getType())
                )
            );
        }
    }

    private void addPeople(List<PersonAPI> people) {
        for (PersonAPI person : people) {
            elements.add(new ListElement(person.getPortraitSprite(), person.getNameString(), person.getPost()));
        }
    }

    private void addShips(List<FleetMemberAPI> ships) {
        for (FleetMemberAPI ship : ships) {
            ShipHullSpecAPI hullSpec = ship.getHullSpec();
            elements.add(
                new ListElement(
                    // Does scale width so each row can be different
                    hullSpec.getSpriteName(),
                    hullSpec.getNameWithDesignationWithDashClass(),
                    L10n.get(hullSpec.getHullSize())
                )
            );
        }
    }

    private String getSpriteFromCargoStack(CargoStackAPI cargoStack) {
        // Might be impossible to correctly render. This is totally wrong.
        return cargoStack.getDisplayName();
    }
}
