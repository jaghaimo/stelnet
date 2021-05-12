package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.config.BoardConfig;
import stelnet.config.MarketConfig;
import stelnet.config.ModConfig;
import stelnet.helper.Configurator;

public class StelnetMod extends BaseModPlugin {

    private ModConfig config;
    private BoardConfig boardConfig;
    private MarketConfig marketConfig;

    @Override
    public void beforeGameSave() {
        if (config.isUninstallMod()) {
            uninstall();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        config = ModConfig.getInstance();
        boardConfig = BoardConfig.getInstance();
        marketConfig = MarketConfig.getInstance();
    }

    @Override
    public void onNewGame() {
        onNewGameOrGameLoad();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        onNewGameOrGameLoad();
    }

    private void onNewGameOrGameLoad() {
        if (config.isDevMode()) {
            uninstall();
        }
        install();
    }

    private void install() {
        Configurator.configure(boardConfig, marketConfig);
    }

    private void uninstall() {
        Configurator.uninstall();
    }
}
