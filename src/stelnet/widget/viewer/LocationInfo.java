package stelnet.widget.viewer;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import java.awt.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import stelnet.util.L10n;

@Getter
@AllArgsConstructor
public class LocationInfo {

    private final String name;
    private final Color fgColor;
    private final Color bgColor;

    public LocationInfo(final MarketAPI market) {
        name = market.getName();
        fgColor = market.getFaction().getBaseUIColor();
        bgColor = market.getFaction().getDarkUIColor();
    }

    public LocationInfo(final SubmarketAPI submarket) {
        final MarketAPI market = submarket.getMarket();
        final FactionAPI faction = market.getFaction();
        name =
            L10n.widget("VIEWER_LOCATION_INFO", submarket.getNameOneLine(), market.getName(), faction.getDisplayName());
        fgColor = submarket.getFaction().getBaseUIColor();
        bgColor = submarket.getFaction().getDarkUIColor();
    }
}
