package stelnet.board.query;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
        name = fleetMember.getHullSpec().getNameWithDesignationWithDashClass();
        type = "Ship";
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
    public int compareTo(Result othr) {
        final String string1[] = new String[] { this.getMarketName(), this.getSubmarketName(), this.getName() };
        final String string2[] = new String[] { othr.getMarketName(), othr.getSubmarketName(), othr.getName() };
        for (int i = 0; i < 3; i++) {
            int compareResult = string1[i].compareTo(string2[i]);
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
        // TODO Auto-generated method stub
        return null;
    }
}
