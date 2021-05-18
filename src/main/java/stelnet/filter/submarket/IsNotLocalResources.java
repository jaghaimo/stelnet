package stelnet.filter.submarket;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;

public class IsNotLocalResources implements SubmarketFilter {

    public boolean accept(SubmarketAPI submarket) {
        String specId = submarket.getSpecId();
        return !Submarkets.LOCAL_RESOURCES.equals(specId);
    }
}
