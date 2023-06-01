package stelnet.board.exploration.button;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.exploration.ExplorationBoard;
import stelnet.board.exploration.IdAware;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;

@RequiredArgsConstructor
public abstract class ExplorationButton implements Drawable {

    @Setter
    protected String memoryKeyEnabledOverwrite;

    protected String getMemoryKeyEnabled(IdAware entity) {
        if (memoryKeyEnabledOverwrite != null) {
            return memoryKeyEnabledOverwrite;
        }
        return MemoryHelper.key(ExplorationBoard.MEMORY_PREFIX, entity, ExplorationBoard.MEMORY_SUFFIX_ENABLED);
    }
}
