package stelnet.util;

public class ModConstants {

    public static final String STELNET = "stelnet";
    public static final String STELNET_JSON = "stelnet.json";
    public static final String STELNET_LOG = "stelnet.log";

    /**
     * Intel tags, these have to match `assets/data/config/tag_data.json` entries.
     */
    public static final String TAG_COMMODITY = "stelnetCommodity";
    public static final String TAG_MARKET = "stelnetMarket";
    public static final String TAG_QUERY = TAG_MARKET;
    public static final String TAG_STORAGE = "stelnetStorage";
    public static final String TAG_VIEWER = TAG_MARKET;

    /**
     * Memory flags used.
     */
    public static final String EXPLORATION_MANAGE = "$stelnetExplorationManage";
    public static final String MEMORY_IS_CALLING = "$stelnetIsCalling";

    /**
     * CaptainsLog integration.
     */
    public static final String CAPTAINS_LOG_ID = "CaptainsLog";
    public static final String CAPTAINS_LOG_INTEL = "$captainsLogIntel";
}
