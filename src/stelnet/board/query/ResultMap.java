package stelnet.board.query;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps star systems to their respective results.
 */
public class ResultMap {

    private final Map<String, ResultSet> resultMap = new HashMap<>();

    public void add(final ResultSet resultSet) {
        resultMap.put(resultSet.getKey(), resultSet);
    }

    public boolean containsKey(final ResultSet resultSet) {
        return resultMap.containsKey(resultSet.getKey());
    }

    public void clear() {
        resultMap.clear();
    }

    public ResultSet get(final ResultSet resultSet) {
        return resultMap.get(resultSet.getKey());
    }

    public void update(final ResultSet resultSet) {
        final ResultSet existingResultSet = get(resultSet);
        existingResultSet.add(resultSet);
    }
}
