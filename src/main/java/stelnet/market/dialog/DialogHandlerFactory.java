package stelnet.market.dialog;

import stelnet.market.dialog.handler.Cargo;
import stelnet.market.dialog.handler.CargoFilter;
import stelnet.market.dialog.handler.DialogHandler;
import stelnet.market.dialog.handler.Dismiss;
import stelnet.market.dialog.handler.Menu;
import stelnet.market.dialog.handler.Ship;
import stelnet.market.dialog.handler.ShipFilter;
import stelnet.market.dialog.handler.Staff;
import stelnet.market.dialog.handler.StaffFilter;

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
