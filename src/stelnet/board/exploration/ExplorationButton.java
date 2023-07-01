package stelnet.board.exploration;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import uilib2.Drawable;

@RequiredArgsConstructor
public abstract class ExplorationButton implements Drawable {

    @Setter
    protected String memoryKeyEnabledOverwrite;

    protected String getMemoryKeyEnabled(final ButtonAware entity) {
        if (memoryKeyEnabledOverwrite != null) {
            return memoryKeyEnabledOverwrite;
        }
        return entity.getEnabledKey();
    }
}
