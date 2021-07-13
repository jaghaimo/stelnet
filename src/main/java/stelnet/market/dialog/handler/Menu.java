package stelnet.market.dialog.handler;

import stelnet.market.dialog.DialogOption;
import stelnet.market.dialog.DialogPlugin;

public class Menu implements DialogHandler {

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        plugin.addText("Back to main menu...");
        forceMenu(plugin);
        return DialogOption.INIT;
    }

    public static void forceMenu(DialogPlugin plugin) {
        plugin.addTitle("Market Search");
        plugin.addText("Are there any other queries you would like to make?");
        plugin.showMenu();
    }
}
