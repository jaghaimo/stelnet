package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.board.commodity.CommodityIntel;
import stelnet.util.L10n;

@Getter
public class DisplayablePrice {

    private final String displayedPrice;
    private final String highlightedString;

    public DisplayablePrice(final CommodityIntel intel, final float oldPrice, final float newPrice) {
        final String oldPriceDgs = Misc.getDGSCredits(oldPrice);
        final String newPriceDgs = Misc.getDGSCredits(newPrice);
        if (intel.isDifferent(oldPrice, newPrice)) {
            displayedPrice = L10n.commodity("PRICE_CHANGED", newPriceDgs, oldPriceDgs);
            highlightedString = newPriceDgs;
        } else {
            displayedPrice = oldPriceDgs;
            highlightedString = oldPriceDgs;
        }
    }
}
