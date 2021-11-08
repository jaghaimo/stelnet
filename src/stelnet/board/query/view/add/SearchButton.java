package stelnet.board.query.view.add;

import stelnet.util.L10n;
import uilib.Button;
import uilib.property.Size;

public class SearchButton extends Button {

    public SearchButton(Enum<?> translationId) {
        super(new Size(0, 30), L10n.get(translationId), true);
    }
}
