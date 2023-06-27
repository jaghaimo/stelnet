package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Comparator;

public class ShipHullSorter implements Comparator<FleetMemberAPI> {

    @Override
    public int compare(final FleetMemberAPI o1, final FleetMemberAPI o2) {
        final ShipHullSpecAPI s1 = o1.getHullSpec();
        final ShipHullSpecAPI s2 = o2.getHullSpec();
        final int hullDifference = s2.getHullSize().ordinal() - s1.getHullSize().ordinal();
        if (hullDifference != 0) {
            return hullDifference;
        }
        return s1.getHullName().compareTo(s2.getHullName());
    }
}
