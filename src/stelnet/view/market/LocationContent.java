package stelnet.view.market;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import lombok.Data;

@Data
public class LocationContent {

    private final LocationInfo locationData;
    private final CargoAPI items;
    private final List<FleetMemberAPI> ships;
}
