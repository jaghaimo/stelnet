package stelnet.board.query.view.result;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Result;
import uilib.RenderableComponent;
import uilib.UiConstants;

@RequiredArgsConstructor
public class ResultDisplay extends RenderableComponent {

    private final float width;
    private final Result result;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        renderPerson(tooltip);
        renderCargoStack(tooltip);
        renderFleetMember(tooltip);
    }

    private void renderPerson(TooltipMakerAPI tooltip) {
        if (!result.isPerson()) {
            return;
        }
        PersonAPI person = result.getPerson();
        tooltip.addPara(
            person.getNameString() + " is a level " + person.getStats().getLevel() + " " + person.getRankId(),
            UiConstants.DEFAULT_SPACER
        );
        tooltip.addSkillPanel(person, UiConstants.DEFAULT_SPACER);
    }

    private void renderCargoStack(TooltipMakerAPI tooltip) {
        if (!result.isCargoStack()) {
            return;
        }
    }

    private void renderFleetMember(TooltipMakerAPI tooltip) {
        if (!result.isFleetMember()) {
            return;
        }
    }
}
