package uilib2.intel.actions;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uilib2.intel.IntelUiAction;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateIntelList implements IntelUiAction {

    private boolean retainCurrentSelection = true;

    @Override
    public void act(IntelUIAPI ui) {
        ui.updateIntelList(retainCurrentSelection);
    }
}
