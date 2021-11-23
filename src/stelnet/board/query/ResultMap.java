package stelnet.board.query;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import java.util.HashMap;
import java.util.Map;

/**
 * Maps star systems to their respective results.
 */
public class ResultMap {

    private final Map<SectorEntityToken, ResultSet> resultMap = new HashMap<>();

    public void add(ResultSet resultSet) {
        resultMap.put(resultSet.getToken(), resultSet);
    }

    public boolean containsKey(ResultSet resultSet) {
        return resultMap.containsKey(resultSet.getToken());
    }

    public void clear() {
        resultMap.clear();
    }

    public ResultSet get(ResultSet resultSet) {
        return resultMap.get(resultSet.getToken());
    }

    public void update(ResultSet resultSet) {
        ResultSet existingResultSet = get(resultSet);
        existingResultSet.add(resultSet);
    }
}
