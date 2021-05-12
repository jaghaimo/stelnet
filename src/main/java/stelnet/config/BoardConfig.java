package stelnet.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class BoardConfig extends Config {

    private boolean hasCommodities = true;
    private boolean hasMarket = true;
    private boolean hasStorage = true;

    public static BoardConfig getInstance() {
        BoardConfig config = new BoardConfig();
        config.setHasCommodities(config.get("hasCommodities", config.isHasCommodities()));
        config.setHasMarket(config.get("hasMarket", config.isHasMarket()));
        config.setHasStorage(config.get("hasStorage", config.isHasStorage()));
        return config;
    }
}
