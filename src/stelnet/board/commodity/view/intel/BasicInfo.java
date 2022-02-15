package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.TableCellHelper;
import uilib.RenderableComponent;

@RequiredArgsConstructor
public class BasicInfo extends RenderableComponent {

    private final float width;
    private final CommodityOnMarketAPI commodityOnMarket;
    private final DisplayablePrice buyPrice;
    private final DisplayablePrice sellPrice;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        String name = commodityOnMarket.getCommodity().getName() + " at a glance";
        tooltip.addSpacer(10);
        addSectionTitle(tooltip, name, commodityOnMarket.getMarket().getTextColorForFactionOrPlanet(), width);
        tooltip.beginGrid(width, 1);
        tooltip.addToGrid(0, 0, "Quantity", "" + TableCellHelper.getAvailable(commodityOnMarket));
        tooltip.addToGrid(0, 1, "Buy at", buyPrice.getDisplayedPrice());
        tooltip.addToGrid(0, 2, "Sell for", sellPrice.getDisplayedPrice());
        addConditional(tooltip, "Excess quantity", commodityOnMarket.getExcessQuantity());
        addConditional(tooltip, "Deficit quantity", commodityOnMarket.getDeficitQuantity());
        tooltip.addGrid(4);
        tooltip.addSpacer(10);
    }

    private void addConditional(TooltipMakerAPI tooltip, String label, float quantity) {
        if (quantity > 0) {
            tooltip.addToGrid(0, 3, label, String.format("%.0f", quantity));
        }
    }
}
