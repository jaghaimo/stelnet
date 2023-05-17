package stelnet.board.exploration;

import lombok.Getter;
import uilib.Checkbox;
import uilib.property.Size;

public class SavableCheckbox extends Checkbox {

    @Getter
    private final String key;

    public SavableCheckbox(String key, String text, Size size) {
        super(text, size);
        this.key = key;
    }
}
