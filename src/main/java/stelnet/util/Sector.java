package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

/**
 * Provides easy access to Global.getSector() methods.
 */
public class Sector {

    public static CampaignClockAPI getCurrentClock() {
        return getSector().getClock();
    }

    public static ListenerManagerAPI getListenerManager() {
        return getSector().getListenerManager();
    }

    public static FactionAPI getPlayerFaction() {
        return getSector().getPlayerFaction();
    }

    public static CampaignFleetAPI getPlayerFleet() {
        return getSector().getPlayerFleet();
    }

    public static SectorAPI getSector() {
        return Global.getSector();
    }
}
