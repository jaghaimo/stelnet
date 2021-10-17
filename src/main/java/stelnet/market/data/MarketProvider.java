package stelnet.market.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.storage.FilterManager;
import stelnet.storage.data.LocationData;
import stelnet.storage.data.PerLocationProvider;
import stelnet.storage.data.StorageData;
import stelnet.util.L10n;

@Log4j
@RequiredArgsConstructor
public class MarketProvider extends PerLocationProvider {

    private final MarketAPI market;

    @Override
    public List<StorageData> getData(FilterManager filterManager) {
        if (market == null) {
            log.debug("No market set, returning empty data.");
            return getEmptyData();
        }
        log.debug("Adding data for market " + market.getName());
        List<StorageData> data = new LinkedList<>();
        for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
            if (Submarkets.SUBMARKET_STORAGE.equals(submarket.getSpecId())) {
                log.debug("Skipping storage");
                continue;
            }
            log.debug("Processing submarket " + submarket.getNameOneLine());
            processSubmarket(new LocationData(submarket), submarket, filterManager, data);
        }
        return data;
    }

    private List<StorageData> getEmptyData() {
        return Collections.singletonList(new StorageData(
                new LocationData(L10n.get("marketViewNoMarket"), Misc.getTextColor(), Misc.getGrayColor()),
                Global.getFactory().createCargo(true), Collections.<FleetMemberAPI>emptyList()));
    }
}
