package stelnet.util;

import java.util.List;

/**
 * Provides access to user/mod inclusions.
 */
public class Includer extends Reader {

    private static final String ABANDONED_STATION_IDS = "data/stelnet/include/abandoned_stations.csv";

    public static List<String> getAbandonedStations() {
        return getStrings(ABANDONED_STATION_IDS);
    }
}
