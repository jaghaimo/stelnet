package stelnet.filter;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;
import stelnet.util.CollectionUtils;

@EqualsAndHashCode(callSuper = false)
public class ResultIsFriendly extends ResultFilter {

    private final Filter filter = new FactionIsFriendly();

    @Override
    protected boolean acceptResultSet(ResultSet resultSet) {
        Set<Result> copyOfResults = new LinkedHashSet<>(resultSet.getResultSet());
        CollectionUtils.reduce(copyOfResults, this);
        return copyOfResults.size() > 0;
    }

    @Override
    protected boolean acceptResult(Result result) {
        return filter.accept(result.getMarket());
    }
}
