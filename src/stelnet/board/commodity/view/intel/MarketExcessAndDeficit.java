package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableComponent;
import uilib.ShowCargo;
import uilib.property.Size;

@RequiredArgsConstructor
public class MarketExcessAndDeficit extends RenderableComponent {

    private final Size size;
    private final Color factionColor;
    private final List<CargoStackAPI> excess = new LinkedList<>();
    private final List<CargoStackAPI> deficit = new LinkedList<>();

    public void add(final CommodityOnMarketAPI commodityOnMarket) {
        addToList(excess, commodityOnMarket, commodityOnMarket.getExcessQuantity());
        addToList(deficit, commodityOnMarket, commodityOnMarket.getDeficitQuantity());
    }

    public boolean isEmpty() {
        return deficit.isEmpty() && excess.isEmpty();
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        render(tooltip, excess, L10n.commodity("INTEL_WITH_EXCESS"));
        render(tooltip, deficit, L10n.commodity("INTEL_WITH_DEFICIT"));
    }

    private void render(final TooltipMakerAPI tooltip, final List<CargoStackAPI> stacks, final String label) {
        if (stacks.isEmpty()) {
            return;
        }
        tooltip.addSpacer(10f);
        final CargoAPI cargo = StelnetHelper.makeCargoFromStacks(stacks);
        final ShowCargo showCargo = new ShowCargo(cargo, label, "", size);
        showCargo.setTitleColor(factionColor);
        showCargo.render(tooltip);
    }

    private void addToList(
        final List<CargoStackAPI> list,
        final CommodityOnMarketAPI commodityOnMarket,
        float quantity
    ) {
        if (commodityOnMarket.isNonEcon() || commodityOnMarket.isMeta()) {
            return;
        }
        quantity = Math.min(quantity, 9999);
        if (quantity < 1) {
            return;
        }
        float addedQuantity = 0;
        while (addedQuantity < quantity) {
            final CargoStackAPI stack = Global
                .getFactory()
                .createCargoStack(CargoItemType.RESOURCES, commodityOnMarket.getId(), null);
            final float desiredSize = Math.min(quantity - addedQuantity, stack.getMaxSize());
            stack.setSize(desiredSize);
            list.add(stack);
            addedQuantity += stack.getSize();
        }
    }
}
