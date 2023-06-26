package stelnet.board.exploration;

import stelnet.util.StringsHelper;
import stelnet.util.StringsHelper.Category;

public enum Banks implements IdAware {
    BANK_ANY_CACHE,
    BANK_DEBRIS_FIELD,
    BANK_DERELICT_SHIP,
    BANK_DOMAIN_ERA_ENTITY,
    BANK_ORBITAL_HABITAT,
    BANK_OTHER,
    BANK_RUINS_LOCATION,
    BANK_SURVEY_DATA;

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getFamily() {
        return "BANKS";
    }

    public int getShortcut() {
        return 0;
    }

    @Override
    public String getTitle() {
        return StringsHelper.get(Category.STELNET_EXPLORATION_BOARD, name());
    }
}
