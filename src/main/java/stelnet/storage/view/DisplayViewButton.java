package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import org.lwjgl.input.Keyboard;

import stelnet.L10n;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageView;
import stelnet.ui.EventHandler;
import stelnet.ui.Location;
import stelnet.ui.Position;
import stelnet.ui.Size;
import stelnet.ui.TabButton;

public class DisplayViewButton extends TabButton {

    public DisplayViewButton(final StorageView view) {
        super(L10n.get("storageView" + view.title), true, Keyboard.KEY_G);
        setSize(new Size(180, 24));
        setLocation(Location.TOP_RIGHT);
        setOffset(new Position(30, -2));
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.setActiveView(view);
            }
        });
    }
}
