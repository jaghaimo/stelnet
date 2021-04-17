package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.commodity.CommodityBoard;
import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.LogHelper;
import stelnet.market.MarketQueryBoard;
import stelnet.market.MonthEndListener;
import stelnet.storage.StorageBoard;

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
            purge(CommodityBoard.class, StorageBoard.class, MarketQueryBoard.class);
        }
        CommodityBoard.getInstance();
        StorageBoard.getInstance();
        MarketQueryBoard.getInstance();
        MonthEndListener.register();
        LogHelper.debug("Initiation complete");
    }

    private void purge(Class<?>... classNames) {
        for (Class<?> className : classNames) {
            IntelHelper.purgeIntel(className);
        }
    }
}
