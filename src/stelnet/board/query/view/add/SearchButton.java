package stelnet.board.query.view.add;

import stelnet.util.L10n;
import uilib.Button;
import uilib.property.Size;

public class SearchButton extends Button {

    public SearchButton(Size size, Enum<?> translationId, boolean isEnabled) {
        super(size, L10n.get(translationId), isEnabled);
    }
}
