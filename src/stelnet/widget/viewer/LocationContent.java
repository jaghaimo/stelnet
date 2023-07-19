package stelnet.widget.viewer;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LocationContent {

    private final LocationInfo locationInfo;
    private final CargoAPI items;
    private final List<FleetMemberAPI> ships;
}
