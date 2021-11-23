package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import stelnet.config.StelnetConfig;
import stelnet.util.ConfigUtils;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void afterGameSave() {
        onGameLoad(false);
    }

    @Override
    public void beforeGameSave() {
        if (StelnetConfig.uninstallMod) {
            ConfigUtils.deactivate();
        }
    }

    @Override
    public void onNewGame() {
        onGameLoad(true);
    }

    @Override
    public void onGameLoad(boolean newGame) {
        ConfigUtils.configure();
        ConfigUtils.activate();
    }
}
