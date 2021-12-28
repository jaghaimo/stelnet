package stelnet.board.query;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Set;
import stelnet.BoardInfo;
import stelnet.util.L10n;

public enum QueryGrouping {
    NO_GROUPING {
        @Override
        public SectorEntityToken getToken(ResultSet resultSet) {
            return null;
        }
    },
    BY_MARKET, // see default implementation
    BY_SYSTEM {
        @Override
        public BoardInfo getBoardInfo(ResultSet resultSet) {
            StarSystemAPI system = resultSet.getSystem();
            if (system == null) {
                return super.getBoardInfo(resultSet);
            }
            Set<MarketAPI> marketSet = resultSet.getMarketSet();
            return new BoardInfo(
                system.getName(),
                L10n.get(QueryL10n.RESULTS_IN_SYSTEM, resultSet.getResultNumber(), marketSet.size())
            );
        }

        @Override
        public FactionAPI getFaction(ResultSet resultSet) {
            StarSystemAPI system = resultSet.getSystem();
            if (system == null) {
                return super.getFaction(resultSet);
            }
            MarketAPI market = resultSet.getMarket();
            return Misc.getClaimingFaction(market.getPrimaryEntity());
        }

        @Override
        public SectorEntityToken getToken(ResultSet resultSet) {
            StarSystemAPI system = resultSet.getSystem();
            if (system == null) {
                return super.getToken(resultSet);
            }
            return system.getCenter();
        }
    };

    public BoardInfo getBoardInfo(ResultSet resultSet) {
        MarketAPI market = resultSet.getMarket();
        String name = String.format("%s, %s", market.getName(), market.getFaction().getDisplayName());
        return new BoardInfo(name, L10n.get(QueryL10n.RESULTS_IN_MARKET, resultSet.getResultNumber()));
    }

    public FactionAPI getFaction(ResultSet resultSet) {
        MarketAPI market = resultSet.getMarket();
        return market.getFaction();
    }

    public SectorEntityToken getToken(ResultSet resultSet) {
        MarketAPI market = resultSet.getMarket();
        return market.getPrimaryEntity();
    }
}
