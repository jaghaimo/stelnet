package stelnet.market.handler;

import stelnet.market.DialogOption;
import stelnet.market.handler.dialog.Cargo;
import stelnet.market.handler.dialog.CargoFilter;
import stelnet.market.handler.dialog.Dismiss;
import stelnet.market.handler.dialog.Menu;
import stelnet.market.handler.dialog.Ship;
import stelnet.market.handler.dialog.ShipFilter;
import stelnet.market.handler.dialog.Staff;
import stelnet.market.handler.dialog.StaffFilter;

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
