package stelnet.board.query.filter;

import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;

@EqualsAndHashCode(callSuper = false)
public final class ResultIsPurchasable extends ResultFilter {

    @Override
    protected boolean acceptResultSet(ResultSet resultSet) {
        return acceptResultSetInPlace(resultSet);
    }

    @Override
    protected boolean acceptResult(Result result) {
        return result.isPurchasable();
    }
}
