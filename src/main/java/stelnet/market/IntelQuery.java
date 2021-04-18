package stelnet.market;

import com.fs.starfarer.api.campaign.CampaignClockAPI;

import stelnet.helper.GlobalHelper;
import stelnet.market.filter.FilterManager;
import stelnet.market.filter.ImmutableFilterManager;

public class IntelQuery {

    private String createdDate;
    private long updatedDate;
    private boolean isEnabled;
    private FilterManager filterManager;
    private IntelList managedIntels;
    private IntelProvider intelProvider;

    public IntelQuery(IntelProvider ip, FilterManager fm) {
        CampaignClockAPI clock = GlobalHelper.getCurrentClock();
        createdDate = clock.getDateString();
        updatedDate = clock.getTimestamp();
        isEnabled = true;
        filterManager = new ImmutableFilterManager(fm);
        managedIntels = ip.provide(filterManager);
        managedIntels.addIntel(isEnabled);
        intelProvider = ip;
    }

    public String getCreatedDate() {
        return createdDate;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isStale() {
        CampaignClockAPI current = GlobalHelper.getCurrentClock();
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
        updatedDate = GlobalHelper.getCurrentClock().getTimestamp();
    }

    public void toggle() {
        if (isEnabled()) {
            disable();
        } else {
            enable();
        }
    }
}
