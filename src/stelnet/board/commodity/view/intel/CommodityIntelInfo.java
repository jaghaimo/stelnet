package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityIntel;
import uilib.RenderableIntelInfo;

@RequiredArgsConstructor
public class CommodityIntelInfo implements RenderableIntelInfo {

    private final CommodityIntel intel;

    @Override
    public void render(TooltipMakerAPI info, Color bulletColor, Color titleColor) {
        info.addPara(intel.getTitle(), titleColor, 0);
        FactionAPI faction = intel.getMarket().getFaction();
        intel.bullet(info);
        info.addPara(
            "Faction: " + faction.getDisplayName(),
            3f,
            bulletColor,
            faction.getBaseUIColor(),
            faction.getDisplayName()
        );
        addPrice(info, bulletColor, "Buy at", intel.getBuyPrice(), intel.getSupplyPrice());
        addPrice(info, bulletColor, "Sell at", intel.getSellPrice(), intel.getDemandPrice());
        intel.unindent(info);
    }

    private void addPrice(TooltipMakerAPI info, Color bulletColor, String key, float oldPrice, float newPrice) {
        String oldPriceDgs = Misc.getDGSCredits(oldPrice);
        String newPriceDgs = Misc.getDGSCredits(newPrice);
        String displayedPrice = oldPriceDgs;
        String highlightedString = oldPriceDgs;
        if (intel.isDifferent(oldPrice, newPrice)) {
            displayedPrice = newPriceDgs + " (was " + oldPriceDgs + ")";
            highlightedString = newPriceDgs;
        }
        info.addPara(key + ": " + displayedPrice, 0f, bulletColor, Misc.getHighlightColor(), highlightedString);
    }
}
