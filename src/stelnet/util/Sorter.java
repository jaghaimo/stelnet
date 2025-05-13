package stelnet.util;

import java.util.HashMap;
import java.util.Map;


public class Sorter extends Reader {
    private static final String COMMODITY_IDS = "data/stelnet/commodity_order.csv";

    public static Map<String, Integer> getPriorityCommodityIds() {
        Map<String, Integer> priorityByCommodityId = new HashMap<>();
        int i = 0;
        // Match order of priority commodities to the order of IDs in the CSV.
        for (var commodityId : getStrings(COMMODITY_IDS, ModConstants.STELNET_ID))
            priorityByCommodityId.put(commodityId, ++i);
        return priorityByCommodityId;
    }
}
