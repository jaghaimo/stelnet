package stelnet.widget.viewer;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import stelnet.filter.CargoStackFilter;
import stelnet.filter.CargoStackIsNotCommodity;
import stelnet.filter.CargoStackIsNotFighterWing;
import stelnet.filter.CargoStackIsNotFighterWingRole;
import stelnet.filter.CargoStackIsNotMountSize;
import stelnet.filter.CargoStackIsNotOther;
import stelnet.filter.CargoStackIsNotWeapon;
import stelnet.filter.Filter;
import stelnet.filter.FleetMemberFilter;
import stelnet.filter.FleetMemberIsNotCapital;
import stelnet.filter.FleetMemberIsNotCarrier;
import stelnet.filter.FleetMemberIsNotCivilian;
import stelnet.filter.FleetMemberIsNotCruiser;
import stelnet.filter.FleetMemberIsNotDestroyer;
import stelnet.filter.FleetMemberIsNotFrigate;
import stelnet.filter.FleetMemberIsNotWarship;
import uilib.Renderable;
import uilib.Spacer;

@Getter
public class FilteringButtons {

    private final Renderable[] itemButtons;
    private final Renderable[] shipButtons;
    private final Set<Filter> itemFilters;
    private final Set<Filter> shipFilters;

    public FilteringButtons() {
        itemFilters = new HashSet<>();
        shipFilters = new HashSet<>();
        itemButtons =
            new Renderable[] {
                new ItemFilterButton(this, "viewFilterCommodities", new CargoStackIsNotCommodity()),
                new ItemFilterButton(this, "viewFilterWeapons", new CargoStackIsNotWeapon()),
                new ItemFilterButton(this, "viewFilterFighterWings", new CargoStackIsNotFighterWing()),
                new ItemFilterButton(this, "viewFilterOthers", new CargoStackIsNotOther()),
                new Spacer(20f),
                new ItemFilterButton(this, "viewFilterSmallMount", new CargoStackIsNotMountSize(WeaponSize.SMALL)),
                new ItemFilterButton(this, "viewFilterMediumMount", new CargoStackIsNotMountSize(WeaponSize.MEDIUM)),
                new ItemFilterButton(this, "viewFilterLargeMount", new CargoStackIsNotMountSize(WeaponSize.LARGE)),
                new Spacer(20f),
                new ItemFilterButton(this, "viewFilterFighters", new CargoStackIsNotFighterWingRole(WingRole.FIGHTER)),
                new ItemFilterButton(this, "viewFilterBombers", new CargoStackIsNotFighterWingRole(WingRole.BOMBER)),
                new ItemFilterButton(
                    this,
                    "viewFilterInterceptors",
                    new CargoStackIsNotFighterWingRole(WingRole.INTERCEPTOR)
                ),
            };
        shipButtons =
            new Renderable[] {
                new ShipFilterButton(this, "viewFilterFrigates", new FleetMemberIsNotFrigate()),
                new ShipFilterButton(this, "viewFilterDestroyers", new FleetMemberIsNotDestroyer()),
                new ShipFilterButton(this, "viewFilterCruisers", new FleetMemberIsNotCruiser()),
                new ShipFilterButton(this, "viewFilterCapitals", new FleetMemberIsNotCapital()),
                new Spacer(20f),
                new ShipFilterButton(this, "viewFilterWarships", new FleetMemberIsNotWarship()),
                new ShipFilterButton(this, "viewFilterCarriers", new FleetMemberIsNotCarrier()),
                new ShipFilterButton(this, "viewFilterCivilians", new FleetMemberIsNotCivilian()),
            };
    }

    public void addFilter(CargoStackFilter filter) {
        itemFilters.add(filter);
    }

    public void addFilter(FleetMemberFilter filter) {
        shipFilters.add(filter);
    }

    public void removeFilter(CargoStackFilter filter) {
        itemFilters.remove(filter);
    }

    public void removeFilter(FleetMemberFilter filter) {
        shipFilters.remove(filter);
    }
}
