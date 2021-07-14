package stelnet.market_old.dialog.handler;

import stelnet.market_old.dialog.DialogOption;
import stelnet.market_old.dialog.DialogPlugin;
import stelnet.market_old.filter.MutableFilterManager;

public class ShipFilter implements DialogHandler {

    private final DialogOption option;

    public ShipFilter(DialogOption o) {
        option = o;
    }

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        MutableFilterManager filterManager = plugin.getFilterManager();
        filterManager.setFleetShipSize(option);
        filterManager.setFleetShipDamaged(option);
        filterManager.setFleetShipCarrier(option);
        filterManager.setFleetShipCivilian(option);

        plugin.addOptions(DialogOption.SHIP, filterManager.getFleetShipSize(), filterManager.getFleetShipDamaged(),
                filterManager.getFleetShipCarrier(), filterManager.getFleetShipCivilian(), DialogOption.INIT);
        plugin.setEscShortcut(DialogOption.INIT);

        return DialogOption.SHIP;
    }

}
