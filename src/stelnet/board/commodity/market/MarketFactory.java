package stelnet.board.commodity.market;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.price.Price;
import stelnet.filter.MarketNotHidden;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;


@RequiredArgsConstructor
public abstract class MarketFactory {

    protected final String commodityId;

    public List<MarketAPI> createMarkets() {
        List<MarketAPI> markets = Global.getSector().getEconomy().getMarketsCopy();
        CollectionUtils.reduce(markets, Arrays.asList(new MarketNotHidden(), Excluder.getMarketFilter()));
        filterMarkets(markets);
        sortMarkets(markets);
        return markets;
    }

    protected void sortMarkets(List<MarketAPI> markets) {
        markets.sort((marketA, marketB) -> {
            float priceA = getPrice().getUnitPrice(marketA);
            float priceB = getPrice().getUnitPrice(marketB);
            return (int) Math.signum(priceB - priceA);
        });
    }

    protected abstract Price getPrice();

    protected abstract void filterMarkets(List<MarketAPI> markets);
}
