package stelnet.board.query;

import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.util.SectorUtils;
import stelnet.util.SettingsUtils;
import stelnet.util.TagConstants;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.property.Size;

@Getter
public class ResultIntel extends BaseIntel {

    private float advancedAmount = 0;
    private final QueryManager queryManager;
    private final ResultSet resultSet;
    private final String tag = TagConstants.MARKET;

    public ResultIntel(QueryManager queryManager, ResultSet resultSet) {
        super(resultSet.getClaimingFaction(), resultSet.getToken());
        this.queryManager = queryManager;
        this.resultSet = resultSet;
    }

    @Override
    public void advance(float amount) {
        advancedAmount += amount;
        if (advancedAmount > 1) {
            setSectorEntityToken(resultSet.getToken());
            SectorUtils.removeScript(this);
            advancedAmount = 0;
        }
    }

    @Override
    public String getIcon() {
        int results = resultSet.getResultNumber();
        if (results > 30) {
            return SettingsUtils.getSpriteName("high");
        }
        if (results > 10) {
            return SettingsUtils.getSpriteName("medium");
        }
        return SettingsUtils.getSpriteName("low");
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
        return new ResultView(this, resultSet).create(size);
    }
}
