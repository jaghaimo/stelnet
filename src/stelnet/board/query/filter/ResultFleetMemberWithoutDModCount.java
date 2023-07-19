package stelnet.board.query.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.DModManager;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ResultFleetMemberWithoutDModCount extends ResultFilter {

    private final int numberOfDMods;

    @Override
    protected boolean acceptResultSet(ResultSet resultSet) {
        return acceptResultSetInPlace(resultSet);
    }

    @Override
    protected boolean acceptResult(Result result) {
        if (!result.isFleetMember()) {
            return true;
        }
        FleetMemberAPI fleetMember = result.getFleetMember();
        return DModManager.getNumDMods(fleetMember.getVariant()) != numberOfDMods;
    }
}
