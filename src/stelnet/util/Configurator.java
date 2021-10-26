package stelnet.util;

import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import lombok.extern.log4j.Log4j;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.market.QueryBoard;
import stelnet.board.market.ViewerBoard;
import stelnet.board.storage.StorageBoard;
import stelnet.board.storage.StorageIntel;
import stelnet.board.storage.StorageListener;
import stelnet.config.BoardConfig;
import stelnet.config.ModConfig;

@Log4j
public class Configurator {

    public static void configure() {
        ModConfig.configure();
        BoardConfig.configure();
    }

    public static void install() {
        initCommodity(BoardConfig.hasCommodities);
        initMarket(BoardConfig.hasMarket);
        initStorage(BoardConfig.hasStorage);
        log.info("Stelnet configured");
    }

    public static void uninstall() {
        initCommodity(false);
        initMarket(false);
        initStorage(false);
        log.info("Stelnet uninstalled");
    }

    public static void purgeIntel(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            log.debug("Removing intel " + className);
            IntelManager.purgeIntel(className);
        }
    }

    public static void purgeListeners(Class<?>... classNames) {
        ListenerManagerAPI listenerManagerAPI = Sector.getListenerManager();
        for (Class<?> className : classNames) {
            log.debug("Removing listener " + className);
            listenerManagerAPI.removeListenerOfClass(className);
        }
    }

    private static void initCommodity(boolean hasCommodities) {
        if (hasCommodities) {
            CommodityBoard.getInstance();
            log.info("Enabled Commodity plugin");
        } else {
            purgeIntel(CommodityBoard.class, CommodityIntel.class);
            log.info("Disabled Commodity plugin");
        }
    }

    private static void initMarket(boolean hasMarket) {
        if (hasMarket) {
            QueryBoard.getInstance();
            ViewerBoard.getInstance();
            log.info("Enabled Market plugin");
        } else {
            purgeIntel(QueryBoard.class, ViewerBoard.class);
            log.info("Disabled Market plugin");
        }
    }

    private static void initStorage(boolean hasStorage) {
        if (hasStorage) {
            StorageBoard.getInstance();
            StorageListener.register();
            log.info("Enabled Storage plugin");
        } else {
            purgeIntel(StorageBoard.class, StorageIntel.class);
            purgeListeners(StorageListener.class);
            log.info("Disabled Storage plugin");
        }
    }
}
