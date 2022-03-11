package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.StringUtils;
import stelnet.util.TableCellHelper;
import uilib.TableContentRow;

@Getter
public class ProfitTableRow extends ProfitCalculator implements Comparable<ProfitTableRow>, TableContentRow {

    private List<Object> elements = new LinkedList<>();
    private final MarketAPI buyMarket;
    private final MarketAPI sellMarket;
    private float profit;

    public ProfitTableRow(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        this.buyMarket = buyMarket;
        this.sellMarket = sellMarket;
        this.profit = calculateProfit(buyMarket, sellMarket, commodityId);
        float buyPrice = new SupplyPrice(commodityId).getUnitPrice(buyMarket);
        float sellPrice = new DemandPrice(commodityId).getUnitPrice(sellMarket);
        CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);
        CommodityOnMarketAPI sellToMarketCommodity = sellMarket.getCommodityData(commodityId);
        int available = TableCellHelper.getAvailable(buyFromCommodity);
        int demand = TableCellHelper.getDemand(sellMarket, sellToMarketCommodity);
        int quantity = Math.min(available, demand);
        Color rowColor = getRowColor();

        addCell(Misc.getTextColor(), Misc.getDGSCredits(profit) + "  (" + Misc.getWithDGS(quantity) + ")");
        addCell(rowColor, Misc.getDGSCredits(buyPrice) + "  (" + Misc.getWithDGS(available) + ")");
        addCell(rowColor, Misc.getDGSCredits(sellPrice) + "  (" + Misc.getWithDGS(demand) + ")");
        addCell(buyMarket.getTextColorForFactionOrPlanet(), TableCellHelper.getLocation(buyMarket));
        addCell(sellMarket.getTextColorForFactionOrPlanet(), TableCellHelper.getLocation(sellMarket));
        addCell(rowColor, String.format("%.1f", getDistance()));
    }

    private void addCell(Color color, Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
    }

    public void addNumber(int number) {
        elements.add(0, number + ".");
        elements.add(0, Misc.getGrayColor());
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

    private float getDistance() {
        float playerToBuy = Misc.getDistanceToPlayerLY(buyMarket.getPrimaryEntity());
        float buyToSell = Misc.getDistanceLY(buyMarket.getPrimaryEntity(), sellMarket.getPrimaryEntity());
        return playerToBuy + buyToSell;
    }

    public Color getRowColor() {
        String buySystemName = StringUtils.getStarSystem(buyMarket, true);
        String sellSystemName = StringUtils.getStarSystem(sellMarket, true);
        if (buySystemName.equals(sellSystemName)) {
            return Misc.getHighlightColor();
        }
        return Misc.getGrayColor();
    }
}
