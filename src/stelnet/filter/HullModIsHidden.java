package stelnet.filter;

import com.fs.starfarer.api.loading.HullModSpecAPI;

public class HullModIsHidden extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof HullModSpecAPI) {
            return acceptHullMod((HullModSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptHullMod(HullModSpecAPI hullMod) {
        return hullMod.isHidden();
    }
}
