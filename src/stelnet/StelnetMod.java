package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import stelnet.config.StelnetConfig;
import stelnet.util.ConfigUtils;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void beforeGameSave() {
        if (StelnetConfig.uninstallMod) {
            ConfigUtils.deactivate();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        ConfigUtils.configure();
        if (StelnetConfig.verboseLogging) {
            LogManager.getRootLogger().setLevel(Level.ALL);
        }
    }

    @Override
    public void onNewGame() {
        onGameLoad(true);
    }

    @Override
    public void onGameLoad(boolean newGame) {
        ConfigUtils.activate();
    }
}
