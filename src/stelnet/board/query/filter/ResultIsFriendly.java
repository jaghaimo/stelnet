package stelnet.board.query.filter;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;
import stelnet.filter.Filter;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ResultIsFriendly extends ResultFilter {

    private final Filter filter;

    @Override
    protected boolean acceptResultSet(final ResultSet resultSet) {
        return acceptResultSetCopy(resultSet);
    }

    @Override
    protected boolean acceptResult(final Result result) {
        return filter.accept(result.getMarket());
    }
}
