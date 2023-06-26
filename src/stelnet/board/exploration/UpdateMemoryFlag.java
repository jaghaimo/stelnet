package stelnet.board.exploration;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.intel.IntelUiAction;

@RequiredArgsConstructor
public class UpdateMemoryFlag implements IntelUiAction {

    private final String key;
    private final Object value;

    @Override
    public void act(final IntelUIAPI ui) {
        MemoryHelper.set(key, value);
    }
}
