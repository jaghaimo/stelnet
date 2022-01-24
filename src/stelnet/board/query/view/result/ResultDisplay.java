package stelnet.board.query.view.result;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Result;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.CollectionUtils;
import uilib.RenderableComponent;
import uilib.ShowSkills;
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
        List<SkillLevelAPI> skills = person.getStats().getSkillsCopy();
        CollectionUtils.reduce(skills, new SkillIsCombatOfficer());
        boolean hasSkills = !skills.isEmpty();
        String description =
            person.getNameString() +
            " is a level " +
            person.getStats().getLevel() +
            " " +
            person.getPost().toLowerCase();
        if (hasSkills) {
            description += " with " + skills.size() + " skills:";
        } else {
            description += ".";
        }
        tooltip.addPara(description, UiConstants.DEFAULT_SPACER);
        if (hasSkills) {
            tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
            (new ShowSkills(person)).render(tooltip);
        }
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
