package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.L10n;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageView;
import stelnet.ui.EventHandler;
import stelnet.ui.Location;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayViewButton extends ToggleButton {

    public DisplayViewButton(final StorageView view, boolean isToggledOn) {
        super(new Size(180, 24), L10n.get("storageView" + view.title), L10n.get("storageView" + view.title), true,
                Misc.getButtonTextColor(), Misc.getButtonTextColor(), isToggledOn);
        setLocation(Location.TOP_RIGHT);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.setActiveView(view);
            }
        });
        if (isToggledOn()) {
            setColor(Misc.getHighlightColor());
        }
    }
}
