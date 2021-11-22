package stelnet.board.query.provider;

import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.Comparator;

public class ShipDmodSorter implements Comparator<HullModSpecAPI> {

    @Override
    public int compare(HullModSpecAPI o1, HullModSpecAPI o2) {
        return o1.getDisplayName().compareTo(o2.getDisplayName());
    }
}
