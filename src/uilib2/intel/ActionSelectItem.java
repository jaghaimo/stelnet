package uilib2.intel;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActionSelectItem implements IntelUiAction {

    private final IntelInfoPlugin plugin;

    @Override
    public void act(IntelUIAPI ui) {
        ui.selectItem(plugin);
    }
}
