package stelnet.market.dialog.handler;

import stelnet.market.dialog.DialogOption;
import stelnet.market.dialog.DialogPlugin;

public class Dismiss implements DialogHandler {

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        plugin.dismiss();
        return DialogOption.EXIT;
    }
}
