package stelnet.commodity.extractor;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public interface Price {

    public float getPrice(MarketAPI market);
}
