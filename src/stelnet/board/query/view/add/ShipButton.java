package stelnet.board.query.view.add;

import lombok.Getter;
import stelnet.filter.Filter;
import uilib.AreaCheckbox;
import uilib.property.Size;

public class ShipButton extends AreaCheckbox implements FilteringButton {

    @Getter
    private final Filter filter;

    public ShipButton(String title, Filter filter) {
        super(new Size(0, 24), title, true, false);
        this.filter = filter;
    }
}
