package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.input.Keyboard;
import stelnet.storage.StorageBoard;
import stelnet.storage.SubmarketDataProvider;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class DisplayViewButton extends Button {

    public DisplayViewButton(final SubmarketDataProvider view) {
        super(new Size(180, 24), L10n.get("storageView" + view.getTitle()), true, Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_G);
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(16, 8));
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance();
                    board.getState().setActiveView(view);
                }
            }
        );
    }
}
