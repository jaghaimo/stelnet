package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
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
    private final String submarketName;
    private final boolean isBlackMarket;
    private final StarSystemAPI system;
    private final MarketAPI market;

    private int hashCode = 0;

    public Result(MarketAPI market, PersonAPI person) {
        this.name = person.getNameString();
        this.type = person.getPost();
        this.object = person;
        this.system = market.getStarSystem();
        this.market = market;
        this.submarketName = "";
        this.isBlackMarket = false;
        this.hashCode = hashCode();
    }

    public Result(MarketAPI market, SubmarketAPI submarket, FleetMemberAPI fleetMember) {
        ShipHullSpecAPI hullSpec = fleetMember.getHullSpec();
        this.name = hullSpec.getNameWithDesignationWithDashClass();
        this.type = L10n.get(hullSpec.getHullSize());
        this.object = fleetMember;
        this.system = market.getStarSystem();
        this.market = market;
        this.submarketName = submarket.getNameOneLine();
        this.isBlackMarket = submarket.getPlugin().isBlackMarket();
        this.hashCode = hashCode();
    }

    public Result(MarketAPI market, SubmarketAPI submarket, CargoStackAPI cargoStack) {
        this.name = cargoStack.getDisplayName();
        this.type = L10n.get(cargoStack.getType());
        this.object = cargoStack;
        this.system = market.getStarSystem();
        this.market = market;
        this.submarketName = submarket.getNameOneLine();
        this.isBlackMarket = submarket.getPlugin().isBlackMarket();
        this.hashCode = hashCode();
    }

    public String getLocationName() {
        if (submarketName.isEmpty()) {
            return market.getName();
        }
        return String.format("%s - %s", market.getName(), submarketName);
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
        final String strings[][] = new String[][] {
            new String[] { getLocationName(), o.getLocationName() },
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
            hashCode = (getName() + getType() + getLocationName()).hashCode();
        }
        return hashCode;
    }
}
