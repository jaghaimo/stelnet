package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.Query;
import uilib.EventHandler;
import uilib.ToggleButton;
import uilib.property.Size;

public class OnOffButton extends ToggleButton {

    public OnOffButton(Size size, final Query query) {
        super(size, "Enabled", "Disabled", true, query.isEnabled());
        setCutStyle(CutStyle.TOP);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.toggle();
                }
            }
        );
    }
}
