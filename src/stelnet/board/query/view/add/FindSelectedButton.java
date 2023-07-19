package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.view.dialog.PickerDialog;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class FindSelectedButton extends C2Button {

    public FindSelectedButton(final PickerDialog plugin) {
        super(new Size(0, UiConstants.VICTOR_14_BUTTON_HEIGHT), L10n.query("FIND_SELECTED"), true);
        overrideSize(30);
        setShortcut(Keyboard.KEY_S);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    plugin.setUi(ui);
                    ui.showDialog(null, plugin);
                }
            }
        );
    }
}
