package stelnet.util;

import lombok.extern.log4j.Log4j;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.ResultIntel;
import stelnet.board.storage.StorageBoard;
import stelnet.board.storage.StorageIntel;
import stelnet.board.viewer.ViewerBoard;
import stelnet.config.BoardConfig;
import stelnet.config.ModConfig;
import stelnet.listener.MarketUpdateScript;
import stelnet.listener.StorageListener;

@Log4j
public class ConfigUtils {

    public static void configure() {
        ModConfig.configure();
        BoardConfig.configure();
    }

    public static void activate() {
        initCommodity(BoardConfig.HAS_COMMODITIES);
        initMarket(BoardConfig.HAS_MARKET);
        initStorage(BoardConfig.HAS_STORAGE);
        log.info("Stelnet activated");
    }

    public static void deactivate() {
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

    private static void initCommodity(boolean hasCommodities) {
        if (hasCommodities) {
            CommodityBoard.getInstance(CommodityBoard.class);
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
            SectorUtils.addTransientScript(new MarketUpdateScript());
            log.info("Enabled Market plugin");
        } else {
            purgeIntel(QueryBoard.class, ViewerBoard.class, ResultIntel.class);
            log.info("Disabled Market plugin");
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
