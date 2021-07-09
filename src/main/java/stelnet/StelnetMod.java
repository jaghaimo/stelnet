package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

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
        if (ModConfig.verboseLogging) {
            LogManager.getRootLogger().setLevel(Level.ALL);
        }
    }

    @Override
    public void onNewGame() {
        Configurator.install();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        Configurator.install();
    }
}
