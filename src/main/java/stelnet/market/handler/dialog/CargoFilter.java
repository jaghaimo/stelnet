package stelnet.market.handler.dialog;

import stelnet.market.DialogOption;
import stelnet.market.DialogPlugin;
import stelnet.market.filter.MutableFilterManager;
import stelnet.market.handler.DialogHandler;

public class CargoFilter implements DialogHandler {

    private final DialogOption option;

    public CargoFilter(DialogOption o) {
        option = o;
    }

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        MutableFilterManager filterManager = plugin.getFilterManager();
        filterManager.setCargoType(option);
        filterManager.setCargoWeaponSize(option);
        filterManager.setCargoWeaponType(option);
        filterManager.setCargoWingType(option);

        DialogOption o = filterManager.getCargoType();
        if (o.equals(DialogOption.CARGO_TYPE_WEAPON)) {
            plugin.addOptions(DialogOption.CARGO, filterManager.getCargoType(), filterManager.getCargoWeaponSize(),
                    filterManager.getCargoWeaponType(), DialogOption.INIT);
        } else if (o.equals(DialogOption.CARGO_TYPE_FIGHTER)) {
            plugin.addOptions(DialogOption.CARGO, filterManager.getCargoType(), filterManager.getCargoWingType(),
                    DialogOption.INIT);
        } else if (o.equals(DialogOption.CARGO_TYPE_MODSPEC)) {
            plugin.addOptions(DialogOption.CARGO, filterManager.getCargoType(), DialogOption.INIT);
        } else if (o.equals(DialogOption.CARGO_TYPE_BLUEPRINT)) {
            plugin.addOptions(DialogOption.CARGO, filterManager.getCargoType(), DialogOption.INIT);
        }

        plugin.setEscShortcut(DialogOption.INIT);
        return DialogOption.CARGO;
    }
}
