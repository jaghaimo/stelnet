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
    public void beforeGameSave() {
        boolean isUninstall = SettingHelper.uninstallMod();
        if (isUninstall) {
            uninstall();
        }
    }

    @Override
    public void onNewGame() {
        init();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        init();
    }

    private void init() {
        boolean isDevMode = SettingHelper.isDevMode();
        if (isDevMode) {
            uninstall();
        }
        initCommodity();
        initMarket();
        initStorage();
        LogHelper.debug("Initiated");
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

    private void uninstall() {
        UninstallHelper.uninstall();
        LogHelper.debug("Uninstalled");
    }
}
