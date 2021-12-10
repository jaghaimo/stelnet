package stelnet.board.commodity.price;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public interface Price {
    float getPriceAmount(MarketAPI market);

    float getPriceAmount(MarketAPI market, int quantity);
}
