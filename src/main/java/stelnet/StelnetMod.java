package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.config.ModConfig;
import stelnet.helper.Configurator;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void beforeGameSave() {
        if (ModConfig.uninstallMod) {
            Configurator.uninstall();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        Configurator.configure();
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
        Configurator.install();
    }
}
