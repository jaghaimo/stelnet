package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.L10n;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageView;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Size;

public class DisplayViewButton extends Button {

    public DisplayViewButton(final StorageView view) {
        super(new Size(180, 24), L10n.get("storageView" + view.title), true, Misc.getButtonTextColor());
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.setActiveView(view);
            }
        });
    }
}
