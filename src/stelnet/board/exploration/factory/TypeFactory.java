package stelnet.board.exploration.factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.exploration.ExplorationL10n;
import stelnet.settings.CaptainsLogSettings;

public class TypeFactory {

    public static List<ExplorationL10n> getBanks() {
        ExplorationL10n[] bankTypes = {
            ExplorationL10n.BANK_ANY_CACHE,
            ExplorationL10n.BANK_DEBRIS_FIELD,
            ExplorationL10n.BANK_DERELICT_SHIP,
            ExplorationL10n.BANK_DOMAIN_ERA_ENTITY,
            ExplorationL10n.BANK_ORBITAL_HABITAT,
            ExplorationL10n.BANK_RUINS_LOCATION,
            ExplorationL10n.BANK_SURVEY_DATA,
            ExplorationL10n.BANK_OTHER,
        };
        return Arrays.asList(bankTypes);
    }

    public static List<ExplorationL10n> getTypes() {
        List<ExplorationL10n> types = new LinkedList<>();
        types.addAll(
            Arrays.asList(
                ExplorationL10n.TYPE_ANALYZE_MISSION,
                ExplorationL10n.TYPE_HISTORIAN_OFFER,
                ExplorationL10n.TYPE_MEMORY_BANK,
                ExplorationL10n.TYPE_RAIDING_BASE,
                ExplorationL10n.TYPE_SURVEY_MISSION
            )
        );
        if (CaptainsLogSettings.COLONY_STRUCTURES.isEnabled()) {
            types.add(ExplorationL10n.TYPE_COLONY_STRUCTURE);
        }
        if (CaptainsLogSettings.COMM_RELAYS.isEnabled()) {
            types.add(ExplorationL10n.TYPE_COMM_RELAY);
        }
        if (CaptainsLogSettings.RUINS.isEnabled()) {
            types.add(ExplorationL10n.TYPE_ANY_RUINS);
        }
        if (CaptainsLogSettings.SALVAGEABLE.isEnabled()) {
            types.add(ExplorationL10n.TYPE_SALVAGEABLE);
        }
        Collections.sort(types);
        types.add(ExplorationL10n.TYPE_OTHER);
        return types;
    }
}
