package stelnet.util;

import lombok.extern.log4j.Log4j;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.contact.ContactsBoard;
import stelnet.board.query.MarketUpdater;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.ResultIntel;
import stelnet.board.storage.StorageBoard;
import stelnet.board.storage.StorageIntel;
import stelnet.board.storage.StorageListener;
import stelnet.board.viewer.ViewerBoard;

@Log4j
public class ConfigUtils {

    public static void configure() {
        ConfigConstants.init();
    }

    public static void activate() {
        initCommodity(ConfigConstants.HAS_COMMODITIES);
        initContacts(ConfigConstants.HAS_CONTACTS);
        initMarket(ConfigConstants.HAS_MARKET);
        initStorage(ConfigConstants.HAS_STORAGE);
        log.info("Stelnet activated");
    }

    public static void deactivate() {
        initContacts(false);
        initCommodity(false);
        initMarket(false);
        initStorage(false);
        log.info("Stelnet deactivated");
    }

    private static void purgeIntel(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            log.debug("Removing intel " + className);
            IntelUtils.removeAll(className);
        }
    }

    private static void initContacts(boolean hasContacts) {
        if (hasContacts) {
            ContactsBoard.getInstance(ContactsBoard.class);
            log.info("Enabled Contact plugin");
        } else {
            purgeIntel(ContactsBoard.class);
            log.info("Disabled Contact plugin");
        }
    }

    private static void initCommodity(boolean hasCommodities) {
        if (hasCommodities) {
            CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
            board.getRenderableState().getIntelTracker().restore();
            log.info("Enabled Commodity plugin");
        } else {
            purgeIntel(CommodityBoard.class, CommodityIntel.class);
            log.info("Disabled Commodity plugin");
        }
    }

    private static void initMarket(boolean hasMarket) {
        if (hasMarket) {
            QueryBoard.getInstance(QueryBoard.class);
            ViewerBoard.getInstance(ViewerBoard.class);
            log.info("Enabled Market plugin");
        } else {
            purgeIntel(QueryBoard.class, ViewerBoard.class, ResultIntel.class);
            log.info("Disabled Market plugin");
        }
        if (hasMarket && ConfigConstants.AUTO_REFRESH_MARKETS) {
            log.info("Enabled Market updater script");
            MarketUpdater.getInstance();
        }
    }

    private static void initStorage(boolean hasStorage) {
        if (hasStorage) {
            StorageBoard.getInstance(StorageBoard.class);
            StorageListener.register();
            log.info("Enabled Storage plugin");
        } else {
            purgeIntel(StorageBoard.class, StorageIntel.class);
            log.info("Disabled Storage plugin");
        }
    }
}
