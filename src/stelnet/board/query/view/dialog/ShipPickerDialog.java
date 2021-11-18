package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.util.L10n;

public class ShipPickerDialog extends PickerDialog {

    private final List<FleetMemberAPI> members;

    public ShipPickerDialog(List<FleetMemberAPI> members, QueryFactory factory) {
        super(factory);
        this.members = members;
    }

    @Override
    protected void show() {
        dialog.showFleetMemberPickerDialog(
            L10n.get(QueryL10n.SELECT_SHIPS_TO_SEARCH_FOR),
            L10n.get(CommonL10n.SEARCH),
            L10n.get(CommonL10n.CANCEL),
            7,
            10,
            92,
            true,
            true,
            members,
            new QueryPickerListener(this, factory)
        );
    }
}
