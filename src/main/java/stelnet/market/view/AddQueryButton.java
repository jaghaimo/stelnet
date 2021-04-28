package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import stelnet.market.DialogPlugin;
import stelnet.market.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Size;

public class AddQueryButton extends Button {

    public AddQueryButton(final List<IntelQuery> queries) {
        super(new Size(160, 24), "Add New Query", true, Misc.getPositiveHighlightColor());
        setShortcut(Keyboard.KEY_A);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                InteractionDialogPlugin intelDialog = new DialogPlugin(queries);
                ui.showDialog(null, intelDialog);
            }
        });
    }
}
