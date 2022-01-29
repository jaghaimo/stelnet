package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;

public abstract class FactionFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof FactionAPI) {
            return acceptFaction((FactionAPI) object);
        }
        return super.accept(object);
    }

    protected abstract boolean acceptFaction(FactionAPI faction);
}
