package stelnet.board.exploration;

import stelnet.util.L10n;
import uilib.property.Size;

public class TranslatableButton extends SavableCheckbox implements ExplorationButton {

    public TranslatableButton(ExplorationL10n key, Size size) {
        super(key.name(), L10n.get(key), size);
    }
}
