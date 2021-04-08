package stelnet.storage.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.filter.cargostack.IsNotWeapon;
import stelnet.storage.StorageBoard;

public class Weapons extends Button {

    public Weapons() {
        super("Weapons", new IsNotWeapon());
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        super.handle(board, ui);
        board.getButtonManager().setEnabledWeaponButtons(isStateOn());
        ui.updateUIForItem(board);
    }
}
