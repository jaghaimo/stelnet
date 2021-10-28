package stelnet.board.query.view.add;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.filter.market.person.PersonFilter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class PostTypeButton extends AreaCheckbox {

    @Getter
    private final PersonFilter filter;

    public PostTypeButton(final PersonnelButtonFactory factory, String translationId, PersonFilter filter) {
        super(new Size(0, 24), L10n.get(translationId), true, false, Misc.getButtonTextColor(), Misc.getGrayColor());
        this.filter = filter;
    }
}
