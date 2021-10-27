package stelnet.board.market.view;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class PersonnelButton extends AreaCheckbox {

    @Getter
    private final HorizontalViewFactory nextFactory;

    public PersonnelButton(
        final PersonnelHorizontalViewFactory factory,
        String translationId,
        HorizontalViewFactory nextFactory
    ) {
        super(new Size(0, 24), L10n.get(translationId), true, false, Misc.getButtonTextColor(), Misc.getGrayColor());
        this.nextFactory = nextFactory;
    }
}
