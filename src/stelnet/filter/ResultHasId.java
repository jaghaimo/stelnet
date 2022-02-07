package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;

@EqualsAndHashCode(callSuper = false)
public class ResultHasId extends ResultFilter {

    private final Filter filter;

    public ResultHasId(String id) {
        filter = new AnyHasId(id);
    }

    @Override
    protected boolean acceptResultSet(ResultSet resultSet) {
        return acceptResultSetCopy(resultSet);
    }

    @Override
    protected boolean acceptResult(Result result) {
        SubmarketAPI submarket = result.getSubmarket();
        if (submarket == null) {
            return true;
        }
        return filter.accept(submarket);
    }
}
