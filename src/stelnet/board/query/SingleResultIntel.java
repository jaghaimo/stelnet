package stelnet.board.query;

import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SectorUtils;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.property.Size;

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
    public void advance(float amount) {
        SectorUtils.removeTransientScript(this);
    }

    @Override
    public String getIcon() {
        return result.getIcon();
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(
            getLocationNameWithSystem(),
            L10n.get(QueryL10n.RESULTS_TYPE_NAME, result.getType(), result.getName())
        );
    }

    @Override
    protected List<Renderable> getRenderableList(Size size) {
        return new SingleResultView(this, result).create(size);
    }
}
