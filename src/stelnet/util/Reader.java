package stelnet.util;

import com.fs.starfarer.api.Global;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import org.json.JSONObject;

@Log4j
public class Reader {

    protected static List<String> getStrings(String path) {
        List<String> strings = new LinkedList<>();
        try {
            JSONArray config = Global.getSettings().getMergedSpreadsheetDataForMod("id", path, ModConstants.STELNET);
            for (int i = 0; i < config.length(); i++) {
                JSONObject row = config.getJSONObject(i);
                strings.add(row.getString("id"));
            }
        } catch (Throwable throwable) {
            log.warn("Skipping invalid file " + path, throwable);
        }
        return strings;
    }
}
