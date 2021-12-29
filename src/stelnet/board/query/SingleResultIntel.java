package stelnet.board.query;

import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.BoardInfo;
import stelnet.util.ModConstants;
import uilib.RenderableIntelInfo;

@Getter
public class SingleResultIntel extends BaseIntel {

    private final QueryManager queryManager;
    private final Result result;
    private final String tag = ModConstants.TAG_MARKET;

    public SingleResultIntel(QueryManager queryManager, Result result) {
        super(result.getMarket().getFaction(), result.getMarket().getPrimaryEntity());
        this.queryManager = queryManager;
        this.result = result;
    }

    @Override
    public String getIcon() {
        return result.getIcon();
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        String title = getLocationNameWithSystem();
        String description = String.format("%s - %s", result.getType(), result.getName());
        return new BoardInfo(title, description);
    }
}
