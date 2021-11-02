package stelnet.util;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.util.Misc;

public class DistanceCalculator {

    public static float getDistanceLY(SectorEntityToken entity1, SectorEntityToken entity2) {
        if (isInSameLocation(entity1, entity2)) {
            return Misc.getDistanceLY(entity1.getLocation(), entity2.getLocation());
        }
        float distance = Misc.getDistanceLY(entity1.getLocationInHyperspace(), entity2.getLocationInHyperspace());
        if (!entity1.isInHyperspace()) {
            distance += getDistanceToClosestJumpPoint(entity1);
        }
        if (!entity2.isInHyperspace()) {
            distance += getDistanceToClosestJumpPoint(entity2);
        }
        return distance;
    }

    public static float getDistanceToPlayerLY(SectorEntityToken entity) {
        CampaignFleetAPI player = Sector.getPlayerFleet();
        return Misc.getDistanceLY(player, entity);
    }

    private static boolean isInSameLocation(SectorEntityToken entity1, SectorEntityToken entity2) {
        LocationAPI entity1Location = entity1.getContainingLocation();
        LocationAPI entity2Location = entity2.getContainingLocation();
        if (entity1Location == null || entity2Location == null) {
            return false;
        }
        return entity1Location == entity2Location;
    }

    private static float getDistanceToClosestJumpPoint(SectorEntityToken entity) {
        JumpPointAPI jumpPoint = Misc.findNearestJumpPointTo(entity);
        if (jumpPoint == null) {
            return 0;
        }
        return Misc.getDistanceLY(jumpPoint.getLocation(), entity.getLocation());
    }
}
