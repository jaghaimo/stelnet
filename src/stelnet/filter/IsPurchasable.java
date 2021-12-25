package stelnet.filter;

import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;
import stelnet.util.CollectionUtils;

public final class IsPurchasable extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof ResultSet) {
            return acceptResultSet((ResultSet) object);
        }
        if (object instanceof Result) {
            return acceptResult((Result) object);
        }
        return false;
    }

    private boolean acceptResultSet(ResultSet resultSet) {
        CollectionUtils.reduce(resultSet.getResultSet(), this);
        resultSet.refresh();
        return resultSet.getResultNumber() > 0;
    }

    private boolean acceptResult(Result result) {
        return result.isPurchasable();
    }
}
