package stelnet.board.exploration;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class FlipMatchingKeys implements IntelUiAction {

    private final List<IdAware> idAwares;

    @Override
    public void act(final IntelUIAPI ui) {
        for (final IdAware idAware : idAwares) {
            changeIfEligible(idAware);
        }
    }

    private void changeIfEligible(final IdAware value) {
        final String key = ExplorationHelper.getCheckedKey(value);
        if (!MemoryHelper.has(key)) {
            return;
        }
        MemoryHelper.flip(key);
    }
}
