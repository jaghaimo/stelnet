package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.Query;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class DeleteButton extends Button {

    public DeleteButton(Size size, final Query query) {
        super(size, "Delete", true, Misc.getNegativeHighlightColor());
        setCutStyle(CutStyle.TOP);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.delete();
                }
            }
        );
    }
}
