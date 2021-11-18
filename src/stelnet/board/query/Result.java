package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import stelnet.util.L10n;

@Getter
@Setter
@ToString
public class Result implements Comparable<Result> {

    private final String name;
    private final String type;
    private final Object object;
    private final MarketAPI market;
    private final SubmarketAPI submarket;
    private final int hashCode;

    public Result(MarketAPI market, PersonAPI person) {
        this.name = person.getNameString();
        this.type = person.getPost();
        this.object = person;
        this.market = market;
        this.submarket = null;
        this.hashCode = hashCode();
    }

    public Result(MarketAPI market, SubmarketAPI submarket, FleetMemberAPI fleetMember) {
        ShipHullSpecAPI hullSpec = fleetMember.getHullSpec();
        this.name = hullSpec.getNameWithDesignationWithDashClass();
        this.type = L10n.get(hullSpec.getHullSize());
        this.object = fleetMember;
        this.market = market;
        this.submarket = submarket;
        this.hashCode = hashCode();
    }

    public Result(MarketAPI market, SubmarketAPI submarket, CargoStackAPI cargoStack) {
        this.name = cargoStack.getDisplayName();
        this.type = L10n.get(cargoStack.getType());
        this.object = cargoStack;
        this.market = market;
        this.submarket = submarket;
        this.hashCode = hashCode();
    }

    public CargoStackAPI getCargoStack() {
        return (CargoStackAPI) object;
    }

    public FleetMemberAPI getFleetMember() {
        return (FleetMemberAPI) object;
    }

    public PersonAPI getPerson() {
        return (PersonAPI) object;
    }

    public int getSubmarketNumber() {
        return market.getSubmarketsCopy().indexOf(submarket);
    }

    public boolean isCargoStack() {
        return object instanceof CargoStackAPI;
    }

    public boolean isFleetMember() {
        return object instanceof FleetMemberAPI;
    }

    public boolean isPerson() {
        return object instanceof PersonAPI;
    }

    @Override
    public int compareTo(Result o) {
        final String comparables[][] = new String[][] {
            new String[] { getMarket().getName(), o.getMarket().getName() },
            new String[] { String.valueOf(getSubmarketNumber()), String.valueOf(o.getSubmarketNumber()) },
            new String[] { getType(), o.getType() },
            new String[] { getName(), o.getName() },
        };
        for (String[] compare : comparables) {
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
}
