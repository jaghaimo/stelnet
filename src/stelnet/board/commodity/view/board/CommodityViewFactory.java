package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.filter.AnyHasTag;
import stelnet.filter.LogicalNot;
import stelnet.util.CollectionUtils;
import uilib.*;
import uilib.property.Position;
import uilib.property.Size;

@RequiredArgsConstructor
public class CommodityViewFactory implements RenderableFactory {

    private final String commodityId;

    @Override
    public List<Renderable> create(final Size size) {
        final List<Renderable> buttons = new LinkedList<>();
        final List<CommoditySpecAPI> commodities = getAllCommodities();
        filterCommodities(commodities);
        sortCommodities(commodities);
        buttons.add(new Spacer(1));
        for (final CommoditySpecAPI commodity : commodities) {
            buttons.add(getCommodityButton(commodity, commodityId));
        }
        final Group group = new Group(buttons);
        group.setSize(new Size(200, size.getHeight() - 100));
        group.setOffset(new Position(size.getWidth() - 200, 28));
        return Collections.<Renderable>singletonList(group);
    }

    private void filterCommodities(final List<CommoditySpecAPI> commodities) {
        CollectionUtils.reduce(commodities, new LogicalNot(new AnyHasTag("nonecon")));
        CollectionUtils.reduce(commodities, new LogicalNot(new AnyHasTag("meta")));
    }

    private List<CommoditySpecAPI> getAllCommodities() {
        final EconomyAPI economy = Global.getSector().getEconomy();
        final List<CommoditySpecAPI> commodities = new LinkedList<>();
        for (final String commodityId : economy.getAllCommodityIds()) {
            commodities.add(economy.getCommoditySpec(commodityId));
        }
        return commodities;
    }

    private Button getCommodityButton(final CommoditySpecAPI commodity, final String activeId) {
        final boolean isOn = commodity.getId().equals(activeId);
        return new CommodityButton(commodity, isOn);
    }

    private void sortCommodities(final List<CommoditySpecAPI> commodities) {
        Collections.sort(
            commodities,
            new Comparator<CommoditySpecAPI>() {
                @Override
                public int compare(final CommoditySpecAPI commodityA, final CommoditySpecAPI commodityB) {
                    return commodityA.getName().compareToIgnoreCase(commodityB.getName());
                }
            }
        );
    }
}
