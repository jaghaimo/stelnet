package stelnet.board.commodity.view.intel;

import com.fs.starfarer.api.util.Misc;
import lombok.Getter;
import stelnet.board.commodity.CommodityIntel;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;

@Getter
public class DisplayablePrice {

    private final String displayedPrice;
    private final String highlightedString;

    public DisplayablePrice(CommodityIntel intel, float oldPrice, float newPrice) {
        String oldPriceDgs = Misc.getDGSCredits(oldPrice);
        String newPriceDgs = Misc.getDGSCredits(newPrice);
        if (intel.isDifferent(oldPrice, newPrice)) {
            displayedPrice = L10n.get(CommodityL10n.PRICE_CHANGED, newPriceDgs, oldPriceDgs);
            highlightedString = newPriceDgs;
        } else {
            displayedPrice = oldPriceDgs;
            highlightedString = oldPriceDgs;
        }
    }
}
