package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
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
import stelnet.util.StelnetHelper;
import uilib.TableContentRow;

@Getter
@RequiredArgsConstructor
public class ProfitTableRow implements Comparable<ProfitTableRow>, TableContentRow {

    private static final int MINIMUM_QUANTITY = 1000;

    private final List<Object> elements = new LinkedList<>();
    private final MarketAPI buyMarket;
    private final MarketAPI sellMarket;
    private float profit;
    private int quantity;

    public ProfitTableRow(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        this(buyMarket, sellMarket);
        quantity = calculateQuantity(buyMarket, sellMarket, commodityId);
        profit = calculateProfit(buyMarket, sellMarket, commodityId);
        addAllCells(commodityId);
    }

    private void addAllCells(String commodityId) {
        float buyPrice = new SupplyPrice(commodityId).getTotalPrice(buyMarket, quantity);
        float sellPrice = new DemandPrice(commodityId).getTotalPrice(sellMarket, quantity);
        Color rowColor = getRowColor(Misc.getTextColor());
        addCell(rowColor, Misc.getDGSCredits(profit) + "  (" + Misc.getWithDGS(quantity) + ")");
        addCell(buyMarket.getTextColorForFactionOrPlanet(), StelnetHelper.getLocation(buyMarket));
        addCell(rowColor, Misc.getDGSCredits(buyPrice));
        addCell(sellMarket.getTextColorForFactionOrPlanet(), StelnetHelper.getLocation(sellMarket));
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
        if (quantity < MINIMUM_QUANTITY) {
            return 0;
        }

        Price supplyPrice = new SupplyPrice(commodityId);
        Price demandPrice = new DemandPrice(commodityId);
        float buyPrice = supplyPrice.getUnitPrice(buyMarket);
        float sellPrice = demandPrice.getUnitPrice(sellMarket);
        if (buyPrice >= sellPrice) {
            return 0;
        }

        float bought = supplyPrice.getTotalPrice(buyMarket, quantity);
        float sold = demandPrice.getTotalPrice(sellMarket, quantity);
        return sold - bought;
    }

    private int calculateQuantity(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);
        CommodityOnMarketAPI sellToMarketCommodity = sellMarket.getCommodityData(commodityId);
        int available = StelnetHelper.getAvailable(buyFromCommodity);
        int demand = StelnetHelper.getDemand(sellMarket, sellToMarketCommodity);
        return Math.min(available, demand);
    }

    private float getDistance() {
        float playerToBuy = Misc.getDistanceToPlayerLY(buyMarket.getPrimaryEntity());
        float buyToSell = Misc.getDistanceLY(buyMarket.getPrimaryEntity(), sellMarket.getPrimaryEntity());
        return playerToBuy + buyToSell;
    }

    private Color getRowColor(Color defaultColor) {
        String buySystemName = StelnetHelper.getStarSystemName(buyMarket.getStarSystem(), true);
        String sellSystemName = StelnetHelper.getStarSystemName(sellMarket.getStarSystem(), true);
        if (buySystemName.equals(sellSystemName)) {
            return Misc.getHighlightColor();
        }
        return defaultColor;
    }
}
