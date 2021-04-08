package stelnet.market.handler;

import stelnet.market.handler.button.Add;
import stelnet.market.handler.button.Delete;
import stelnet.market.handler.button.DeleteAll;
import stelnet.market.handler.button.Disable;
import stelnet.market.handler.button.Dummy;
import stelnet.market.handler.button.Enable;
import stelnet.market.handler.button.RefreshAll;
import stelnet.market.handler.button.ToggleAll;
import stelnet.market.panel.ControlRow;
import stelnet.market.panel.QueryRow;

public class ButtonHandlerFactory {

    public static ButtonHandler get(Object buttonId) {
        if (buttonId == null) {
            return new Dummy();
        }
        String buttonType = String.valueOf(buttonId);
        if (buttonType.contains("#")) {
            return getIntelHandler(buttonType);
        }
        return getQueriesHandler(buttonType);
    }

    private static ButtonHandler getIntelHandler(String b) {
        String[] buttonIds = b.split("#");
        String buttonType = buttonIds[0];
        int queryNumber = Integer.parseInt(buttonIds[1]);
        ButtonHandler handler = new Dummy();
        if (buttonType.equals(QueryRow.BUTTON_DELETE)) {
            handler = new Delete(queryNumber);
        } else if (buttonType.equals(QueryRow.BUTTON_DISABLE)) {
            handler = new Disable(queryNumber);
        } else if (buttonType.equals(QueryRow.BUTTON_ENABLE)) {
            handler = new Enable(queryNumber);
        }
        return handler;
    }

    private static ButtonHandler getQueriesHandler(String buttonType) {
        ButtonHandler handler = new Dummy();
        if (buttonType.equals(ControlRow.BUTTON_ADD)) {
            handler = new Add();
        } else if (buttonType.equals(ControlRow.BUTTON_DELETE_ALL)) {
            handler = new DeleteAll();
        } else if (buttonType.equals(ControlRow.BUTTON_REFRESH_ALL)) {
            handler = new RefreshAll();
        } else if (buttonType.equals(ControlRow.BUTTON_TOGGLE_ALL)) {
            handler = new ToggleAll();
        }
        return handler;
    }
}
