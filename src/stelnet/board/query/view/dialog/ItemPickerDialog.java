package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.util.L10n;

public class ItemPickerDialog extends PickerDialog {

    private final CargoAPI cargo;
    private final String type;

    public ItemPickerDialog(CargoAPI cargo, QueryFactory factory) {
        super(factory);
        this.cargo = cargo;
        this.type = L10n.get(CommonL10n.ITEMS);
    }

    @Override
    protected void show() {
        dialog.showCargoPickerDialog(
            L10n.get(QueryL10n.SELECT_ITEMS_TO_SEARCH_FOR),
            L10n.get(CommonL10n.SEARCH),
            L10n.get(CommonL10n.CANCEL),
            false,
            0f,
            cargo,
            new QueryPickerListener(this, factory, type)
        );
    }
}
