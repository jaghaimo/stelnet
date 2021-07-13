package stelnet.market.dialog.handler;

import stelnet.market.dialog.DialogHandlerFactory;
import stelnet.market.dialog.DialogOption;
import stelnet.market.dialog.DialogPlugin;

public class FilterAware implements DialogHandler {

    protected DialogOption option;
    protected DialogOption previousOption;

    public FilterAware(DialogOption o, DialogOption p) {
        option = o;
        previousOption = p;
    }

    public DialogOption handle(DialogPlugin plugin) {
        if (previousOption.equals(DialogOption.INIT)) {
            String action = option.getName();
            plugin.addTitle(action);
            plugin.addText("Please select filters and " + action.toLowerCase() + ".");
            return init(plugin);
        }

        return run(plugin);
    }

    protected DialogOption init(DialogPlugin plugin) {
        DialogHandler handler = DialogHandlerFactory.getFilterHandler(option, previousOption);
        return handler.handle(plugin);
    }

    protected DialogOption run(DialogPlugin plugin) {
        return option;
    }
}
