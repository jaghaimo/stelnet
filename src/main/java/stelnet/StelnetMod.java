package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.commodity.CommodityBoard;
import stelnet.market.MarketQueryBoard;
import stelnet.market.MonthEndListener;
import stelnet.storage.StorageBoard;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void onNewGame() {
        init();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        init();
    }

    private void init() {
        CommodityBoard.getInstance();
        StorageBoard.getInstance();
        MarketQueryBoard.getInstance();
        MonthEndListener.register();
    }
}
