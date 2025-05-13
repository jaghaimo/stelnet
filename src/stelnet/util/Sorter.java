package stelnet.util;

import java.util.HashMap;
import java.util.Map;


public class Sorter extends Reader {
    private static final String PRIORITY_COMMODITIES_CSV = "data/stelnet/sort/priority_commodities.csv";

    public static Map<String, Integer> getPriorityByCommodityId() {
        Map<String, Integer> priorityByCommodityId = new HashMap<>();
        int i = 0;
        // Match order of priority commodities to the order of IDs in the CSV.
        for (var commodityId : getStrings(PRIORITY_COMMODITIES_CSV, ModConstants.STELNET_ID))
            priorityByCommodityId.put(commodityId, ++i);
        return priorityByCommodityId;
    }
}
