package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Set;
import stelnet.BoardInfo;
import stelnet.board.query.MultiResultIntel;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;
import stelnet.board.query.SingleResultIntel;
import stelnet.util.IntelUtils;
import stelnet.util.L10n;
import uilib.RenderableIntelInfo;

public enum GroupingStrategy {
    NO_GROUPING {
        @Override
        public void createIntel(QueryManager manager, ResultSet resultSet) {
            for (Result result : resultSet.getResultSet()) {
                SingleResultIntel intel = new SingleResultIntel(manager, result);
                IntelUtils.add(intel, true);
            }
        }

        @Override
        public GroupingData getGroupingData(ResultSet resultSet) {
            GroupingData data = super.getGroupingData(resultSet);
            return new GroupingData(data.getInfo(), data.getFaction(), null, data.getToken());
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
                L10n.get(QueryL10n.RESULTS_IN_SYSTEM, resultSet.getResultCount(), marketSet.size())
            );
            MarketAPI market = resultSet.getMarket();
            FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
            return new GroupingData(info, faction, system.getId(), system.getCenter());
        }
    };

    public void createIntel(QueryManager manager, ResultSet resultSet) {
        MultiResultIntel intel = new MultiResultIntel(manager, resultSet);
        IntelUtils.add(intel, true);
    }

    public GroupingData getGroupingData(ResultSet resultSet) {
        MarketAPI market = resultSet.getMarket();
        RenderableIntelInfo info = new BoardInfo(
            L10n.get(QueryL10n.RESULTS_MARKET_SYSTEM, market.getName(), market.getFaction().getDisplayName()),
            L10n.get(QueryL10n.RESULTS_IN_MARKET, resultSet.getResultCount())
        );
        return new GroupingData(info, market.getFaction(), market.getId(), market.getPrimaryEntity());
    }
}
