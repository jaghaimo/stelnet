package stelnet.storage.data;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import lombok.Data;

@Data
public class SubmarketData {

    private final LocationData locationData;
    private final CargoAPI items;
    private final List<FleetMemberAPI> ships;
}
