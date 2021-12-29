package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Set;
import stelnet.BoardInfo;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.Result;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.ResultSet;
import stelnet.util.IntelUtils;
import stelnet.util.L10n;
import uilib.RenderableIntelInfo;

public enum GroupingStrategy {
    NO_GROUPING {
        @Override
        public void createIntel(QueryManager manager, ResultSet resultSet) {
            for (Result result : resultSet.getResultSet()) {
                ResultSet newResultSet = new ResultSet(this, result.getMarket());
                newResultSet.add(result);
                super.createIntel(manager, newResultSet);
            }
        }

        @Override
        public GroupingData getGroupingData(ResultSet resultSet) {
            MarketAPI market = resultSet.getMarket();
            GroupingData data = super.getGroupingData(resultSet);
            Result result = resultSet.getResultSet().iterator().next();
            String title = String.format("%s - %s", result.getType(), result.getName());
            RenderableIntelInfo info = new BoardInfo(title, getName(market));
            return new GroupingData(info, data.getFaction(), null, data.getToken());
        }
    },
    BY_MARKET,
    BY_SYSTEM {
        @Override
        public GroupingData getGroupingData(ResultSet resultSet) {
            StarSystemAPI system = resultSet.getSystem();
            if (system == null) {
                return super.getGroupingData(resultSet);
            }
            Set<MarketAPI> marketSet = resultSet.getMarketSet();
            RenderableIntelInfo info = new BoardInfo(
                system.getName(),
                L10n.get(QueryL10n.RESULTS_IN_SYSTEM, resultSet.getResultNumber(), marketSet.size())
            );
            MarketAPI market = resultSet.getMarket();
            FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
            return new GroupingData(info, faction, system.getId(), system.getCenter());
        }
    };

    public void createIntel(QueryManager manager, ResultSet resultSet) {
        ResultIntel intel = new ResultIntel(manager, resultSet);
        IntelUtils.add(intel, true);
    }

    public GroupingData getGroupingData(ResultSet resultSet) {
        MarketAPI market = resultSet.getMarket();
        RenderableIntelInfo info = new BoardInfo(
            getName(market),
            L10n.get(QueryL10n.RESULTS_IN_MARKET, resultSet.getResultNumber())
        );
        return new GroupingData(info, market.getFaction(), market.getId(), market.getPrimaryEntity());
    }

    protected String getName(MarketAPI market) {
        // todo: l10n
        return String.format("%s, %s", market.getName(), market.getFaction().getDisplayName());
    }
}
