package stelnet.board.exploration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import stelnet.settings.CaptainsLogSettings;
import stelnet.util.StringsHelper;
import stelnet.util.StringsHelper.Category;

public enum Types implements ButtonAware {
    TYPE_ANALYZE_MISSION,
    TYPE_ANY_RUINS,
    TYPE_COLONY_STRUCTURE,
    TYPE_COMM_RELAY,
    TYPE_HISTORIAN_OFFER,
    TYPE_MEMORY_BANK,
    TYPE_OTHER,
    TYPE_RAIDING_BASE,
    TYPE_SALVAGEABLE,
    TYPE_SURVEY_MISSION;

    public static List<ButtonAware> getAll() {
        final List<ButtonAware> types = new LinkedList<>();
        types.addAll(
            Arrays.asList(
                Types.TYPE_ANALYZE_MISSION,
                Types.TYPE_HISTORIAN_OFFER,
                Types.TYPE_MEMORY_BANK,
                Types.TYPE_RAIDING_BASE,
                Types.TYPE_SURVEY_MISSION
            )
        );
        if (CaptainsLogSettings.COLONY_STRUCTURES.isEnabled()) {
            types.add(Types.TYPE_COLONY_STRUCTURE);
        }
        if (CaptainsLogSettings.COMM_RELAYS.isEnabled()) {
            types.add(Types.TYPE_COMM_RELAY);
        }
        if (CaptainsLogSettings.RUINS.isEnabled()) {
            types.add(Types.TYPE_ANY_RUINS);
        }
        if (CaptainsLogSettings.SALVAGEABLE.isEnabled()) {
            types.add(Types.TYPE_SALVAGEABLE);
        }
        Collections.sort(
            types,
            new Comparator<ButtonAware>() {
                @Override
                public int compare(final ButtonAware o1, final ButtonAware o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            }
        );
        types.add(Types.TYPE_OTHER);
        return types;
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
        return StringsHelper.get(Category.STELNET_EXPLORATION_BOARD, name());
    }
}
