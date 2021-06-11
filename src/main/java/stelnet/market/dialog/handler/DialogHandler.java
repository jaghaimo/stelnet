package stelnet.market.dialog.handler;

import stelnet.market.dialog.DialogOption;
import stelnet.market.dialog.DialogPlugin;

public interface DialogHandler {

    public DialogOption handle(DialogPlugin plugin);
}
