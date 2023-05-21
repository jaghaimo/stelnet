package stelnet.board.exploration;

import org.lwjgl.input.Keyboard;

public enum ExplorationL10n implements IdAware {
    BANK_DEBRIS_FIELD,
    BANK_DERELICT_SHIP,
    BANK_DOMAIN_ERA_PROBE,
    BANK_EQUIPMENT_CACHE,
    BANK_HABITABLE_WORLD,
    BANK_ORBITAL_HABITAT,
    BANK_OTHER,
    BANK_RUINS_LOCATION,
    BANK_SUPPLY_CACHE,
    BANK_SURVEY_DATA,
    BANK_SURVEY_SHIP,
    BOARD_DESCRIPTION,
    BOARD_TITLE,
    HEADER_FACTION,
    HEADER_MEMORY_BANK,
    HEADER_TYPE,
    TYPE_ANALYZE_MISSION,
    TYPE_COMM_RELAY,
    TYPE_HISTORIAN_OFFER,
    TYPE_MEMORY_BANK,
    TYPE_OTHER,
    TYPE_RAIDING_BASE,
    TYPE_STORY_MISSION,
    TYPE_SURVEY_MISSION;

    @Override
    public String getId() {
        return name();
    }

    public int getShorcut() {
        switch (this) {
            case TYPE_ANALYZE_MISSION:
                return Keyboard.KEY_A;
            case TYPE_COMM_RELAY:
                return Keyboard.KEY_C;
            case TYPE_HISTORIAN_OFFER:
                return Keyboard.KEY_H;
            case TYPE_MEMORY_BANK:
                return Keyboard.KEY_M;
            case TYPE_OTHER:
                return Keyboard.KEY_O;
            case TYPE_RAIDING_BASE:
                return Keyboard.KEY_R;
            case TYPE_STORY_MISSION:
                return Keyboard.KEY_S;
            case TYPE_SURVEY_MISSION:
                return Keyboard.KEY_U;
            default:
                return 0;
        }
    }
}
