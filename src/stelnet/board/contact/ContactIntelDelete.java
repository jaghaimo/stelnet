package stelnet.board.contact;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import org.lwjgl.input.Keyboard;
import stelnet.CommonL10n;
import stelnet.util.IntelUtils;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class ContactIntelDelete extends Button {

    public ContactIntelDelete(float width, final IntelInfoPlugin intel) {
        super(new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT), L10n.get(CommonL10n.DELETE), true);
        setShortcut(Keyboard.KEY_D);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    IntelUtils.remove(intel);
                }
            }
        );
    }
}
