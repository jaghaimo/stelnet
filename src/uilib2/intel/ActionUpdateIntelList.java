package uilib2.intel;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ActionUpdateIntelList implements IntelUiAction {

    private boolean retainCurrentSelection = false;

    @Override
    public void act(IntelUIAPI ui) {
        ui.updateIntelList(retainCurrentSelection);
    }
}
