package stelnet.market.handler.dialog;

import stelnet.market.DialogOption;
import stelnet.market.DialogPlugin;
import stelnet.market.filter.MutableFilterManager;
import stelnet.market.handler.DialogHandler;

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
