package stelnet.market_old.dialog;

import stelnet.market_old.dialog.handler.Cargo;
import stelnet.market_old.dialog.handler.CargoFilter;
import stelnet.market_old.dialog.handler.DialogHandler;
import stelnet.market_old.dialog.handler.Dismiss;
import stelnet.market_old.dialog.handler.Menu;
import stelnet.market_old.dialog.handler.Ship;
import stelnet.market_old.dialog.handler.ShipFilter;
import stelnet.market_old.dialog.handler.Staff;
import stelnet.market_old.dialog.handler.StaffFilter;

public class DialogHandlerFactory {

    public static DialogHandler getHandler(DialogOption currentOption, DialogOption previousOption) {
        switch (currentOption) {
        case INIT:
            return new Menu();

        case CARGO:
            return new Cargo(currentOption, previousOption);

        case SHIP:
            return new Ship(currentOption, previousOption);

        case STAFF:
            return new Staff(currentOption, previousOption);

        case EXIT:
            return new Dismiss();

        default:
            return getFilterHandler(currentOption, previousOption);
        }
    }

    public static DialogHandler getFilterHandler(DialogOption currentOption, DialogOption previousOption) {
        DialogOption option = getCurrentOption(currentOption, previousOption);

        switch (option) {
        case CARGO:
            return new CargoFilter(currentOption);

        case SHIP:
            return new ShipFilter(currentOption);

        case STAFF:
            return new StaffFilter(currentOption);

        default:
            return new Menu();
        }
    }

    private static DialogOption getCurrentOption(DialogOption currentOption, DialogOption previousOption) {
        if (previousOption.equals(DialogOption.INIT)) {
            return currentOption;
        }

        return previousOption;
    }
}
