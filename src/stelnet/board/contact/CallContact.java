package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryManager;
import stelnet.util.ModConstants;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class CallContact implements IntelUiAction {

    private final MarketAPI market;
    private final PersonAPI person;

    @Override
    public void act(final IntelUIAPI ui) {
        MemoryManager.getInstance().set(ModConstants.MEMORY_IS_CALLING, true);
        ui.showDialog(
            market.getPrimaryEntity(),
            new ContactDialog(ui, person, market.getSubmarket(Submarkets.SUBMARKET_STORAGE))
        );
    }
}
