package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.market.price.Price;
import stelnet.filter.market.MarketNotHidden;
import stelnet.util.CollectionReducer;
import stelnet.util.Economy;

@RequiredArgsConstructor
public abstract class MarketFactory {

    protected final String commodityId;

    public List<MarketApiWrapper> createMarkets() {
        List<MarketAPI> markets = Economy.getMarkets();
        CollectionReducer.reduce(markets, new MarketNotHidden());
        filterMarkets(markets);
        sortMarkets(markets);
        return mapToWrapper(markets);
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

    protected abstract void filterMarkets(List<MarketAPI> markets);

    protected abstract void sortMarkets(List<MarketAPI> markets);

    protected abstract Price getPrice();
}
