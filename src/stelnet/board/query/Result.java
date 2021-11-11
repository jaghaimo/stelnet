package stelnet.board.query;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Result extends Object implements Comparable<Result> {

    private final String name;
    private final String description;
    private final StarSystemAPI system;
    private final MarketAPI market;
    private final SubmarketAPI submarket;
    private int quantity = 0;
    private int hashCode = 0;

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
            hashCode = (name + market.getName() + submarket.getName()).hashCode();
        }
        return hashCode;
    }
}
