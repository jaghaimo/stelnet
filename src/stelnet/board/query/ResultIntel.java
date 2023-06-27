package stelnet.board.query;

import com.fs.starfarer.api.Global;
import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.board.IntelBasePlugin;
import stelnet.settings.Modules;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.property.Size;

@Getter
@Log4j
public class ResultIntel extends IntelBasePlugin {

    private float advancedAmount = 0;
    private final QueryManager queryManager;
    private final ResultSet resultSet;
    private final String tag = ModConstants.TAG_MARKET;

    public ResultIntel(final QueryManager queryManager, final ResultSet resultSet) {
        super(resultSet.getFaction(), resultSet.getToken());
        this.queryManager = queryManager;
        this.resultSet = resultSet;
    }

    @Override
    public boolean isHidden() {
        return Modules.MARKET.isHidden();
    }

    @Override
    public void advance(final float amount) {
        advancedAmount += amount;
        if (advancedAmount > 1) {
            log.debug("Restoring original token");
            setSectorEntityToken(resultSet.getToken());
            Global.getSector().removeTransientScript(this);
            advancedAmount = 0;
        }
    }

    @Override
    public String getIcon() {
        final int results = Math.min(100, resultSet.getResultCount());
        final double rounded = Math.floor(results / 10);
        final String icon = String.format("result_%.0f", rounded);
        return StelnetHelper.getSpriteName(icon);
    }

    @Override
    public boolean runWhilePaused() {
        return true;
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return resultSet.getBoardInfo();
    }

    @Override
    protected List<Renderable> getRenderableList(final Size size) {
        return resultSet.getGroupingStrategy().getView(this, resultSet).create(size);
    }
}
