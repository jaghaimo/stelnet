package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.view.add.QueryFactory;

public class ItemPickerDialog extends PickerDialog {

    private final CargoAPI cargo;

    public ItemPickerDialog(CargoAPI cargo, QueryFactory factory, IntelUIAPI ui) {
        super(factory, ui);
        this.cargo = cargo;
    }

    @Override
    protected void show() {
        dialog.showCargoPickerDialog(
            "Select items to search for...",
            "Find selected",
            "Find all",
            false,
            0f,
            cargo,
            new QueryPickerListener(this, factory)
        );
    }
}
