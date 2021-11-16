package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.Query;
import uilib.EventHandler;
import uilib.ToggleButton;
import uilib.property.Size;

public class PreviewButton extends ToggleButton {

    public PreviewButton(Size size, final Query query) {
        super(size, "Preview", "Preview", true, query.isSelected());
        setCutStyle(CutStyle.TOP);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.select();
                }
            }
        );
    }
}
