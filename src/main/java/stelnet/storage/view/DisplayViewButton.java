package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import stelnet.L10n;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageView;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.property.Location;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

public class DisplayViewButton extends Button {

    public DisplayViewButton(final StorageView view) {
        super(new Size(180, 24), L10n.get("storageView" + view.getTitle()), true, Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_G);
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(30, 8));
        setHandler(new EventHandler() {
            @Override
            public void onConfirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.setActiveView(view);
            }
        });
    }
}
