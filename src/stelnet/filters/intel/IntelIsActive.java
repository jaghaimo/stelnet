package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

public class IntelIsActive extends IntelFilter {

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        return !(object.isEnding() || object.isEnded());
    }
}
