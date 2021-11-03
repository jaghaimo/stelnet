package stelnet.board.commodity.market.table;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.util.TableCellHelper;

@RequiredArgsConstructor
@Getter
public class SortableRow extends TableRow implements Comparable<SortableRow> {

    private final float profit;

    public void addLocation(MarketAPI market) {
        addCell(TableCellHelper.getFactionColor(market.getFaction()), TableCellHelper.getLocation(market));
    }

    public void addPriceAndQuantity(Color color, float price, int quantity) {
        addCell(color, Misc.getDGSCredits(price) + " / " + quantity);
    }

    @Override
    public int compareTo(SortableRow o) {
        return compare(this.getProfit(), o.getProfit());
    }

    private int compare(float o1, float o2) {
        return (int) (o2 - o1);
    }
}
