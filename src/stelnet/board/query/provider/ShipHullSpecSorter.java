package stelnet.board.query.provider;

import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.Comparator;

public class ShipHullSpecSorter implements Comparator<HullModSpecAPI> {

    @Override
    public int compare(final HullModSpecAPI o1, final HullModSpecAPI o2) {
        return o1.getDisplayName().compareTo(o2.getDisplayName());
    }
}
