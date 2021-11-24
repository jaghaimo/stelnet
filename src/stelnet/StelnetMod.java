package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import stelnet.util.ConfigConstants;
import stelnet.util.ConfigUtils;
import stelnet.util.ReportUtils;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void beforeGameSave() {
        if (ConfigConstants.UNINSTALL_MOD) {
            ConfigUtils.deactivate();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        ReportUtils.generate();
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
