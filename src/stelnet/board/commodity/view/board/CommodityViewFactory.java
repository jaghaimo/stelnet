package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.filter.AnyHasTag;
import stelnet.filter.LogicalNot;
import stelnet.util.CollectionUtils;
import stelnet.util.EconomyUtils;
import uilib.Button;
import uilib.Group;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.property.Position;
import uilib.property.Size;

@RequiredArgsConstructor
public class CommodityViewFactory implements RenderableFactory {

    private final String commodityId;

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> buttons = new LinkedList<>();
        List<CommoditySpecAPI> commodities = EconomyUtils.getAllCommodities();
        filterCommodities(commodities);
        sortCommodities(commodities);
        buttons.add(new Spacer(1));
        for (CommoditySpecAPI commodity : commodities) {
            buttons.add(getCommodityButton(commodity, commodityId));
        }
        Group group = new Group(buttons);
        group.setSize(new Size(200, size.getHeight() - 100));
        group.setOffset(new Position(size.getWidth() - 200, 28));
        return Collections.<Renderable>singletonList(group);
    }

    private void filterCommodities(List<CommoditySpecAPI> commodities) {
        CollectionUtils.reduce(commodities, new LogicalNot(new AnyHasTag("nonecon")));
        CollectionUtils.reduce(commodities, new LogicalNot(new AnyHasTag("meta")));
    }

    private Button getCommodityButton(CommoditySpecAPI commodity, String activeId) {
        boolean isOn = commodity.getId().equals(activeId);
        return new CommodityButton(commodity, isOn);
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
