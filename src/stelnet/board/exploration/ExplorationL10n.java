package stelnet.board.exploration;

public enum ExplorationL10n implements IdAware {
    BANK_ANY_CACHE,
    BANK_DEBRIS_FIELD,
    BANK_DERELICT_SHIP,
    BANK_DOMAIN_ERA_ENTITY,
    BANK_ORBITAL_HABITAT,
    BANK_OTHER,
    BANK_RUINS_LOCATION,
    BANK_SURVEY_DATA,
    BOARD_DESCRIPTION,
    BOARD_TITLE,
    HEADER_FACTION,
    HEADER_MEMORY_BANK,
    HEADER_TYPE,
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

    public int getShortcut() {
        return 0;
    }
}
