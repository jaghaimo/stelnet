package stelnet.storage.data;

import java.awt.Color;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationData {

    private final String name;
    private final Color fgColor;
    private final Color bgColor;

    public LocationData(SubmarketAPI submarket) {
        name = submarket.getName();
        fgColor = submarket.getMarket().getFaction().getBaseUIColor();
        bgColor = submarket.getMarket().getFaction().getDarkUIColor();
    }
}
