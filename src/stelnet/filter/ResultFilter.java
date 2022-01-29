package stelnet.filter;

import lombok.EqualsAndHashCode;
import stelnet.board.query.Result;
import stelnet.board.query.ResultSet;

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

    protected abstract boolean acceptResultSet(ResultSet resultSet);

    protected abstract boolean acceptResult(Result result);
}
