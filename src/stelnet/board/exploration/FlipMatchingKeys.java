package stelnet.board.exploration;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryManager;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class FlipMatchingKeys implements IntelUiAction {

    private final List<ButtonAware> entities;
    private final MemoryManager memoryManager = MemoryManager.getInstance();

    @Override
    public void act(final IntelUIAPI ui) {
        for (final ButtonAware entity : entities) {
            changeIfEligible(entity);
        }
    }

    private void changeIfEligible(final ButtonAware value) {
        final String key = value.getCheckedKey();
        if (!memoryManager.has(key)) {
            return;
        }
        memoryManager.flip(key);
    }
}
