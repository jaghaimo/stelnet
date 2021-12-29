package stelnet.board.query;

import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.BaseIntel;
import stelnet.util.ModConstants;
import stelnet.util.SectorUtils;
import stelnet.util.SettingsUtils;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.property.Size;

@Getter
@Log4j
public class MultiResultIntel extends BaseIntel {

    private float advancedAmount = 0;
    private final QueryManager queryManager;
    private final ResultSet resultSet;
    private final String tag = ModConstants.TAG_MARKET;

    public MultiResultIntel(QueryManager queryManager, ResultSet resultSet) {
        super(resultSet.getFaction(), resultSet.getToken());
        this.queryManager = queryManager;
        this.resultSet = resultSet;
    }

    @Override
    public void advance(float amount) {
        advancedAmount += amount;
        if (advancedAmount > 1) {
            log.debug("Restoring original token");
            setSectorEntityToken(resultSet.getToken());
            SectorUtils.removeTransientScript(this);
            advancedAmount = 0;
        }
    }

    @Override
    public String getIcon() {
        int results = Math.min(100, resultSet.getResultNumber());
        double rounded = Math.floor(results / 10);
        String icon = String.format("result_%.0f", rounded);
        return SettingsUtils.getSpriteName(icon);
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
    protected List<Renderable> getRenderableList(Size size) {
        return new MultiResultView(this, resultSet).create(size);
    }
}
