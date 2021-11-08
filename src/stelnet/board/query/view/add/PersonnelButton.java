package stelnet.board.query.view.add;

import lombok.Getter;
import stelnet.filter.PersonIsPostedAs;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class PersonnelButton extends AreaCheckbox implements FilteringButton {

    @Getter
    private final PersonIsPostedAs filter;

    public PersonnelButton(Enum<?> translationId, PersonIsPostedAs filter) {
        super(new Size(0, 24), L10n.get(translationId), true, false);
        this.filter = filter;
    }
}
