package stelnet.board.commodity.price;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public interface Price {
    float getUnitPrice(MarketAPI market);

    float getTotalPrice(MarketAPI market, int quantity);
}
