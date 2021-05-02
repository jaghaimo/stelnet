package stelnet.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.commodity.market.price.Price;
import stelnet.filter.market.MarketNotHidden;
import stelnet.helper.CollectionHelper;
import stelnet.helper.GlobalHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class MarketFactory {

    protected String commodityId;

    public MarketFactory(String commodityId) {
        this.commodityId = commodityId;
    }

    public List<MarketApiWrapper> createMarkets() {
        List<MarketAPI> markets = GlobalHelper.getMarkets();
        CollectionHelper.reduce(markets, new MarketNotHidden());
        filterMarkets(markets);
        sortMarkets(markets);
        return mapToWrapper(markets);
    }

    private List<MarketApiWrapper> mapToWrapper(List<MarketAPI> entities) {
        List<MarketApiWrapper> list = new ArrayList<>();
        for (MarketAPI marketAPI : entities) {
            MarketApiWrapper marketApiWrapper = MarketApiWrapper.builder().marketAPI(marketAPI).price(getPrice()).build();
            list.add(marketApiWrapper);
        }
        return list;
    }

    protected abstract void filterMarkets(List<MarketAPI> markets);

    protected abstract void sortMarkets(List<MarketAPI> markets);

    protected abstract Price getPrice();
}
