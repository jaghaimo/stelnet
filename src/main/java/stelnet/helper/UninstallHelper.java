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

public class UninstallHelper {

    public static void uninstall() {
        purgeIntel(CommodityBoard.class, StorageBoard.class, MarketQueryBoard.class);
        purgeIntel(CommodityIntel.class, StorageIntel.class, MarketResultIntel.class);
        purgeListeners(MonthEndListener.class, StorageListener.class);
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
}
