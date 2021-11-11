package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import stelnet.util.L10n;
import uilib.TableContentRow;

@Getter
@Setter
@ToString
public class Result extends Object implements Comparable<Result>, TableContentRow {

    private final String name;
    private final String type;
    private final String submarketName;
    private final boolean isBlackMarket;
    private final StarSystemAPI system;
    private final MarketAPI market;
    private int quantity = 0;
    private int hashCode = 0;

    public Result(MarketAPI market, PersonAPI person) {
        name = person.getNameString();
        quantity = 1;
        type = person.getPost();
        system = market.getStarSystem();
        this.market = market;
        submarketName = "";
        isBlackMarket = false;
        hashCode = hashCode();
    }

    public Result(MarketAPI market, SubmarketAPI submarket, FleetMemberAPI fleetMember) {
        ShipHullSpecAPI hullSpec = fleetMember.getHullSpec();
        name = hullSpec.getNameWithDesignationWithDashClass();
        quantity = 1;
        type = L10n.get(hullSpec.getHullSize());
        system = market.getStarSystem();
        this.market = market;
        submarketName = submarket.getNameOneLine();
        isBlackMarket = submarket.getPlugin().isBlackMarket();
        hashCode = hashCode();
    }

    public Result(MarketAPI market, SubmarketAPI submarket, CargoStackAPI cargoStack) {
        name = cargoStack.getDisplayName();
        quantity = (int) cargoStack.getSize();
        type = L10n.get(cargoStack.getType());
        system = market.getStarSystem();
        this.market = market;
        submarketName = submarket.getNameOneLine();
        isBlackMarket = submarket.getPlugin().isBlackMarket();
        hashCode = hashCode();
    }

    public String getLocationName() {
        if (submarketName.isEmpty()) {
            return market.getName();
        }
        return String.format("%s - %s", market.getName(), submarketName);
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
            hashCode = (getName() + getLocationName()).hashCode();
        }
        return hashCode;
    }

    @Override
    public Object[] buildObjectArray() {
        Color rowColor = isBlackMarket ? Misc.getNegativeHighlightColor() : Misc.getTextColor();

        return new Object[] {
            Alignment.MID,
            market.getFaction().getColor(),
            getLocationName(),
            Alignment.MID,
            rowColor,
            getType(),
            Alignment.MID,
            rowColor,
            getName(),
            Alignment.MID,
            rowColor,
            String.valueOf(getQuantity()),
        };
    }
}
