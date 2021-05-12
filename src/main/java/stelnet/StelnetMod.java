package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.config.BoardConfig;
import stelnet.config.MarketConfig;
import stelnet.config.ModConfig;
import stelnet.helper.Configurator;

public class StelnetMod extends BaseModPlugin {

    private ModConfig config;

    @Override
    public void beforeGameSave() {
        if (config.isUninstallMod()) {
            uninstall();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        config = ModConfig.getInstance();
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
        BoardConfig boardConfig = BoardConfig.getInstance();
        MarketConfig marketConfig = MarketConfig.getInstance();
        Configurator.configure(boardConfig, marketConfig);
    }

    private void uninstall() {
        Configurator.uninstall();
    }
}
