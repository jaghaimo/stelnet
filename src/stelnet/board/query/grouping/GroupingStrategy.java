package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Set;
import stelnet.board.BoardRenderableInfo;
import stelnet.board.query.ResultSet;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntelInfo;

public enum GroupingStrategy {
    BY_MARKET,
    BY_SYSTEM {
        @Override
        public GroupingData getGroupingData(final ResultSet resultSet) {
            final StarSystemAPI system = resultSet.getSystem();
            if (system == null) {
                return super.getGroupingData(resultSet);
            }
            final Set<MarketAPI> marketSet = resultSet.getMarketSet();
            final RenderableIntelInfo info = new BoardRenderableInfo(
                system.getName(),
                L10n.query("RESULTS_IN_SYSTEM", resultSet.getResultCount(), marketSet.size())
            );
            final MarketAPI market = resultSet.getMarket();
            final FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
            return new GroupingData(info, faction, system.getId(), system.getCenter());
        }
    };

    public GroupingData getGroupingData(final ResultSet resultSet) {
        final MarketAPI market = resultSet.getMarket();
        final RenderableIntelInfo info = new BoardRenderableInfo(
            StelnetHelper.getMarketWithFactionName(market),
            L10n.query("RESULTS_IN_MARKET", resultSet.getResultCount())
        );
        return new GroupingData(info, market.getFaction(), market.getId(), market.getPrimaryEntity());
    }
}
