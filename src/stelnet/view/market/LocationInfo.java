package stelnet.view.market;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import stelnet.util.L10n;

@Data
@AllArgsConstructor
public class LocationInfo {

    private final String name;
    private final Color fgColor;
    private final Color bgColor;

    public LocationInfo(MarketAPI market) {
        name = market.getName();
        fgColor = market.getFaction().getBaseUIColor();
        bgColor = market.getFaction().getDarkUIColor();
    }

    public LocationInfo(SubmarketAPI submarket) {
        MarketAPI market = submarket.getMarket();
        FactionAPI faction = market.getFaction();
        name = L10n.get("viewLocationInfo", submarket.getNameOneLine(), market.getName(), faction.getDisplayName());
        fgColor = submarket.getFaction().getBaseUIColor();
        bgColor = submarket.getFaction().getDarkUIColor();
    }
}
