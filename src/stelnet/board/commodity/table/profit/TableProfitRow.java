package stelnet.board.commodity.table.profit;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.StringUtils;
import stelnet.util.TableCellHelper;
import uilib.TableContentRow;

@Getter
public class TableProfitRow implements Comparable, TableContentRow {

    private List<Object> elements = new LinkedList<>();
    private final MarketAPI buyMarket;
    private final MarketAPI sellMarket;
    private float profit;

    public TableProfitRow(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        this.buyMarket = buyMarket;
        this.sellMarket = sellMarket;
        Price price = new SupplyPrice(commodityId);
        float buyPrice = price.getPriceAmount(buyMarket);
        float sellPrice = price.getPriceAmount(sellMarket);
        CommodityOnMarketAPI sellToMarketCommodity = sellMarket.getCommodityData(commodityId);
        CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);

        // Buy Price
        addCell(Misc.getHighlightColor(), Misc.getDGSCredits(buyPrice));

        // Sell Price
        addCell(Misc.getHighlightColor(), Misc.getDGSCredits(sellPrice));

        // Available / Demand
        addCell(
            Misc.getHighlightColor(),
            Misc.getWithDGS(TableCellHelper.getAvailable(buyFromCommodity)) +
            " / " +
            Misc.getWithDGS(TableCellHelper.getDemand(sellMarket, sellToMarketCommodity))
        );
        // Profit
        profit = ProfitCalculator.calculateProfit(buyMarket, sellMarket, commodityId);
        addDGSCreditsCell(profit);

        addCell(buyMarket.getTextColorForFactionOrPlanet(), TableCellHelper.getLocation(buyMarket));
        addCell(sellMarket.getTextColorForFactionOrPlanet(), TableCellHelper.getLocation(sellMarket));

        String buySystemName = StringUtils.getStarSystem(buyMarket, true);
        String sellSystemName = StringUtils.getStarSystem(sellMarket, true);

        Color buySystemColor = Misc.getGrayColor();
        if (buySystemName.equals(sellSystemName)) {
            buySystemColor = Misc.getHighlightColor();
        }

        float playerToBuy = Misc.getDistanceToPlayerLY(buyMarket.getPrimaryEntity());
        float buyToSell = Misc.getDistanceLY(buyMarket.getPrimaryEntity(), sellMarket.getPrimaryEntity());

        addCell(buySystemColor, String.format("%.1f", playerToBuy + buyToSell));
    }

    private void addDGSCreditsCell(float value) {
        addCell(Misc.getTextColor(), Misc.getDGSCredits(value));
    }

    private void addCell(Color color, Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
    }

    @Override
    public Object[] buildObjectArray() {
        return elements.toArray();
    }

    @Override
    public int compareTo(Object o) {
        return compare(profit, ((TableProfitRow) o).getProfit());
    }

    private int compare(float o1, float o2) {
        return (int) (o2 - o1);
    }
}
