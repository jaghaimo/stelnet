package stelnet.board.query;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.BoardInfo;
import stelnet.util.SectorUtils;
import stelnet.util.SettingsUtils;
import stelnet.util.TagConstants;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.property.Size;

@Getter
public class ResultIntel extends BaseIntel {

    private final QueryManager queryManager;
    private final ResultSet resultSet;

    @Getter
    private final String tag = TagConstants.MARKET;

    public ResultIntel(QueryManager queryManager, ResultSet resultSet) {
        super(SectorUtils.getPlayerFaction(), resultSet.getSystemToken());
        this.queryManager = queryManager;
        this.resultSet = resultSet;
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
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(
            resultSet.getSystemName(),
            String.format("Found %d results in %d markets.", resultSet.getResultNumber(), resultSet.getMarketNumber())
        );
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Collections.emptyList();
    }
}
