package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import stelnet.market.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;

public class ToggleAllButton extends Button {

    public ToggleAllButton(final List<IntelQuery> queries) {
        super(new Size(120, 24), "Toggle All", !queries.isEmpty(), Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_T);
        setCallback(new SimpleCallback() {

            @Override
            public void confirm(IntelUIAPI ui) {
                for (IntelQuery query : queries) {
                    query.toggle();
                }
            }
        });
    }
}
