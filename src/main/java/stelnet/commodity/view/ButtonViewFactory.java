package stelnet.commodity.view;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import stelnet.helper.GlobalHelper;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Button;
import stelnet.ui.Size;
import stelnet.ui.VerticalViewContainer;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ButtonViewFactory {

    public VerticalViewContainer createContainer(String activeId, Size size) {
        EconomyAPI economy = GlobalHelper.getEconomy();
        List<AbstractRenderable> buttons = new LinkedList<>();
        List<String> commodityIds = economy.getAllCommodityIds();
        sortCommodities(commodityIds);
        for (String commodityId : commodityIds) {
            CommoditySpecAPI commodity = economy.getCommoditySpec(commodityId);
            if (canInclude(commodity)) {
                buttons.add(createContainer(commodity, activeId));
            }
        }
        VerticalViewContainer verticalViewContainer = new VerticalViewContainer(buttons);
        verticalViewContainer.setSize(size);
        return verticalViewContainer;
    }

    // TODO: Replace this with a Filter
    private boolean canInclude(CommoditySpecAPI commodity) {
        if (commodity.hasTag("nonecon")) {
            return false;
        }
        if (commodity.hasTag("meta")) {
            return false;
        }
        return true;
    }

    private Button createContainer(CommoditySpecAPI commodity, String activeId) {
        boolean isOn = commodity.getId().equals(activeId);
        return new CommodityButton(commodity, isOn);
    }

    private void sortCommodities(List<String> commodityIds) {
        Collections.sort(commodityIds, new Comparator<String>() {

            @Override
            public int compare(String stringA, String stringB) {
                CommoditySpecAPI commodityA = GlobalHelper.getCommoditySpec(stringA);
                CommoditySpecAPI commodityB = GlobalHelper.getCommoditySpec(stringB);
                return commodityA.getName().compareToIgnoreCase(commodityB.getName());
            }
        });
    }
}
