package stelnet.market.handler.button;

import java.awt.event.KeyEvent;
import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.helper.KeyboardHelper;
import stelnet.market.IntelQuery;
import stelnet.market.handler.ButtonHandler;

public class RefreshAll implements ButtonHandler {

    @Override
    public void handle(List<IntelQuery> queries, IntelUIAPI ui) {
        for (IntelQuery query : queries) {
            query.refresh();
        }
        KeyboardHelper.send(KeyEvent.VK_E);
    }
}
