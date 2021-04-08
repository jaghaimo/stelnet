package stelnet.market.handler.button;

import java.awt.event.KeyEvent;
import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.helper.KeyboardHelper;
import stelnet.market.IntelQuery;
import stelnet.market.handler.ButtonHandler;

public class Disable implements ButtonHandler {

    int queryNumber;

    public Disable(int q) {
        queryNumber = q;
    }

    @Override
    public void handle(List<IntelQuery> queries, IntelUIAPI ui) {
        queries.get(queryNumber).disable();
        KeyboardHelper.send(KeyEvent.VK_E);
    }
}
