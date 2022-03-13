package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableComponent;

@RequiredArgsConstructor
public class BasicInfo extends RenderableComponent {

    private final float width;
    private final CommodityOnMarketAPI commodityOnMarket;
    private final DisplayablePrice buyPrice;
    private final DisplayablePrice sellPrice;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        String name = L10n.get(CommodityL10n.INTEL_AT_A_GLANCE, commodityOnMarket.getCommodity().getName());
        tooltip.addSpacer(10);
        addSectionTitle(tooltip, name, commodityOnMarket.getMarket().getTextColorForFactionOrPlanet(), width);
        tooltip.beginGrid(width, 1);
        tooltip.addToGrid(
            0,
            0,
            L10n.get(CommodityL10n.INTEL_QUANTITY),
            "" + StelnetHelper.getAvailable(commodityOnMarket)
        );
        tooltip.addToGrid(0, 1, L10n.get(CommodityL10n.INTEL_BUY_AT), buyPrice.getDisplayedPrice());
        tooltip.addToGrid(0, 2, L10n.get(CommodityL10n.INTEL_SELL_FOR), sellPrice.getDisplayedPrice());
        addConditional(tooltip, L10n.get(CommodityL10n.INTEL_EXCESS_QUANTITY), commodityOnMarket.getExcessQuantity());
        addConditional(tooltip, L10n.get(CommodityL10n.INTEL_DEFICIT_QUANTITY), commodityOnMarket.getDeficitQuantity());
        tooltip.addGrid(4);
        tooltip.addSpacer(10);
    }

    private void addConditional(TooltipMakerAPI tooltip, String label, float quantity) {
        if (quantity > 0) {
            tooltip.addToGrid(0, 3, label, String.format("%.0f", quantity));
        }
    }
}
