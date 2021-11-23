package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;

public class FactionIsShownFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof FactionAPI) {
            return ((FactionAPI) object).isShowInIntelTab();
        }
        return false;
    }
}
