package stelnet.board.query.view.add;

import lombok.Getter;
import stelnet.filter.Filter;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class OfficerButton extends AreaCheckbox implements FilteringButton {

    @Getter
    private final Filter filter;

    public OfficerButton(String translatedString, Filter filter) {
        super(new Size(0, 24), translatedString, true, false);
        this.filter = filter;
    }
}
