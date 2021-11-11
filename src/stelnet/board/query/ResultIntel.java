package stelnet.board.query;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.util.SectorUtils;
import stelnet.util.TagConstants;
import uilib.Renderable;
import uilib.property.Size;

@Getter
public class ResultIntel extends BaseIntel {

    private final QueryManager queryManager;
    private final ResultSet resultSet;

    @Getter
    private final String tag = TagConstants.MARKET;

    public ResultIntel(QueryManager queryManager, ResultSet resultSet, StarSystemAPI system) {
        super(SectorUtils.getPlayerFaction(), resultSet.getSystemToken());
        this.queryManager = queryManager;
        this.resultSet = resultSet;
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(
            resultSet.getSystemName(),
            "Matching results", // todo l10n
            String.valueOf(resultSet.getResultNumber()),
            "Matching markets", // todo l10n
            String.valueOf(resultSet.getMarketNumber())
        );
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        // todo
        return Collections.emptyList();
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }
}
