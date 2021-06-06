package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import stelnet.market.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.property.Size;

public class ToggleAllButton extends Button {

    public ToggleAllButton(final List<IntelQuery> queries) {
        super(new Size(120, 24), "Toggle All", !queries.isEmpty(), Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_T);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                for (IntelQuery query : queries) {
                    query.toggle();
                }
            }
        });
    }
}
