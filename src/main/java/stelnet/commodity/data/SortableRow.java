package stelnet.commodity.data;

import lombok.Getter;

@Getter
public class SortableRow extends RowDataElement implements Comparable<SortableRow> {

    private final float profit;

    public SortableRow(float profit) {
        this.profit = profit;
    }

    @Override
    public int compareTo(SortableRow o) {
        return compare(this.getProfit(), o.getProfit());
    }

    private int compare(float o1, float o2) {
        return (int) (o2 - o1);
    }
}
