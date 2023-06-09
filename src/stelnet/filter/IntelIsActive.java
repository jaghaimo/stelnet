package stelnet.filter;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

public class IntelIsActive extends IntelFilter {

    @Override
    protected boolean acceptIntel(IntelInfoPlugin intel) {
        return !(intel.isEnding() || intel.isEnded());
    }
}
