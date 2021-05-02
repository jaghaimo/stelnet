package stelnet.commodity.market.price;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public interface Price {

    float getPriceAmount(MarketAPI market);
}
