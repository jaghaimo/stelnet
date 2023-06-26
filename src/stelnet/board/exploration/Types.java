package stelnet.board.exploration;

import stelnet.util.StringsHelper;
import stelnet.util.StringsHelper.Category;

public enum Types implements IdAware {
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

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getFamily() {
        return "TYPES";
    }

    public int getShortcut() {
        return 0;
    }

    @Override
    public String getTitle() {
        return StringsHelper.get(Category.STELNET_EXPLORATION_BOARD, name());
    }
}
