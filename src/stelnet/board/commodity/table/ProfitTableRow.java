package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
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

    public static int MINIMUM_QUANTITY = 100;

    private final List<Object> elements = new LinkedList<>();
    private final MarketAPI buyMarket;
    private final MarketAPI sellMarket;
    private float profit;
    private int quantity;

    public ProfitTableRow(final MarketAPI buyMarket, final MarketAPI sellMarket, final String commodityId) {
        this(buyMarket, sellMarket);
        quantity = calculateQuantity(buyMarket, sellMarket, commodityId);
        profit = calculateProfit(buyMarket, sellMarket, commodityId);
        addAllCells(commodityId);
    }

    private void addAllCells(final String commodityId) {
        final float buyPrice = new SupplyPrice(commodityId).getTotalPrice(buyMarket, quantity);
        final float sellPrice = new DemandPrice(commodityId).getTotalPrice(sellMarket, quantity);
        final Color rowColor = getRowColor(Misc.getTextColor());
        addCell(rowColor, Misc.getDGSCredits(profit) + "  (" + Misc.getWithDGS(quantity) + ")");
        addCell(buyMarket.getTextColorForFactionOrPlanet(), StelnetHelper.getMarketWithFactionName(buyMarket));
        addCell(rowColor, Misc.getDGSCredits(buyPrice));
        addCell(sellMarket.getTextColorForFactionOrPlanet(), StelnetHelper.getMarketWithFactionName(sellMarket));
        addCell(rowColor, Misc.getDGSCredits(sellPrice));
        addCell(rowColor, String.format("%.1f", getDistance()));
    }

    private void addCell(final Color color, final Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
    }

    public void addNumber(final int number) {
        elements.add(0, number + ".");
        elements.add(0, getRowColor(Misc.getGrayColor()));
        elements.add(0, Alignment.MID);
    }

    @Override
    public Object[] buildObjectArray() {
        return elements.toArray();
    }

    @Override
    public int compareTo(final ProfitTableRow o) {
        return compare(profit, o.getProfit());
    }

    private int compare(final float o1, final float o2) {
        return (int) (o2 - o1);
    }

    private float calculateProfit(final MarketAPI buyMarket, final MarketAPI sellMarket, final String commodityId) {
        if (quantity < MINIMUM_QUANTITY) {
            return 0;
        }

        final Price supplyPrice = new SupplyPrice(commodityId);
        final Price demandPrice = new DemandPrice(commodityId);
        final float buyPrice = supplyPrice.getUnitPrice(buyMarket);
        final float sellPrice = demandPrice.getUnitPrice(sellMarket);
        if (buyPrice >= sellPrice) {
            return 0;
        }

        final float bought = supplyPrice.getTotalPrice(buyMarket, quantity);
        final float sold = demandPrice.getTotalPrice(sellMarket, quantity);
        return sold - bought;
    }

    private int calculateQuantity(final MarketAPI buyMarket, final MarketAPI sellMarket, final String commodityId) {
        final CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);
        final CommodityOnMarketAPI sellToMarketCommodity = sellMarket.getCommodityData(commodityId);
        final int available = StelnetHelper.getCommodityAvailable(buyFromCommodity);
        final int demand = StelnetHelper.getCommodityDemand(sellMarket, sellToMarketCommodity);
        return Math.min(available, demand);
    }

    private float getDistance() {
        final float playerToBuy = Misc.getDistanceToPlayerLY(buyMarket.getPrimaryEntity());
        final float buyToSell = Misc.getDistanceLY(buyMarket.getPrimaryEntity(), sellMarket.getPrimaryEntity());
        return playerToBuy + buyToSell;
    }

    private Color getRowColor(final Color defaultColor) {
        final String buySystemName = StelnetHelper.getStarSystemName(buyMarket.getStarSystem(), true);
        final String sellSystemName = StelnetHelper.getStarSystemName(sellMarket.getStarSystem(), true);
        if (buySystemName.equals(sellSystemName)) {
            return Misc.getHighlightColor();
        }
        return defaultColor;
    }
}
