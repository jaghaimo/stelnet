package stelnet.board.commodity.view;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.view.button.CommodityButton;
import stelnet.filter.AnyHasTagFilter;
import stelnet.filter.LogicalNotFilter;
import stelnet.util.CollectionUtils;
import stelnet.util.EconomyUtils;
import uilib.Button;
import uilib.Group;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Position;
import uilib.property.Size;

@RequiredArgsConstructor
public class ButtonViewFactory implements RenderableFactory {

    private final String commodityId;

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> buttons = new LinkedList<>();
        List<CommoditySpecAPI> commodities = EconomyUtils.getAllCommodities();
        filterCommodities(commodities);
        sortCommodities(commodities);
        for (CommoditySpecAPI commodity : commodities) {
            buttons.add(createContainer(commodity, commodityId));
        }
        Group group = new Group(buttons);
        group.setSize(new Size(200, size.getHeight() - 55));
        group.setOffset(new Position(size.getWidth() - 200, 24));
        return Collections.<Renderable>singletonList(group);
    }

    private Button createContainer(CommoditySpecAPI commodity, String activeId) {
        boolean isOn = commodity.getId().equals(activeId);
        return new CommodityButton(commodity, isOn);
    }

    private void filterCommodities(List<CommoditySpecAPI> commodities) {
        CollectionUtils.reduce(commodities, new LogicalNotFilter(new AnyHasTagFilter("nonecon")));
        CollectionUtils.reduce(commodities, new LogicalNotFilter(new AnyHasTagFilter("meta")));
    }

    private void sortCommodities(List<CommoditySpecAPI> commodities) {
        Collections.sort(
            commodities,
            new Comparator<CommoditySpecAPI>() {
                @Override
                public int compare(CommoditySpecAPI commodityA, CommoditySpecAPI commodityB) {
                    return commodityA.getName().compareToIgnoreCase(commodityB.getName());
                }
            }
        );
    }
}
