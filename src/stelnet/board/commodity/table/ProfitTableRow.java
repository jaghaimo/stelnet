package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.StringUtils;
import stelnet.util.TableCellHelper;
import uilib.TableContentRow;

@RequiredArgsConstructor
public class ProfitTableRow implements Comparable<ProfitTableRow>, TableContentRow {

    private static final int MINIMUM_QUANTITY = 1000;

    private final List<Object> elements = new LinkedList<>();
    private final MarketAPI buyMarket;
    private final MarketAPI sellMarket;

    @Getter
    private float profit;

    public ProfitTableRow(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        this(buyMarket, sellMarket);
        profit = calculateProfit(buyMarket, sellMarket, commodityId);
        addAllCells(commodityId);
    }

    private void addAllCells(String commodityId) {
        int available = TableCellHelper.getAvailable(buyMarket, commodityId);
        int demand = TableCellHelper.getDemand(sellMarket, commodityId);
        int quantity = Math.min(available, demand);
        float buyPrice = new SupplyPrice(commodityId).getTotalPrice(buyMarket, quantity);
        float sellPrice = new DemandPrice(commodityId).getTotalPrice(sellMarket, quantity);
        Color rowColor = getRowColor(Misc.getTextColor());
        addCell(rowColor, Misc.getDGSCredits(profit) + "  (" + Misc.getWithDGS(quantity) + ")");
        addCell(buyMarket.getTextColorForFactionOrPlanet(), TableCellHelper.getLocation(buyMarket));
        addCell(rowColor, Misc.getDGSCredits(buyPrice));
        addCell(sellMarket.getTextColorForFactionOrPlanet(), TableCellHelper.getLocation(sellMarket));
        addCell(rowColor, Misc.getDGSCredits(sellPrice));
        addCell(rowColor, String.format("%.1f", getDistance()));
    }

    private void addCell(Color color, Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
    }

    public void addNumber(int number) {
        elements.add(0, number + ".");
        elements.add(0, getRowColor(Misc.getGrayColor()));
        elements.add(0, Alignment.MID);
    }

    @Override
    public Object[] buildObjectArray() {
        return elements.toArray();
    }

    @Override
    public int compareTo(ProfitTableRow o) {
        return compare(profit, o.getProfit());
    }

    private int compare(float o1, float o2) {
        return (int) (o2 - o1);
    }

    private float calculateProfit(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        Price supplyPrice = new SupplyPrice(commodityId);
        Price demandPrice = new DemandPrice(commodityId);
        float buyPrice = supplyPrice.getUnitPrice(buyMarket);
        float sellPrice = demandPrice.getUnitPrice(sellMarket);
        if (buyPrice >= sellPrice) {
            return 0;
        }

        int available = TableCellHelper.getAvailable(buyMarket, commodityId);
        int demand = TableCellHelper.getDemand(sellMarket, commodityId);
        int quantity = Math.min(available, demand);

        if (quantity < MINIMUM_QUANTITY) {
            return 0;
        }

        float bought = supplyPrice.getTotalPrice(buyMarket, quantity);
        float sold = demandPrice.getTotalPrice(sellMarket, quantity);
        return sold - bought;
    }

    private float getDistance() {
        float playerToBuy = Misc.getDistanceToPlayerLY(buyMarket.getPrimaryEntity());
        float buyToSell = Misc.getDistanceLY(buyMarket.getPrimaryEntity(), sellMarket.getPrimaryEntity());
        return playerToBuy + buyToSell;
    }

    private Color getRowColor(Color defaultColor) {
        String buySystemName = StringUtils.getStarSystem(buyMarket, true);
        String sellSystemName = StringUtils.getStarSystem(sellMarket, true);
        if (buySystemName.equals(sellSystemName)) {
            return Misc.getHighlightColor();
        }
        return defaultColor;
    }
}
