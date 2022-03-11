package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.price.Price;
import stelnet.filter.MarketNotHidden;
import stelnet.util.CollectionUtils;
import stelnet.util.EconomyUtils;

@RequiredArgsConstructor
public abstract class MarketFactory {

    protected final String commodityId;

    public List<MarketAPI> createMarkets() {
        List<MarketAPI> markets = EconomyUtils.getMarkets();
        CollectionUtils.reduce(markets, new MarketNotHidden());
        filterMarkets(markets);
        sortMarkets(markets);
        return markets;
    }

    protected void sortMarkets(List<MarketAPI> markets) {
        Collections.sort(
            markets,
            new Comparator<MarketAPI>() {
                @Override
                public int compare(MarketAPI marketA, MarketAPI marketB) {
                    float priceA = getPrice().getUnitPrice(marketA);
                    float priceB = getPrice().getUnitPrice(marketB);
                    return (int) Math.signum(priceB - priceA);
                }
            }
        );
    }

    protected abstract Price getPrice();

    protected abstract void filterMarkets(List<MarketAPI> markets);
}
