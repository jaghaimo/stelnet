package stelnet.util;

import java.io.IOException;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

@Log4j
public class ConfigConstants {

    private static transient JSONObject cachedSettings = null;

    public static transient boolean AUTO_REFRESH_MARKETS = true;
    public static transient boolean UNINSTALL_MOD = false;

    public static transient boolean HAS_COMMODITIES = true;
    public static transient boolean HAS_CONTACTS = true;
    public static transient boolean HAS_MARKET = true;
    public static transient boolean HAS_STORAGE = true;

    public static transient boolean CONTACTS_ADD_SEBESTYEN = true;

    public static transient boolean QUERY_USE_OPEN_MARKET = true;
    public static transient boolean QUERY_USE_MILITARY_MARKET = true;
    public static transient boolean QUERY_USE_BLACK_MARKET = true;
    public static transient boolean QUERY_USE_CUSTOM_MARKETS = false;

    public static transient boolean VIEWER_USE_OPEN_MARKET = true;
    public static transient boolean VIEWER_USE_MILITARY_MARKET = true;
    public static transient boolean VIEWER_USE_BLACK_MARKET = true;
    public static transient boolean VIEWER_USE_CUSTOM_MARKETS = false;

    public static void init() {
        AUTO_REFRESH_MARKETS = get("autoRefreshMarkets", AUTO_REFRESH_MARKETS);
        UNINSTALL_MOD = get("uninstallMod", UNINSTALL_MOD);

        HAS_COMMODITIES = get("hasCommodities", HAS_COMMODITIES);
        HAS_CONTACTS = get("hasContacts", HAS_CONTACTS);
        HAS_MARKET = get("hasMarket", HAS_MARKET);
        HAS_STORAGE = get("hasStorage", HAS_STORAGE);

        CONTACTS_ADD_SEBESTYEN = get("contactsAddSebestyen", CONTACTS_ADD_SEBESTYEN);

        QUERY_USE_OPEN_MARKET = get("queryUseOpenMarket", QUERY_USE_OPEN_MARKET);
        QUERY_USE_MILITARY_MARKET = get("queryUseMilitaryMarket", QUERY_USE_MILITARY_MARKET);
        QUERY_USE_BLACK_MARKET = get("queryUseBlackMarket", QUERY_USE_BLACK_MARKET);
        QUERY_USE_CUSTOM_MARKETS = get("queryUseCustomMarkets", QUERY_USE_CUSTOM_MARKETS);

        VIEWER_USE_OPEN_MARKET = get("viewerUseOpenMarket", VIEWER_USE_OPEN_MARKET);
        VIEWER_USE_MILITARY_MARKET = get("viewerUseMilitaryMarket", VIEWER_USE_MILITARY_MARKET);
        VIEWER_USE_BLACK_MARKET = get("viewerUseBlackMarket", VIEWER_USE_BLACK_MARKET);
        VIEWER_USE_CUSTOM_MARKETS = get("viewerUseCustomMarkets", VIEWER_USE_CUSTOM_MARKETS);
    }

    protected static JSONObject load() throws JSONException, IOException {
        if (cachedSettings == null) {
            log.debug("Reading config file");
            cachedSettings = Reader.loadJson(ModConstants.STELNET_JSON, ModConstants.STELNET);
        }
        return cachedSettings;
    }

    @SuppressWarnings("unchecked")
    protected static <T> T get(String key, T defaultValue) {
        try {
            Object value = load().get(key);
            log.debug("Returning read value for '" + key + "' - " + value);
            return (T) value;
        } catch (Exception exception) {
            log.warn("Returning default value for '" + key + "' - " + defaultValue, exception);
            return defaultValue;
        }
    }
}
