package stelnet.board.query;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import lombok.Data;

@Data
public class Result {

    private final String name;
    private final String description;
    private final MarketAPI market;
    private final SubmarketAPI submarket;
    private final int quantity;
}
