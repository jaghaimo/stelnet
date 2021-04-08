package stelnet.market.handler.dialog;

import stelnet.market.DialogOption;
import stelnet.market.DialogPlugin;
import stelnet.market.handler.DialogHandler;

public class Menu implements DialogHandler {

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        plugin.addText("Back to main menu...");
        forceMenu(plugin);
        return DialogOption.INIT;
    }

    public static void forceMenu(DialogPlugin plugin) {
        plugin.addTitle("HyperNET");
        plugin.addText("Are there any other queries you would like to make?");
        plugin.showMenu();
    }
}
