package stelnet.helper;

import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

import stelnet.commodity.CommodityBoard;
import stelnet.commodity.CommodityIntel;
import stelnet.config.BoardConfig;
import stelnet.config.MarketConfig;
import stelnet.config.ModConfig;
import stelnet.market.MarketQueryBoard;
import stelnet.market.MarketResultIntel;
import stelnet.market.MonthEndListener;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageIntel;
import stelnet.storage.StorageListener;

public class Configurator {

    public static void configure() {
        ModConfig.configure();
        BoardConfig.configure();
        MarketConfig.configure();
    }

    public static void install() {
        initCommodity(BoardConfig.hasCommodities);
        initMarket(BoardConfig.hasMarket, MarketConfig.warnAboutEndOfMonth);
        initStorage(BoardConfig.hasStorage);
        LogHelper.debug("Configured");
    }

    public static void uninstall() {
        initCommodity(false);
        initMarket(false, false);
        initStorage(false);
        LogHelper.debug("Uninstalled");
    }

    public static void purgeIntel(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            IntelHelper.purgeIntel(className);
        }
    }

    public static void purgeListeners(Class<?>... classNames) {
        ListenerManagerAPI listenerManagerAPI = GlobalSectorHelper.getListenerManager();
        for (Class<?> className : classNames) {
            LogHelper.debug("Removing listener " + className);
            listenerManagerAPI.removeListenerOfClass(className);
        }
    }

    private static void initCommodity(boolean hasCommodities) {
        if (hasCommodities) {
            CommodityBoard.getInstance();
            LogHelper.info("Enabled Commodity");
        } else {
            purgeIntel(CommodityBoard.class, CommodityIntel.class);
            LogHelper.info("Disabled Commodity");
        }
    }

    private static void initMarket(boolean hasMarket, boolean warnAboutEndOfMonth) {
        if (hasMarket) {
            MarketQueryBoard.getInstance();
            LogHelper.info("Enabled Market");
        } else {
            purgeIntel(MarketQueryBoard.class, MarketResultIntel.class);
            LogHelper.info("Disabled Market");
        }
        boolean needListener = warnAboutEndOfMonth && hasMarket;
        if (needListener) {
            MonthEndListener.register();
            LogHelper.debug("Enabled end-of-month nagging");
        } else {
            purgeListeners(MonthEndListener.class);
            LogHelper.debug("Disabled end-of-month nagging");
        }
    }

    private static void initStorage(boolean hasStorage) {
        if (hasStorage) {
            StorageBoard.getInstance();
            StorageListener.register();
            LogHelper.info("Enabled Storage");
        } else {
            purgeIntel(StorageBoard.class, StorageIntel.class);
            purgeListeners(StorageListener.class);
            LogHelper.info("Disabled Storage");
        }
    }
}
