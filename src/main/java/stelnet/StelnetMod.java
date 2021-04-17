package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerUtil;

import stelnet.commodity.CommodityBoard;
import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.LogHelper;
import stelnet.market.MarketQueryBoard;
import stelnet.market.MonthEndListener;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageListener;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void onNewGame() {
        LogHelper.debug("Initiating new game");
        init();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        LogHelper.debug("Initiating game load");
        init();
    }

    private void init() {
        if (GlobalHelper.isDevMode()) {
            purgeIntel(CommodityBoard.class, StorageBoard.class, MarketQueryBoard.class);
            purgeListeners(MonthEndListener.class, StorageListener.class);
        }
        initCommodity();
        initMarket();
        initStorage();
        LogHelper.debug("Initiation complete");
    }

    private void initCommodity() {
        CommodityBoard.getInstance();
    }

    private void initMarket() {
        MarketQueryBoard.getInstance();
        MonthEndListener.register();
    }

    private void initStorage() {
        StorageBoard.getInstance();
        StorageListener.register();
    }

    private void purgeIntel(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            IntelHelper.purgeIntel(className);
        }
    }

    private void purgeListeners(Class<?>... classNames) {
        ListenerManagerAPI listenerManagerAPI = GlobalHelper.getListenerManager();
        for (Class<?> className : classNames) {
            listenerManagerAPI.removeListenerOfClass(className);
        }
    }
}
