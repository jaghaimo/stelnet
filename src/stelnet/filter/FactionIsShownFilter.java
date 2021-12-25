package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class FactionIsShownFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof FactionAPI) {
            return ((FactionAPI) object).isShowInIntelTab();
        }
        return false;
    }
}
