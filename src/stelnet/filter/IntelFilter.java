package stelnet.filter;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

public abstract class IntelFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof IntelInfoPlugin) {
            return acceptIntel((IntelInfoPlugin) object);
        }
        return super.accept(object);
    }

    protected abstract boolean acceptIntel(IntelInfoPlugin intel);
}
