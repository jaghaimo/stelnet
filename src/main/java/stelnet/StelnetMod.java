package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.commodity.CommodityBoard;
import stelnet.helper.LogHelper;
import stelnet.helper.SettingHelper;
import stelnet.helper.UninstallHelper;
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
        boolean isUninstall = SettingHelper.uninstallMod();
        boolean isDevMode = SettingHelper.isDevMode();
        if (isUninstall || isDevMode) {
            UninstallHelper.uninstall();
            initStorage();
            LogHelper.info("Stelnet uninstalled");
        }
        if (!isUninstall) {
            initCommodity();
            initMarket();
            initStorage();
            LogHelper.info("Initiation complete");
        }
    }

    private void initCommodity() {
        CommodityBoard.getInstance();
    }

    private void initMarket() {
        MarketQueryBoard.getInstance();
        if (SettingHelper.warnAboutEndOfMonth()) {
            MonthEndListener.register();
        } else {
            UninstallHelper.purgeListeners(MonthEndListener.class);
        }
    }

    private void initStorage() {
        StorageBoard.getInstance();
        StorageListener.register();
    }
}
