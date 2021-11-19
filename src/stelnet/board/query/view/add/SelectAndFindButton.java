package stelnet.board.query.view.add;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.List;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.view.dialog.ItemPickerDialog;
import stelnet.board.query.view.dialog.PickerDialog;
import stelnet.board.query.view.dialog.ShipPickerDialog;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.EventHandler;
import uilib.property.Size;

public class SelectAndFindButton extends C2Button {

    private SelectAndFindButton(final PickerDialog plugin) {
        super(new Size(0, 30), L10n.get(QueryL10n.FIND_SELECTED), true);
        overrideSize(30);
        setShortcut(Keyboard.KEY_S);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    plugin.setUi(ui);
                    ui.showDialog(null, plugin);
                }
            }
        );
    }

    public SelectAndFindButton(QueryFactory factory, CargoAPI cargo) {
        this(new ItemPickerDialog(cargo, factory));
    }

    public SelectAndFindButton(QueryFactory factory, List<FleetMemberAPI> members) {
        this(new ShipPickerDialog(members, factory));
    }
}
