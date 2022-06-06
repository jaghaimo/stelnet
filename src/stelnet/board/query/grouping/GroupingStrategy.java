package stelnet.board.query.grouping;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Set;
import stelnet.board.BoardRenderableInfo;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.ResultSet;
import stelnet.board.query.ResultView;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntelInfo;

public enum GroupingStrategy {
    BY_MARKET,
    BY_SYSTEM {
        @Override
        public GroupingData getGroupingData(ResultSet resultSet) {
            StarSystemAPI system = resultSet.getSystem();
            if (system == null) {
                return super.getGroupingData(resultSet);
            }
            Set<MarketAPI> marketSet = resultSet.getMarketSet();
            RenderableIntelInfo info = new BoardRenderableInfo(
                system.getName(),
                L10n.get(QueryL10n.RESULTS_IN_SYSTEM, resultSet.getResultCount(), marketSet.size())
            );
            MarketAPI market = resultSet.getMarket();
            FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
            return new GroupingData(info, faction, system.getId(), system.getCenter());
        }

        @Override
        public ResultView getView(ResultIntel intel, ResultSet resultSet) {
            return new SystemView(intel, resultSet);
        }
    };

    public void createIntel(QueryManager manager, ResultSet resultSet) {
        ResultIntel intel = new ResultIntel(manager, resultSet);
        Global.getSector().getIntelManager().addIntel(intel, true);
    }

    public GroupingData getGroupingData(ResultSet resultSet) {
        MarketAPI market = resultSet.getMarket();
        RenderableIntelInfo info = new BoardRenderableInfo(
            StelnetHelper.getMarketWithFactionName(market),
            L10n.get(QueryL10n.RESULTS_IN_MARKET, resultSet.getResultCount())
        );
        return new GroupingData(info, market.getFaction(), market.getId(), market.getPrimaryEntity());
    }

    public ResultView getView(ResultIntel intel, ResultSet resultSet) {
        return new MarketView(intel, resultSet);
    }
}
