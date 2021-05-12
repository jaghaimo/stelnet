package stelnet.market;

import com.fs.starfarer.api.campaign.CampaignClockAPI;

import lombok.Getter;
import stelnet.helper.GlobalSectorHelper;
import stelnet.market.filter.FilterManager;
import stelnet.market.filter.ImmutableFilterManager;

@Getter
public class IntelQuery {

    private final String createdDate;
    private final FilterManager filterManager;
    private final IntelProvider intelProvider;

    private long updatedDate;
    private boolean isEnabled;
    private IntelList managedIntels;

    public IntelQuery(IntelProvider ip, FilterManager fm) {
        CampaignClockAPI clock = GlobalSectorHelper.getCurrentClock();
        createdDate = clock.getDateString();
        updatedDate = clock.getTimestamp();
        isEnabled = true;
        filterManager = new ImmutableFilterManager(fm);
        managedIntels = ip.provide(filterManager);
        managedIntels.addIntel(isEnabled);
        intelProvider = ip;
    }

    public String getDescription() {
        String off = isEnabled() ? "" : " (disabled)";
        String stale = isStale() ? " (stale)" : "";
        return intelProvider.getDescription() + off + stale;
    }

    public String getResultCount() {
        int count = managedIntels.size();
        String intelOrIntels = count == 1 ? " result" : " results";
        return String.valueOf(count) + intelOrIntels;
    }

    public void disable() {
        isEnabled = false;
        managedIntels.removeIntel();
    }

    public void enable() {
        isEnabled = true;
        managedIntels.addIntel(isEnabled);
    }

    public boolean isActive() {
        return isEnabled() && !isStale();
    }

    public boolean isStale() {
        CampaignClockAPI current = GlobalSectorHelper.getCurrentClock();
        CampaignClockAPI lastUpdate = current.createClock(updatedDate);
        if (current.getCycle() != lastUpdate.getCycle()) {
            return true;
        }
        if (current.getMonth() != lastUpdate.getMonth()) {
            return true;
        }
        return false;
    }

    public void refresh() {
        managedIntels.removeIntel();
        managedIntels = intelProvider.provide(filterManager);
        managedIntels.addIntel(isEnabled);
        updatedDate = GlobalSectorHelper.getCurrentClock().getTimestamp();
    }

    public void toggle() {
        if (isEnabled()) {
            disable();
        } else {
            enable();
        }
    }
}
