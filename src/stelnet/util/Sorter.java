package stelnet.util;

import java.util.List;


public class Sorter extends Reader {
    private static final String COMMODITY_IDS = "data/stelnet/commodity_order.csv";

    public static List<String> getPriorityCommodityIds() {
        return getStrings(COMMODITY_IDS, ModConstants.STELNET_ID);
    }
}
