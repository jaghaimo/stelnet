package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import java.awt.*;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryManager;

@RequiredArgsConstructor
public class ButtonAwareFaction implements ButtonAware {

    private final FactionAPI faction;

    @Override
    public String getId() {
        return faction.getId();
    }

    @Override
    public String getTitle() {
        return faction.getDisplayName();
    }

    @Override
    public String getCheckedKey() {
        return getKey(ExplorationConstants.MEMORY_SUFFIX_CHECKED);
    }

    @Override
    public Color getColor() {
        return faction.getBaseUIColor();
    }

    @Override
    public String getEnabledKey() {
        return getKey(ExplorationConstants.MEMORY_SUFFIX_ENABLED);
    }

    private String getKey(final String suffix) {
        return MemoryManager.getInstance().key(ExplorationConstants.MEMORY_PREFIX, this, suffix);
    }
}
