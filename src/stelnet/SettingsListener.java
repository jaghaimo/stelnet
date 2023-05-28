package stelnet;

import com.fs.starfarer.api.GameState;
import com.fs.starfarer.api.Global;
import lombok.extern.log4j.Log4j;
import lunalib.lunaSettings.LunaSettings;
import lunalib.lunaSettings.LunaSettingsListener;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.commodity.table.ProfitTableContent;
import stelnet.board.commodity.table.ProfitTableRow;
import stelnet.board.contact.ContactsBoard;
import stelnet.board.contact.SebestyenContactIntel;
import stelnet.board.contact.SebestyenContactMaker;
import stelnet.board.exploration.ExplorationBoard;
import stelnet.board.query.MarketUpdater;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.provider.DmodProvider;
import stelnet.board.query.provider.FactionProvider;
import stelnet.board.query.provider.ItemQueryProvider;
import stelnet.board.query.provider.PeopleQueryProvider;
import stelnet.board.query.provider.ShipQueryProvider;
import stelnet.board.query.provider.SkillProvider;
import stelnet.board.storage.StorageBoard;
import stelnet.board.storage.StorageIntel;
import stelnet.board.storage.StorageUpdater;
import stelnet.board.trade.TradeBoard;
import stelnet.board.viewer.ViewerBoard;
import stelnet.settings.BooleanSettings;
import stelnet.settings.IntSettings;
import stelnet.settings.Modules;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;

@Log4j
public class SettingsListener implements LunaSettingsListener {

    public static void register() {
        LunaSettings.addSettingsListener(new SettingsListener());
    }

    @Override
    public void settingsChanged(String modId) {
        if (!ModConstants.STELNET_ID.equals(modId)) {
            return;
        }
        if (Global.getCurrentState().equals(GameState.TITLE)) {
            return;
        }
        resetCache();
        initCommodity(Modules.hasCommodities());
        initContacts(Modules.hasContacts());
        initExploration(Modules.hasExploration());
        initMarket(Modules.hasMarket());
        initStorage(Modules.hasStorage());
        if (BooleanSettings.UNINSTALL.get()) {
            log.info("Stelnet uninstalled");
        } else {
            log.info("Stelnet configured");
        }
    }

    public static void resetCache() {
        DmodProvider.resetCache();
        FactionProvider.resetCache();
        SkillProvider.resetCache();
        ItemQueryProvider.resetCache();
        PeopleQueryProvider.resetCache();
        ShipQueryProvider.resetCache();
    }

    private void purgeIntel(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            log.debug("Removing intel " + className);
            StelnetHelper.removeIntel(className);
        }
    }

    private void initContacts(boolean hasContacts) {
        if (hasContacts) {
            StelnetHelper.getInstance(ContactsBoard.class);
            SebestyenContactMaker.register();
            log.info("Enabled Contacts module");
        } else {
            purgeIntel(ContactsBoard.class, SebestyenContactIntel.class);
            log.info("Disabled Contacts module");
        }
    }

    private void initCommodity(boolean hasCommodities) {
        if (hasCommodities) {
            StelnetHelper.getInstance(CommodityBoard.class).restore();
            // TradeBoard.getInstance(TradeBoard.class);
            log.info("Enabled Commodity module");
            ProfitTableContent.MAX_ROWS = IntSettings.COMMODITY_PROFIT_ROW_NUMBER.get();
            ProfitTableContent.MINIMUM_PROFIT_VALUE = IntSettings.COMMODITY_PROFIT_MIN_PROFIT.get();
            ProfitTableRow.MINIMUM_QUANTITY = IntSettings.COMMODITY_PROFIT_MIN_QUANTITY.get();
        } else {
            purgeIntel(CommodityBoard.class, CommodityIntel.class, TradeBoard.class);
            log.info("Disabled Commodity module");
        }
    }

    private void initExploration(boolean hasExploration) {
        if (hasExploration) {
            StelnetHelper.getInstance(ExplorationBoard.class);
            log.info("Enabled Exploration module");
        } else {
            purgeIntel(ExplorationBoard.class);
            log.info("Disabled Exploration module");
        }
    }

    private void initMarket(boolean hasMarket) {
        if (hasMarket) {
            StelnetHelper.getInstance(QueryBoard.class);
            StelnetHelper.getInstance(ViewerBoard.class);
            log.info("Enabled Market module");
        } else {
            purgeIntel(QueryBoard.class, ViewerBoard.class, ResultIntel.class);
            log.info("Disabled Market module");
        }
        if (hasMarket && BooleanSettings.MARKET_AUTO_REFRESH.get()) {
            MarketUpdater.register();
        } else {
            MarketUpdater.unregister();
        }
    }

    private void initStorage(boolean hasStorage) {
        if (hasStorage) {
            StelnetHelper.getInstance(StorageBoard.class);
            log.info("Enabled Storage module");
            StorageUpdater.register();
        } else {
            purgeIntel(StorageBoard.class, StorageIntel.class);
            log.info("Disabled Storage module");
            StorageUpdater.unregister();
        }
    }
}
