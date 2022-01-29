package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;
import stelnet.util.CollectionUtils;

@EqualsAndHashCode(callSuper = false)
public class ResultHasId extends ResultFilter {

    private final Filter filter;

    public ResultHasId(String id) {
        filter = new AnyHasId(id);
    }

    @Override
    protected boolean acceptResultSet(ResultSet resultSet) {
        Set<Result> copyOfResults = new LinkedHashSet<>(resultSet.getResultSet());
        CollectionUtils.reduce(copyOfResults, this);
        return copyOfResults.size() > 0;
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
