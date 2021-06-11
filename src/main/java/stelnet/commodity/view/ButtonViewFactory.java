package stelnet.commodity.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;

import stelnet.filter.commodityspec.HasNotTag;
import stelnet.helper.CollectionHelper;
import stelnet.helper.GlobalSectorHelper;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Button;
import stelnet.ui.Group;
import stelnet.ui.Position;
import stelnet.ui.Size;

public class ButtonViewFactory {

    public AbstractRenderable createContainer(String activeId, Size size) {
        List<AbstractRenderable> buttons = new LinkedList<>();
        List<CommoditySpecAPI> commodities = GlobalSectorHelper.getAllCommodities();
        filterCommodities(commodities);
        sortCommodities(commodities);
        for (CommoditySpecAPI commodity : commodities) {
            buttons.add(createContainer(commodity, activeId));
        }
        Group group = new Group(buttons);
        group.setSize(new Size(200, size.getHeight() - 55));
        group.setOffset(new Position(size.getWidth() - 200, 0));
        return group;
    }

    private Button createContainer(CommoditySpecAPI commodity, String activeId) {
        boolean isOn = commodity.getId().equals(activeId);
        return new CommodityButton(commodity, isOn);
    }

    private void filterCommodities(List<CommoditySpecAPI> commodities) {
        CollectionHelper.reduce(commodities, new HasNotTag("nonecon"));
        CollectionHelper.reduce(commodities, new HasNotTag("meta"));
    }

    private void sortCommodities(List<CommoditySpecAPI> commodities) {
        Collections.sort(commodities, new Comparator<CommoditySpecAPI>() {

            @Override
            public int compare(CommoditySpecAPI commodityA, CommoditySpecAPI commodityB) {
                return commodityA.getName().compareToIgnoreCase(commodityB.getName());
            }
        });
    }
}
