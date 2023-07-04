package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityIntel;
import stelnet.util.L10n;
import uilib.RenderableIntelInfo;

@RequiredArgsConstructor
public class CommodityIntelInfo implements RenderableIntelInfo {

    private final CommodityIntel intel;

    @Override
    public void render(final TooltipMakerAPI info, final Color bulletColor, final Color titleColor) {
        info.addPara(intel.getTitle(), titleColor, 0);
        final FactionAPI faction = intel.getMarket().getFaction();
        intel.bullet(info);
        info.addPara(
            L10n.commodity("INTEL_INFO_FACTION", faction.getDisplayName()),
            3f,
            bulletColor,
            faction.getBaseUIColor(),
            faction.getDisplayName()
        );
        addPrice(
            info,
            bulletColor,
            "INTEL_INFO_BUY_AT",
            new DisplayablePrice(intel, intel.getBuyPrice(), intel.getSupplyPrice())
        );
        addPrice(
            info,
            bulletColor,
            "INTEL_INFO_SELL_FOR",
            new DisplayablePrice(intel, intel.getSellPrice(), intel.getDemandPrice())
        );
        intel.unindent(info);
    }

    private void addPrice(
        final TooltipMakerAPI info,
        final Color bulletColor,
        final String key,
        final DisplayablePrice price
    ) {
        info.addPara(
            L10n.commodity(key, price.getDisplayedPrice()),
            0f,
            bulletColor,
            Misc.getHighlightColor(),
            price.getHighlightedString()
        );
    }
}
