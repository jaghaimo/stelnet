package stelnet.storage.view;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;

import org.lwjgl.input.Keyboard;

import stelnet.L10n;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageView;
import stelnet.ui.EventHandler;
import stelnet.ui.TabButton;
import stelnet.ui.property.Location;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

public class DisplayViewButton extends TabButton {

    public DisplayViewButton(final StorageView view) {
        super(L10n.get("storageView" + view.getTitle()), true, Keyboard.KEY_G);
        setCutStyle(CutStyle.ALL);
        setSize(new Size(180, 24));
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
