package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import stelnet.L10n;
import stelnet.market.dialog.MarketSelectDialog;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.property.Location;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

public class MarketSelectButton extends Button {

    public MarketSelectButton() {
        super(new Size(180, 24), L10n.get("marketViewSelectMarket"), true, Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_M);
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(30, 8));
        setHandler(new EventHandler() {
            @Override
            public void onConfirm(IntelUIAPI ui) {
                ui.showDialog(null, new MarketSelectDialog(ui));
            }
        });
    }
}
