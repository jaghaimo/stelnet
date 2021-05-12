package stelnet.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class ModConfig extends Config {

    private boolean isDevMode = false;
    private boolean isUninstallMod = false;

    public static ModConfig getInstance() {
        ModConfig config = new ModConfig();
        config.setDevMode(config.get("devMode", config.isDevMode()));
        config.setUninstallMod(config.get("uninstallMod", config.isUninstallMod()));
        return config;
    }
}
