package stelnet.board.query.filter;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;

@EqualsAndHashCode(callSuper = false)
public abstract class ResultFilter extends Filter {

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

    protected boolean acceptResultSetCopy(ResultSet resultSet) {
        Set<Result> copyOfResults = new LinkedHashSet<>(resultSet.getResultSet());
        CollectionUtils.reduce(copyOfResults, this);
        return copyOfResults.size() > 0;
    }

    protected boolean acceptResultSetInPlace(ResultSet resultSet) {
        CollectionUtils.reduce(resultSet.getResultSet(), this);
        resultSet.refresh();
        return resultSet.size() > 0;
    }

    protected abstract boolean acceptResultSet(ResultSet resultSet);

    protected abstract boolean acceptResult(Result result);
}
