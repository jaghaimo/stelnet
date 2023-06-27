package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.util.L10n;

public class ShipPickerDialog extends PickerDialog {

    private final List<FleetMemberAPI> members;
    private final String type;

    public ShipPickerDialog(final List<FleetMemberAPI> members, final QueryFactory factory) {
        super(factory);
        this.members = members;
        this.type = L10n.common("SHIPS");
    }

    @Override
    protected void show() {
        dialog.showFleetMemberPickerDialog(
            L10n.query("SELECT_SHIPS_TO_SEARCH_FOR"),
            L10n.common("SEARCH"),
            L10n.common("CANCEL"),
            7,
            10,
            92,
            true,
            true,
            members,
            new QueryPickerListener(this, factory, type)
        );
    }
}
