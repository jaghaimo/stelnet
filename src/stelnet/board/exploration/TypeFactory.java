package stelnet.board.exploration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import stelnet.settings.CaptainsLogSettings;

public class TypeFactory {

    public static List<IdAware> getBanks() {
        final IdAware[] bankTypes = {
            Banks.BANK_ANY_CACHE,
            Banks.BANK_DEBRIS_FIELD,
            Banks.BANK_DERELICT_SHIP,
            Banks.BANK_DOMAIN_ERA_ENTITY,
            Banks.BANK_ORBITAL_HABITAT,
            Banks.BANK_RUINS_LOCATION,
            Banks.BANK_SURVEY_DATA,
            Banks.BANK_OTHER,
        };
        return Arrays.asList(bankTypes);
    }

    public static List<IdAware> getTypes() {
        final List<IdAware> types = new LinkedList<>();
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
            new Comparator<IdAware>() {
                @Override
                public int compare(final IdAware o1, final IdAware o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            }
        );
        types.add(Types.TYPE_OTHER);
        return types;
    }
}
