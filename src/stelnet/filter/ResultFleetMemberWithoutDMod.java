package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ResultFleetMemberWithoutDMod extends ResultFilter {

    private final String id;

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
        return !fleetMember.getVariant().hasHullMod(id);
    }
}
