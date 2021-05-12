package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.config.BoardConfig;
import stelnet.config.MarketConfig;
import stelnet.config.ModConfig;
import stelnet.helper.Configurator;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void beforeGameSave() {
        if (ModConfig.uninstallMod) {
            uninstall();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        ModConfig.configure();
        BoardConfig.configure();
        MarketConfig.configure();
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
        install();
    }

    private void install() {
        Configurator.configure();
    }

    private void uninstall() {
        Configurator.uninstall();
    }
}
