package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignUIAPI;
import com.fs.starfarer.api.campaign.CoreUITabId;
import java.awt.event.KeyEvent;
import lombok.extern.log4j.Log4j;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.contact.ContactsBoard;
import stelnet.board.contact.SebestyenContactIntel;
import stelnet.board.contact.SebestyenContactMaker;
import stelnet.board.exploration.ExplorationBoard;
import stelnet.board.query.MarketUpdater;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.provider.DmodProvider;
import stelnet.board.query.provider.FactionProvider;
import stelnet.board.query.provider.ItemProvider;
import stelnet.board.query.provider.MarketProvider;
import stelnet.board.query.provider.ShipProvider;
import stelnet.board.query.provider.SkillProvider;
import stelnet.board.storage.StorageBoard;
import stelnet.board.storage.StorageIntel;
import stelnet.board.storage.StorageUpdater;
import stelnet.board.trade.TradeBoard;
import stelnet.board.viewer.ViewerBoard;

@Log4j
public class Configurator {

    public static void activate() {
        initCommodity(ModSettings.has(ModSettings.COMMODITIES));
        initContacts(ModSettings.has(ModSettings.CONTACTS));
        initExploration(ModSettings.has(ModSettings.EXPLORATION));
        initMarket(ModSettings.has(ModSettings.MARKET));
        initStorage(ModSettings.has(ModSettings.STORAGE));
        log.info("Stelnet activated");
    }

    public static void deactivate(boolean skipUiReset) {
        resetIntelUi(skipUiReset);
        initContacts(false);
        initCommodity(false);
        initExploration(false);
        initMarket(false);
        initStorage(false);
        resetIntelUi(skipUiReset);
        Configurator.resetCache();
        log.info("Stelnet deactivated");
    }

    public static void resetCache() {
        DmodProvider.reset();
        FactionProvider.reset();
        ItemProvider.reset();
        MarketProvider.reset();
        ShipProvider.reset();
        SkillProvider.reset();
    }

    private static void purgeIntel(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            log.debug("Removing intel " + className);
            StelnetHelper.removeIntel(className);
        }
    }

    private static void initContacts(boolean hasContacts) {
        if (hasContacts) {
            ContactsBoard.getInstance(ContactsBoard.class);
            SebestyenContactMaker.register();
            log.info("Enabled Contacts module");
        } else {
            purgeIntel(ContactsBoard.class, SebestyenContactIntel.class);
            log.info("Disabled Contacts module");
        }
    }

    private static void initCommodity(boolean hasCommodities) {
        if (hasCommodities) {
            CommodityBoard.getInstance(CommodityBoard.class).restore();
            // TradeBoard.getInstance(TradeBoard.class);
            log.info("Enabled Commodity module");
        } else {
            purgeIntel(CommodityBoard.class, CommodityIntel.class, TradeBoard.class);
            log.info("Disabled Commodity plugin");
        }
    }

    private static void initExploration(boolean hasExploration) {
        if (hasExploration) {
            ExplorationBoard.getInstance();
            log.info("Enabled Exploration module");
        } else {
            purgeIntel(ExplorationBoard.class);
            log.info("Disabled Exploration plugin");
        }
    }

    private static void initMarket(boolean hasMarket) {
        if (hasMarket) {
            QueryBoard.getInstance(QueryBoard.class);
            ViewerBoard.getInstance(ViewerBoard.class);
            log.info("Enabled Market module");
        } else {
            purgeIntel(QueryBoard.class, ViewerBoard.class, ResultIntel.class);
            log.info("Disabled Market module");
        }
        if (hasMarket && ModSettings.has(ModSettings.AUTO_REFRESH_MARKETS)) {
            MarketUpdater.register();
        } else {
            MarketUpdater.unregister();
        }
    }

    private static void initStorage(boolean hasStorage) {
        if (hasStorage) {
            StorageBoard.getInstance(StorageBoard.class);
            log.info("Enabled Storage module");
            StorageUpdater.register();
        } else {
            purgeIntel(StorageBoard.class, StorageIntel.class);
            log.info("Disabled Storage module");
            StorageUpdater.unregister();
        }
    }

    private static void resetIntelUi(boolean skipUiReset) {
        if (skipUiReset) {
            return;
        }
        CampaignUIAPI campaignUi = Global.getSector().getCampaignUI();
        if (campaignUi == null) {
            return;
        }
        campaignUi.showCoreUITab(CoreUITabId.INTEL, null);
        StelnetHelper.sendKey(KeyEvent.VK_ESCAPE);
    }
}
