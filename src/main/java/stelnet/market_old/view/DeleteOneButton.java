package stelnet.market_old.view;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market_old.intel.IntelQuery;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.property.Location;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

public class DeleteOneButton extends Button {

    public DeleteOneButton(final List<IntelQuery> queries, final int number) {
        super(new Size(120, 24), "Delete", true, Misc.getNegativeHighlightColor());
        setLocation(Location.TOP_RIGHT);
        setOffset(new Position(10, -24));
        setHandler(new EventHandler() {

            @Override
            public boolean hasPrompt() {
                return true;
            }

            @Override
            public void onConfirm(IntelUIAPI ui) {
                queries.get(number).disable();
                queries.remove(number);
            }

            @Override
            public void onPrompt(TooltipMakerAPI tooltipMaker) {
                String description = queries.get(number).getDescription();
                tooltipMaker.addPara("Are you sure you want to delete '" + description + "' intel query?",
                        Misc.getTextColor(), 0f);
            }
        });
    }
}
