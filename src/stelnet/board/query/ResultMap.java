package stelnet.board.query;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps star systems to their respective results.
 */
public class ResultMap {

    private final Map<String, ResultSet> resultMap = new HashMap<>();

    public void add(ResultSet resultSet) {
        resultMap.put(resultSet.getKey(), resultSet);
    }

    public boolean containsKey(ResultSet resultSet) {
        return resultMap.containsKey(resultSet.getKey());
    }

    public void clear() {
        resultMap.clear();
    }

    public ResultSet get(ResultSet resultSet) {
        return resultMap.get(resultSet.getKey());
    }

    public void update(ResultSet resultSet) {
        ResultSet existingResultSet = get(resultSet);
        existingResultSet.add(resultSet);
    }
}
