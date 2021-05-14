package stelnet.commodity.market;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import lombok.RequiredArgsConstructor;
import stelnet.commodity.market.price.Price;
import stelnet.filter.market.MarketNotHidden;
import stelnet.helper.CollectionHelper;
import stelnet.helper.GlobalSectorHelper;

@RequiredArgsConstructor
public abstract class MarketFactory {

    protected final String commodityId;

    public List<MarketApiWrapper> createMarkets() {
        List<MarketAPI> markets = GlobalSectorHelper.getMarkets();
        CollectionHelper.reduce(markets, new MarketNotHidden());
        filterMarkets(markets);
        sortMarkets(markets);
        return mapToWrapper(markets);
    }

    private List<MarketApiWrapper> mapToWrapper(List<MarketAPI> entities) {
        List<MarketApiWrapper> list = new ArrayList<>();
        for (MarketAPI marketAPI : entities) {
            MarketApiWrapper marketApiWrapper = MarketApiWrapper.builder()
                .marketAPI(marketAPI)
                .price(getPrice())
                .build();
            list.add(marketApiWrapper);
        }
        return list;
    }

    protected abstract void filterMarkets(List<MarketAPI> markets);

    protected abstract void sortMarkets(List<MarketAPI> markets);

    protected abstract Price getPrice();
}
