package stelnet.commodity.element;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;

import stelnet.commodity.ui.Button;
import stelnet.commodity.ui.Renderable;
import stelnet.commodity.ui.Stack;
import stelnet.helper.GlobalHelper;

public class ButtonViewFactory {

    public Stack get(String activeId) {
        EconomyAPI economy = GlobalHelper.getEconomy();
        List<Renderable> buttons = new LinkedList<>();
        List<String> commodityIds = economy.getAllCommodityIds();
        sortCommodities(commodityIds);
        for (String commodityId : commodityIds) {
            CommoditySpecAPI commodity = economy.getCommoditySpec(commodityId);
            if (canInclude(commodity)) {
                buttons.add(get(commodity, activeId));
            }
        }
        return new Stack(buttons);
    }

    private boolean canInclude(CommoditySpecAPI commodity) {
        if (commodity.hasTag("nonecon")) {
            return false;
        }
        if (commodity.hasTag("meta")) {
            return false;
        }
        return true;
    }

    private Button get(CommoditySpecAPI commodity, String activeId) {
        boolean isOn = commodity.getId().equals(activeId);
        Button button = new CommodityButton(commodity, isOn);
        return button;
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
