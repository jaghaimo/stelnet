package stelnet;

import com.fs.starfarer.api.BaseModPlugin;

import stelnet.helper.Installer;
import stelnet.helper.SettingHelper;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void beforeGameSave() {
        boolean isUninstall = SettingHelper.uninstallMod();
        if (isUninstall) {
            uninstall();
        }
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
        boolean isDevMode = SettingHelper.isDevMode();
        if (isDevMode) {
            uninstall();
        }
        install();
    }

    private void install() {
        Installer.install();
    }

    private void uninstall() {
        Installer.uninstall();
    }
}
