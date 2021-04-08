package stelnet.market.handler.dialog;

import stelnet.market.DialogOption;
import stelnet.market.DialogPlugin;
import stelnet.market.handler.DialogHandler;

public class Dismiss implements DialogHandler {

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        plugin.dismiss();
        return DialogOption.EXIT;
    }
}
