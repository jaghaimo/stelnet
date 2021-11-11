package stelnet.board.query;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.Delegate;

/**
 * Maps star systems to their respective results.
 */
public class ResultMap {

    @Delegate
    private final Map<StarSystemAPI, ResultSet> resultSet = new HashMap<>();

    public void add(ResultSet resultSet) {
        put(resultSet.getSystem(), resultSet);
    }

    public boolean containsKey(ResultSet resultSet) {
        return containsKey(resultSet.getSystem());
    }

    public ResultSet get(ResultSet resultSet) {
        return get(resultSet.getSystem());
    }

    public void update(ResultSet resultSet) {
        ResultSet existingResultSet = get(resultSet);
        existingResultSet.add(resultSet);
    }
}
