package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.List;
import stelnet.board.query.view.add.QueryFactory;

public class ShipPickerDialog extends PickerDialog {

    private final List<FleetMemberAPI> members;

    public ShipPickerDialog(List<FleetMemberAPI> members, QueryFactory factory, IntelUIAPI ui) {
        super(factory, ui);
        this.members = members;
    }

    @Override
    protected void show() {
        dialog.showFleetMemberPickerDialog(
            "Select ships to search for...",
            "Find selected",
            "Find all",
            8,
            12,
            64,
            true,
            true,
            members,
            new QueryPickerListener(this, factory)
        );
    }
}
