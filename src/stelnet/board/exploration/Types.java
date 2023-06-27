package stelnet.board.exploration;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.settings.CaptainsLogSettings;
import stelnet.util.L10n;

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

    private final boolean isEnabled;

    private Types() {
        this(true);
    }

    @Override
    public String getId() {
        return name();
    }

    public int getShortcut() {
        return 0;
    }

    @Override
    public String getTitle() {
        return L10n.exploration(name());
    }
}
