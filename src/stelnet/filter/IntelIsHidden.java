package stelnet.filter;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class IntelIsHidden extends IntelFilter {

    protected boolean acceptIntel(IntelInfoPlugin intel) {
        return intel.isHidden();
    }
}
