package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import stelnet.util.L10n;
import uilib.TableContentRow;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Result extends Object implements Comparable<Result>, TableContentRow {

    private final String name;
    private final String type;
    private final StarSystemAPI system;
    private final MarketAPI market;
    private final SubmarketAPI submarket;
    private int quantity = 0;
    private int hashCode = 0;

    public Result(MarketAPI market, SubmarketAPI submarketAPI, FleetMemberAPI fleetMember) {
        ShipHullSpecAPI hullSpec = fleetMember.getHullSpec();
        name = hullSpec.getNameWithDesignationWithDashClass();
        quantity = 1;
        type = L10n.get(hullSpec.getHullSize());
        system = market.getStarSystem();
        this.market = market;
        this.submarket = submarketAPI;
        hashCode = hashCode();
    }

    public Result(MarketAPI market, SubmarketAPI submarketAPI, CargoStackAPI cargoStack) {
        name = cargoStack.getDisplayName();
        quantity = (int) cargoStack.getSize();
        type = L10n.get(cargoStack.getType());
        system = market.getStarSystem();
        this.market = market;
        this.submarket = submarketAPI;
        hashCode = hashCode();
    }

    public String getMarketName() {
        return market.getName();
    }

    public String getSubmarketName() {
        return submarket.getName();
    }

    @Override
    public int compareTo(Result o) {
        final String strings[][] = new String[][] {
            new String[] { getMarketName(), o.getMarketName() },
            new String[] { getSubmarketName(), o.getSubmarketName() },
            new String[] { getType(), o.getType() },
            new String[] { getName(), o.getName() },
        };
        for (String[] compare : strings) {
            int compareResult = compare[0].compareTo(compare[1]);
            if (compareResult != 0) {
                return compareResult;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Result) {
            return compareTo((Result) other) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            hashCode = (getName() + getMarketName() + getSubmarketName()).hashCode();
        }
        return hashCode;
    }

    @Override
    public Object[] buildObjectArray() {
        return new Object[] {
            Alignment.MID,
            market.getFaction().getColor(),
            getMarketName(),
            Alignment.MID,
            submarket.getFaction().getColor(),
            getSubmarketName(),
            Alignment.MID,
            Misc.getTextColor(),
            getType(),
            Alignment.MID,
            Misc.getTextColor(),
            getName(),
            Alignment.MID,
            Misc.getTextColor(),
            String.valueOf(getQuantity()),
        };
    }
}
