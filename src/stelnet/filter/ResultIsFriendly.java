package stelnet.filter;

import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;

@EqualsAndHashCode(callSuper = false)
public class ResultIsFriendly extends ResultFilter {

    private final Filter filter = new FactionIsFriendly();

    @Override
    protected boolean acceptResultSet(ResultSet resultSet) {
        return acceptResultSetCopy(resultSet);
    }

    @Override
    protected boolean acceptResult(Result result) {
        return filter.accept(result.getMarket());
    }
}
