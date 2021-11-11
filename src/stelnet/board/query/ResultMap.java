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
        StarSystemAPI system = resultSet.getSystem();
        if (!containsKey(system)) {
            put(system, resultSet);
            return;
        }
        ResultSet existingResultSet = get(system);
        existingResultSet.add(resultSet);
    }
}
