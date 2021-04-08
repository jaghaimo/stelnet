package stelnet.market.handler.button;

import java.util.List;

import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.market.DialogPlugin;
import stelnet.market.IntelQuery;
import stelnet.market.handler.ButtonHandler;

public class Add implements ButtonHandler {

    @Override
    public void handle(List<IntelQuery> queries, IntelUIAPI ui) {
        InteractionDialogPlugin intelDialog = new DialogPlugin(queries);
        ui.showDialog(null, intelDialog);
    }
}
