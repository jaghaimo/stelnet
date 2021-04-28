package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import stelnet.market.IntelQuery;
import stelnet.ui.AbstractButtonHandler;
import stelnet.ui.Button;
import stelnet.ui.Size;

public class UpdateAllButton extends Button {

    public UpdateAllButton(final List<IntelQuery> queries) {
        super(new Size(120, 24), "Update All", !queries.isEmpty(), Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_U);
        setHandler(new AbstractButtonHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                for (IntelQuery query : queries) {
                    query.refresh();
                }
            }
        });
    }
}
