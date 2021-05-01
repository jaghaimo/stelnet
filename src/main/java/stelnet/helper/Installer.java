package stelnet.helper;

import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

import stelnet.commodity.CommodityBoard;
import stelnet.commodity.CommodityIntel;
import stelnet.market.MarketQueryBoard;
import stelnet.market.MarketResultIntel;
import stelnet.market.MonthEndListener;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageIntel;
import stelnet.storage.StorageListener;

public class Installer {

    public static void install() {
        initCommodity();
        initMarket();
        initStorage();
        LogHelper.debug("Initiated");
    }

    public static void uninstall() {
        purgeIntel(CommodityBoard.class, StorageBoard.class, MarketQueryBoard.class);
        purgeIntel(CommodityIntel.class, StorageIntel.class, MarketResultIntel.class);
        purgeListeners(MonthEndListener.class, StorageListener.class);
        LogHelper.debug("Uninstalled");
    }

    public static void purgeIntel(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            IntelHelper.purgeIntel(className);
        }
    }

    public static void purgeListeners(Class<?>... classNames) {
        ListenerManagerAPI listenerManagerAPI = GlobalHelper.getListenerManager();
        for (Class<?> className : classNames) {
            listenerManagerAPI.removeListenerOfClass(className);
        }
    }

    private static void initCommodity() {
        CommodityBoard.getInstance();
    }

    private static void initMarket() {
        MarketQueryBoard.getInstance();
        if (SettingHelper.warnAboutEndOfMonth()) {
            MonthEndListener.register();
        } else {
            purgeListeners(MonthEndListener.class);
        }
    }

    private static void initStorage() {
        StorageBoard.getInstance();
        StorageListener.register();
    }
}
