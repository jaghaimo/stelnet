package stelnet.board.exploration;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class FlipMatchingKeys implements IntelUiAction {

    private final String enumPrefix;

    @Override
    public void act(final IntelUIAPI ui) {
        for (final ExplorationL10n value : ExplorationL10n.values()) {
            changeIfEligible(value);
        }
    }

    private void changeIfEligible(final ExplorationL10n value) {
        if (!value.name().startsWith(enumPrefix)) {
            return;
        }
        final String key = ExplorationHelper.getCheckedKey(value);
        if (!MemoryHelper.has(key)) {
            return;
        }
        MemoryHelper.flip(key);
    }
}
