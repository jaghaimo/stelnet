package uilib2.intel.actions;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.RequiredArgsConstructor;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class UpdateForItem implements IntelUiAction {

    private final IntelInfoPlugin plugin;

    @Override
    public void act(IntelUIAPI ui) {
        ui.updateUIForItem(plugin);
    }
}
