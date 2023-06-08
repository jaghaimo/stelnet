package stelnet.board.exploration;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class FlipMatchingKeys implements IntelUiAction {

    private final String enumPrefix;

    @Override
    public void act(IntelUIAPI ui) {
        for (ExplorationL10n value : ExplorationL10n.values()) {
            changeIfEligible(value);
        }
    }

    private void changeIfEligible(ExplorationL10n value) {
        if (!value.name().startsWith(enumPrefix)) {
            return;
        }
        String key = MemoryHelper.key(ExplorationBoard.MEMORY_PREFIX, value, ExplorationBoard.MEMORY_SUFFIX_CHECKED);
        if (!MemoryHelper.has(key)) {
            return;
        }
        MemoryHelper.flip(key);
    }
}
