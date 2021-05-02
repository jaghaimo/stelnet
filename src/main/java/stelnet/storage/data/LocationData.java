package stelnet.storage.data;

import java.awt.Color;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationData {

    private final String name;
    private final Color fgColor;
    private final Color bgColor;

    public LocationData(MarketAPI market) {
        name = market.getName();
        fgColor = market.getFaction().getBaseUIColor();
        bgColor = market.getFaction().getDarkUIColor();
    }
}
