package stelnet.market;

import lombok.Getter;
import stelnet.L10n;
import stelnet.helper.GlobalSectorHelper;
import stelnet.market.filter.FilterManager;
import stelnet.market.filter.ImmutableFilterManager;

@Getter
public class IntelQuery {

    private final String createdDate;
    private final FilterManager filterManager;
    private final IntelProvider intelProvider;
    private final IntelList managedIntels;
    private boolean isEnabled = true;

    public IntelQuery(IntelProvider ip, FilterManager fm) {
        createdDate = GlobalSectorHelper.getCurrentClock().getDateString();
        filterManager = new ImmutableFilterManager(fm);
        managedIntels = ip.provide(filterManager);
        managedIntels.addIntel(isEnabled);
        intelProvider = ip;
    }

    public String getDescription() {
        String off = isEnabled() ? "" : " (off)";
        return intelProvider.getDescription() + off;
    }

    public String getResultCount() {
        int count = managedIntels.available();
        int total = managedIntels.size();
        return L10n.get("marketResultCount", count, total);
    }

    public void disable() {
        isEnabled = false;
        managedIntels.removeIntel();
    }

    public void enable() {
        isEnabled = true;
        managedIntels.addIntel(isEnabled);
    }

    public void refresh() {
        managedIntels.removeIntel();
        managedIntels.clear();
        managedIntels.addAll(intelProvider.provide(filterManager));
        managedIntels.addIntel(isEnabled);
    }

    public void toggle() {
        if (isEnabled()) {
            disable();
        } else {
            enable();
        }
    }
}
