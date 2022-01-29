package stelnet.filter;

import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;
import stelnet.util.CollectionUtils;

@EqualsAndHashCode(callSuper = false)
public final class ResultIsPurchasable extends ResultFilter {

    @Override
    protected boolean acceptResultSet(ResultSet resultSet) {
        CollectionUtils.reduce(resultSet.getResultSet(), this);
        resultSet.refresh();
        return resultSet.size() > 0;
    }

    @Override
    protected boolean acceptResult(Result result) {
        return result.isPurchasable();
    }
}
