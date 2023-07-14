package stelnet.board.commodity2;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.contact2.FilterFactory;

@RequiredArgsConstructor
public class CommodityModel {

    private final IntelInfoPlugin intelToUpdate;

    @Getter
    private final List<Market> markets = new ArrayList<>();

    private final FilterFactory factory = new FilterFactory();

    public void update(final List<MarketAPI> marketList) {
        markets.clear();
        for (final MarketAPI market : marketList) {
            markets.add(new Market(market));
        }
    }

    public int getContactNumber() {
        return markets.size();
    }
}
