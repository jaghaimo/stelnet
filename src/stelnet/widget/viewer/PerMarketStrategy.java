package stelnet.widget.viewer;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.util.CollectionUtils;
import stelnet.util.DistanceCalculator;
import stelnet.util.StelnetHelper;

@Log4j
/**
 * Displays storage content grouped by the market it is located at.
 */
public class PerMarketStrategy implements DisplayStrategy {

    @Override
    public List<LocationContent> getData(final ButtonManager filteringButtons) {
        final List<LocationContent> data = new LinkedList<>();
        final List<SubmarketAPI> storages = getAllSortedWithAccess();
        for (final SubmarketAPI storage : storages) {
            processSubmarket(new LocationInfo(storage.getMarket()), storage, filteringButtons, data);
        }
        return data;
    }

    protected void processSubmarket(
        final LocationInfo locationData,
        final SubmarketAPI storage,
        final ButtonManager filteringButtons,
        final List<LocationContent> data
    ) {
        final CargoAPI storageCargo = storage.getCargo();
        final CargoAPI items = getItems(filteringButtons, storageCargo);
        final List<FleetMemberAPI> ships = getShips(filteringButtons, storageCargo);
        final String name = storage.getMarket().getName();
        log.debug("Found " + items.getStacksCopy().size() + " items in " + name);
        log.debug("Found " + ships.size() + " ships in " + name);
        data.add(new LocationContent(locationData, items, ships));
    }

    private List<SubmarketAPI> getAllSortedWithAccess() {
        final List<SubmarketAPI> availableStorages = new LinkedList<>(StelnetHelper.getAllWithAccess());
        Collections.sort(
            availableStorages,
            new Comparator<SubmarketAPI>() {
                @Override
                public int compare(final SubmarketAPI s1, final SubmarketAPI s2) {
                    final float s1distance = DistanceCalculator.getDistanceToPlayerLY(
                        s1.getMarket().getPrimaryEntity()
                    );
                    final float s2distance = DistanceCalculator.getDistanceToPlayerLY(
                        s2.getMarket().getPrimaryEntity()
                    );
                    return Math.round(s1distance - s2distance);
                }
            }
        );
        return availableStorages;
    }

    private CargoAPI getItems(final ButtonManager filteringButtons, final CargoAPI storageCargo) {
        final CargoAPI items = storageCargo.createCopy();
        final List<CargoStackAPI> cargoStacks = storageCargo.getStacksCopy();
        CollectionUtils.reduce(cargoStacks, filteringButtons.getFilters());
        replaceCargoStacks(items, cargoStacks);
        return items;
    }

    private List<FleetMemberAPI> getShips(final ButtonManager filteringButtons, final CargoAPI storageCargo) {
        final List<FleetMemberAPI> ships = storageCargo.getMothballedShips().getMembersInPriorityOrder();
        CollectionUtils.reduce(ships, filteringButtons.getFilters());
        return ships;
    }

    private void replaceCargoStacks(final CargoAPI cargo, final List<CargoStackAPI> cargoStacks) {
        cargo.clear();
        for (final CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
    }
}
