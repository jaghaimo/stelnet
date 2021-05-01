package stelnet.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.Builder;
import lombok.Data;
import stelnet.commodity.data.Price;

@Data
@Builder
public class MarketApiWrapper {
    Price price;
    MarketAPI marketAPI;

    public float getPriceAmount() {
        return price.getPriceAmount(marketAPI);
    }
}
