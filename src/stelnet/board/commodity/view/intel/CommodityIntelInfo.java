package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;
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
        addPrice(
            info,
            bulletColor,
            CommodityL10n.INTEL_BUY_AT_INFO,
            new DisplayablePrice(intel, intel.getBuyPrice(), intel.getSupplyPrice())
        );
        addPrice(
            info,
            bulletColor,
            CommodityL10n.INTEL_SELL_FOR_INFO,
            new DisplayablePrice(intel, intel.getSellPrice(), intel.getDemandPrice())
        );
        intel.unindent(info);
    }

    private void addPrice(TooltipMakerAPI info, Color bulletColor, Enum<?> key, DisplayablePrice price) {
        info.addPara(
            L10n.get(key) + price.getDisplayedPrice(),
            0f,
            bulletColor,
            Misc.getHighlightColor(),
            price.getHighlightedString()
        );
    }
}
