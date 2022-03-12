package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;
import uilib.RenderableComponent;
import uilib.ShowCargo;
import uilib.property.Size;

@RequiredArgsConstructor
public class MarketExcessAndDeficit extends RenderableComponent {

    private final Size size;
    private final Color factionColor;
    private final List<CargoStackAPI> excess = new LinkedList<>();
    private final List<CargoStackAPI> deficit = new LinkedList<>();

    public void add(CommodityOnMarketAPI commodityOnMarket) {
        addToList(excess, commodityOnMarket, commodityOnMarket.getExcessQuantity());
        addToList(deficit, commodityOnMarket, commodityOnMarket.getDeficitQuantity());
    }

    public boolean isEmpty() {
        return deficit.isEmpty() && excess.isEmpty();
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (!excess.isEmpty()) {
            render(tooltip, new ShowCargo(excess, L10n.get(CommodityL10n.INTEL_WITH_EXCESS), "", size));
        }
        if (!deficit.isEmpty()) {
            render(tooltip, new ShowCargo(deficit, L10n.get(CommodityL10n.INTEL_WITH_DEFICIT), "", size));
        }
    }

    private void render(TooltipMakerAPI tooltip, ShowCargo showCargo) {
        tooltip.addSpacer(10f);
        showCargo.setTitleColor(factionColor);
        showCargo.render(tooltip);
    }

    private void addToList(List<CargoStackAPI> list, CommodityOnMarketAPI commodityOnMarket, float quantity) {
        if (commodityOnMarket.isNonEcon() || commodityOnMarket.isMeta()) {
            return;
        }
        quantity = Math.min(quantity, 9999);
        if (quantity < 1) {
            return;
        }
        float addedQuantity = 0;
        while (addedQuantity < quantity) {
            CargoStackAPI stack = Global
                .getFactory()
                .createCargoStack(CargoItemType.RESOURCES, commodityOnMarket.getId(), null);
            float desiredSize = Math.min(quantity - addedQuantity, stack.getMaxSize());
            stack.setSize(desiredSize);
            list.add(stack);
            addedQuantity += stack.getSize();
        }
    }
}
