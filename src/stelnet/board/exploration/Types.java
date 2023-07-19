package stelnet.board.exploration;

import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.settings.CaptainsLogSettings;
import stelnet.util.L10n;
import stelnet.util.MemoryManager;

@RequiredArgsConstructor
public enum Types implements ButtonAware {
    TYPE_ANALYZE_MISSION,
    TYPE_ANY_RUINS(CaptainsLogSettings.RUINS.isEnabled()),
    TYPE_COLONY_STRUCTURE(CaptainsLogSettings.COLONY_STRUCTURES.isEnabled()),
    TYPE_COMM_RELAY(CaptainsLogSettings.COMM_RELAYS.isEnabled()),
    TYPE_HISTORIAN_OFFER,
    TYPE_MEMORY_BANK,
    TYPE_OTHER(false),
    TYPE_RAIDING_BASE,
    TYPE_SALVAGEABLE(CaptainsLogSettings.SALVAGEABLE.isEnabled()),
    TYPE_SURVEY_MISSION;

    private final boolean isEnabled;

    Types() {
        this(true);
    }

    public static List<ButtonAware> getAll() {
        final List<ButtonAware> types = new LinkedList<>();
        for (final Types type : Types.values()) {
            if (type.isEnabled) {
                types.add(type);
            }
        }
        types.add(Types.TYPE_OTHER);
        return types;
    }

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getTitle() {
        return L10n.exploration(name());
    }

    @Override
    public String getCheckedKey() {
        return getKey(ExplorationConstants.MEMORY_SUFFIX_CHECKED);
    }

    @Override
    public Color getColor() {
        return Misc.getBasePlayerColor();
    }

    @Override
    public String getEnabledKey() {
        return getKey(ExplorationConstants.MEMORY_SUFFIX_ENABLED);
    }

    private String getKey(final String suffix) {
        return MemoryManager.getInstance().key(ExplorationConstants.MEMORY_PREFIX, this, suffix);
    }
}