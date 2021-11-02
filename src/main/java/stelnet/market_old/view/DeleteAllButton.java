package stelnet.market_old.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import stelnet.market_old.intel.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.property.Location;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

public class DeleteAllButton extends Button {

    public DeleteAllButton(final List<IntelQuery> queries) {
        super(new Size(120, 24), "Delete All", !queries.isEmpty(), Misc.getNegativeHighlightColor());
        setShortcut(Keyboard.KEY_G);
        setLocation(Location.TOP_RIGHT);
        setOffset(new Position(4, -24));
        setHandler(new EventHandler() {

            @Override
            public boolean hasPrompt() {
                return true;
            }

            @Override
            public void onConfirm(IntelUIAPI ui) {
                for (IntelQuery query : queries) {
                    query.disable();
                }
                queries.clear();
            }

            @Override
            public void onPrompt(TooltipMakerAPI tooltipMaker) {
                tooltipMaker.addPara("Are you sure you want to delete ALL intel queries?", Misc.getTextColor(), 0f);
            }
        });
    }
}
