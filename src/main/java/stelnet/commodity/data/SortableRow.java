package stelnet.commodity.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SortableRow extends TableRow implements Comparable<SortableRow> {

    private final float profit;

    @Override
    public int compareTo(SortableRow o) {
        return compare(this.getProfit(), o.getProfit());
    }

    private int compare(float o1, float o2) {
        return (int) (o2 - o1);
    }
}
