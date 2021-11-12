package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.List;

/**
 * Provides easy access to Global.getSector() methods.
 */
public class SectorUtils {

    public static FactionAPI getClaimingFaction(StarSystemAPI starSystem) {
        if (starSystem == null) {
            return getPlayerFaction();
        }
        List<PlanetAPI> planets = starSystem.getPlanets();
        if (planets.isEmpty()) {
            return getPlayerFaction();
        }
        FactionAPI claimingFaction = Misc.getClaimingFaction(planets.get(0));
        if (claimingFaction == null) {
            return getPlayerFaction();
        }
        return claimingFaction;
    }

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
