package stelnet.board.query.view.add;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.filter.PersonHasPersonality;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class PersonalityButton extends AreaCheckbox {

    @Getter
    private final PersonHasPersonality filter;

    public PersonalityButton(final PersonnelButtonFactory factory, String translationId, PersonHasPersonality filter) {
        super(new Size(0, 24), L10n.get(translationId), true, false, Misc.getButtonTextColor(), Misc.getGrayColor());
        this.filter = filter;
    }
}
