package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.util.L10n;

public class ItemPickerDialog extends PickerDialog {

    private final CargoAPI cargo;
    private final String type;

    public ItemPickerDialog(final CargoAPI cargo, final QueryFactory factory) {
        super(factory);
        this.cargo = cargo;
        this.type = L10n.common("ITEMS");
    }

    @Override
    protected void show() {
        dialog.showCargoPickerDialog(
            L10n.query("SELECT_ITEMS_TO_SEARCH_FOR"),
            L10n.common("SEARCH"),
            L10n.common("CANCEL"),
            false,
            0f,
            cargo,
            new QueryPickerListener(this, factory, type)
        );
    }
}
