package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import java.awt.*;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;

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
        return MemoryHelper.key(ExplorationConstants.MEMORY_PREFIX, this, ExplorationConstants.MEMORY_SUFFIX_CHECKED);
    }

    @Override
    public Color getColor() {
        return faction.getBaseUIColor();
    }

    @Override
    public String getEnabledKey() {
        return MemoryHelper.key(ExplorationConstants.MEMORY_PREFIX, this, ExplorationConstants.MEMORY_SUFFIX_ENABLED);
    }
}
