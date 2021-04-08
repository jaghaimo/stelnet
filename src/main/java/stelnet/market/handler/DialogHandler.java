package stelnet.market.handler;

import stelnet.market.DialogOption;
import stelnet.market.DialogPlugin;

public interface DialogHandler {

    public DialogOption handle(DialogPlugin plugin);
}
