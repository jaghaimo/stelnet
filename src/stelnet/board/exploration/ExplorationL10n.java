package stelnet.board.exploration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lwjgl.input.Keyboard;

@AllArgsConstructor
@NoArgsConstructor
public enum ExplorationL10n implements IdAware {
    BANK_ANY_CACHE,
    BANK_DEBRIS_FIELD,
    BANK_DERELICT_SHIP,
    BANK_DOMAIN_ERA_ENTITY,
    BANK_HABITABLE_WORLD,
    BANK_ORBITAL_HABITAT,
    BANK_OTHER,
    BANK_RUINS_LOCATION,
    BANK_SURVEY_DATA,
    BOARD_DESCRIPTION,
    BOARD_TITLE,
    HEADER_FACTION,
    HEADER_MEMORY_BANK,
    HEADER_TYPE,
    TYPE_ANALYZE_MISSION(Keyboard.KEY_A),
    TYPE_ANY_RUINS(Keyboard.KEY_R),
    TYPE_COLONY_STRUCTURE(Keyboard.KEY_L),
    TYPE_COMM_RELAY(Keyboard.KEY_C),
    TYPE_HISTORIAN_OFFER(Keyboard.KEY_H),
    TYPE_MEMORY_BANK(Keyboard.KEY_M),
    TYPE_OTHER(Keyboard.KEY_O),
    TYPE_RAIDING_BASE(Keyboard.KEY_B),
    TYPE_SALVAGEABLE(Keyboard.KEY_S),
    TYPE_SURVEY_MISSION(Keyboard.KEY_U);

    private int shortcut = 0;

    @Override
    public String getId() {
        return name();
    }

    public int getShortcut() {
        return shortcut;
    }
}
