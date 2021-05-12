package stelnet.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class MarketConfig extends Config {

    private boolean ignoreStorageInQueries = true;
    private boolean warnAboutEndOfMonth = true;

    public static MarketConfig getInstance() {
        MarketConfig config = new MarketConfig();
        config.setIgnoreStorageInQueries(config.get("ignoreStorageInQueries", config.isIgnoreStorageInQueries()));
        config.setWarnAboutEndOfMonth(config.get("warnAboutEndOfMonth", config.isWarnAboutEndOfMonth()));
        return config;
    }
}
