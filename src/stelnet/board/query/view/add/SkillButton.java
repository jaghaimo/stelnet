package stelnet.board.query.view.add;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.filter2.PersonHasSkill;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class SkillButton extends AreaCheckbox {

    @Getter
    private final PersonHasSkill filter;

    public SkillButton(final PersonnelButtonFactory factory, String translationId, PersonHasSkill filter) {
        super(new Size(0, 24), L10n.get(translationId), true, false, Misc.getButtonTextColor(), Misc.getGrayColor());
        this.filter = filter;
    }
}
