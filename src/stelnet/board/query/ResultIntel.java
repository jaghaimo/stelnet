package stelnet.board.query;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.util.SectorUtils;
import stelnet.util.TagConstants;
import uilib.Renderable;
import uilib.property.Size;

@Getter
@Log4j
public class ResultIntel extends BaseIntel {

    private final String resultTitle;
    private final QueryManager queryManager;
    private final ResultMarketMap resultSet;

    @Getter
    private final String tag = TagConstants.MARKET;

    public ResultIntel(QueryManager queryManager, ResultMarketMap resultSet, StarSystemAPI system) {
        super(SectorUtils.getPlayerFaction(), extractSectorEntityToken(resultSet, system));
        this.queryManager = queryManager;
        this.resultSet = resultSet;
        this.resultTitle = extractResultTitle(resultSet, system);
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(
            resultTitle,
            "Matching results", // todo l10n
            resultSet.getResultNumber(),
            "Matching markets", // todo l10n
            resultSet.getMarketNumber()
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

    private static String extractResultTitle(ResultMarketMap resultSet, StarSystemAPI system) {
        if (system != null) {
            return system.getName();
        }
        for (MarketAPI market : resultSet.keySet()) {
            return market.getName();
        }
        // This should never happen
        log.error("Couldn't determine ResultIntel's title");
        return "Unknown";
    }

    private static SectorEntityToken extractSectorEntityToken(ResultMarketMap resultSet, StarSystemAPI system) {
        if (system != null) {
            return system.getCenter();
        }
        for (MarketAPI market : resultSet.keySet()) {
            return market.getPrimaryEntity();
        }
        // This should never happen
        log.error("Couldn't determine ResultIntel's token");
        return null;
    }
}
