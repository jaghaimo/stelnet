package stelnet.commodity.data;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class DummyPrice implements Price {

    @Override
    public float getPrice(MarketAPI market) {
        return 0;
    }
}
