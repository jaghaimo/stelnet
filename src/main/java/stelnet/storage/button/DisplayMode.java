package stelnet.storage.button;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;

public class DisplayMode extends Button {

    public final static String CARGO = "Display Cargo";
    public final static String SHIPS = "Display Ships";

    public DisplayMode(boolean isStateOn) {
        super("", isStateOn);
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }

    @Override
    public String getTitle() {
        return isStateOn() ? CARGO : SHIPS;
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        board.togglePane();
        ui.updateUIForItem(board);
    }
}
