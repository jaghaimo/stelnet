package uilib;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowSkills extends RenderableComponent {

    private final List<SkillLevelAPI> skills;
    private final String title;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addPara(title, UiConstants.DEFAULT_BUTTON_PADDING);
        tooltip.addSpacer(UiConstants.DEFAULT_BUTTON_PADDING);
        for (SkillLevelAPI skill : skills) {
            showSkill(tooltip, skill);
        }
    }

    private void showSkill(TooltipMakerAPI tooltip, SkillLevelAPI skill) {
        String elite = skill.getLevel() > 1 ? "Elite " : "";
        String skillString = String.format("%s%s", elite, skill.getSkill().getName());
        TooltipMakerAPI inner = tooltip.beginImageWithText(skill.getSkill().getSpriteName(), 18);
        inner.addPara(skillString, 0, Misc.getHighlightColor(), "Elite");
        tooltip.addImageWithText(2);
    }
}
