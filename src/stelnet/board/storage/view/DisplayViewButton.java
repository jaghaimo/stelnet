package stelnet.board.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.input.Keyboard;
import stelnet.board.storage.StorageBoard;
import stelnet.board.storage.SubmarketDataStrategy;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class DisplayViewButton extends Button {

    public DisplayViewButton(final SubmarketDataStrategy newStrategy) {
        super(new Size(180, 24), L10n.get("storageView" + newStrategy.id), true, Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_G);
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(16, 8));
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance();
                    board.getState().setActiveStrategy(newStrategy);
                }
            }
        );
    }
}
