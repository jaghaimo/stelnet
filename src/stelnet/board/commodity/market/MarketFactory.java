package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.market.price.Price;
import stelnet.filter.MarketNotHidden;
import stelnet.util.CollectionUtils;
import stelnet.util.EconomyUtils;

@RequiredArgsConstructor
public abstract class MarketFactory {

    protected final String commodityId;

    public List<MarketApiWrapper> createMarkets() {
        List<MarketAPI> markets = EconomyUtils.getMarkets();
        CollectionUtils.reduce(markets, new MarketNotHidden());
        filterMarkets(markets);
        sortMarkets(markets);
        return mapToWrapper(markets);
    }

    protected void sortMarkets(List<MarketAPI> markets) {
        Collections.sort(
            markets,
            new Comparator<MarketAPI>() {
                @Override
                public int compare(MarketAPI marketA, MarketAPI marketB) {
                    float priceA = getPriceAmount(marketA);
                    float priceB = getPriceAmount(marketB);
                    return (int) Math.signum(priceB - priceA);
                }
            }
        );
    }

    private List<MarketApiWrapper> mapToWrapper(List<MarketAPI> entities) {
        List<MarketApiWrapper> list = new ArrayList<>();
        for (MarketAPI marketAPI : entities) {
            MarketApiWrapper marketApiWrapper = MarketApiWrapper
                .builder()
                .marketAPI(marketAPI)
                .price(getPrice())
                .build();
            list.add(marketApiWrapper);
        }
        return list;
    }

    protected abstract Price getPrice();

    protected abstract float getPriceAmount(MarketAPI market);

    protected abstract void filterMarkets(List<MarketAPI> markets);
}
