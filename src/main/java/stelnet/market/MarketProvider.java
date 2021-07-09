package stelnet.market;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import lombok.RequiredArgsConstructor;
import stelnet.storage.FilterManager;
import stelnet.storage.data.DataProvider;
import stelnet.storage.data.LocationData;
import stelnet.storage.data.StorageData;

@RequiredArgsConstructor
public class MarketProvider implements DataProvider {

    private final MarketAPI market;

    @Override
    public List<StorageData> getData(FilterManager filterManager) {
        List<StorageData> storageData = new LinkedList<>();
        for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
            storageData.add(
                    new StorageData(
                            new LocationData(submarket),
                            submarket.getCargo(),
                            submarket.getCargo().getMothballedShips().getMembersListCopy()
                    )
            );
        }
        return storageData;
    }
}
