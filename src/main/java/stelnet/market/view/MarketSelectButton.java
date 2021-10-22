package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.input.Keyboard;
import stelnet.market.dialog.MarketSelectDialog;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class MarketSelectButton extends Button {

    public MarketSelectButton() {
        super(new Size(180, 24), L10n.get("marketViewSelectMarket"), true, Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_M);
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(16, 8));
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    ui.showDialog(null, new MarketSelectDialog(ui));
                }
            }
        );
    }
}
