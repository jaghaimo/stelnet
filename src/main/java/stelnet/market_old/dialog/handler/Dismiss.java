package stelnet.market_old.dialog.handler;

import stelnet.market_old.dialog.DialogOption;
import stelnet.market_old.dialog.DialogPlugin;

public class Dismiss implements DialogHandler {

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        plugin.dismiss();
        return DialogOption.EXIT;
    }
}
