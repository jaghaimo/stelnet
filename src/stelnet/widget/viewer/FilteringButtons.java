package stelnet.widget.viewer;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.cargostack.IsNotCommodity;
import stelnet.filter.cargostack.IsNotFighterWing;
import stelnet.filter.cargostack.IsNotFighterWingRole;
import stelnet.filter.cargostack.IsNotMountSize;
import stelnet.filter.cargostack.IsNotOther;
import stelnet.filter.cargostack.IsNotWeapon;
import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.filter.fleetmember.IsNotCapital;
import stelnet.filter.fleetmember.IsNotCarrier;
import stelnet.filter.fleetmember.IsNotCivilian;
import stelnet.filter.fleetmember.IsNotCruiser;
import stelnet.filter.fleetmember.IsNotDestroyer;
import stelnet.filter.fleetmember.IsNotFrigate;
import stelnet.filter.fleetmember.IsNotWarship;
import uilib.Renderable;
import uilib.Spacer;

@Getter
public class FilteringButtons {

    private final Renderable[] itemButtons;
    private final Renderable[] shipButtons;
    private final Set<CargoStackFilter> itemFilters;
    private final Set<FleetMemberFilter> shipFilters;

    public FilteringButtons() {
        itemFilters = new HashSet<>();
        shipFilters = new HashSet<>();
        itemButtons =
            new Renderable[] {
                new ItemFilterButton(this, "viewFilterCommodities", new IsNotCommodity()),
                new ItemFilterButton(this, "viewFilterWeapons", new IsNotWeapon()),
                new ItemFilterButton(this, "viewFilterFighterWings", new IsNotFighterWing()),
                new ItemFilterButton(this, "viewFilterOthers", new IsNotOther()),
                new Spacer(20f),
                new ItemFilterButton(this, "viewFilterSmallMount", new IsNotMountSize(WeaponSize.SMALL)),
                new ItemFilterButton(this, "viewFilterMediumMount", new IsNotMountSize(WeaponSize.MEDIUM)),
                new ItemFilterButton(this, "viewFilterLargeMount", new IsNotMountSize(WeaponSize.LARGE)),
                new Spacer(20f),
                new ItemFilterButton(this, "viewFilterFighters", new IsNotFighterWingRole(WingRole.FIGHTER)),
                new ItemFilterButton(this, "viewFilterBombers", new IsNotFighterWingRole(WingRole.BOMBER)),
                new ItemFilterButton(this, "viewFilterInterceptors", new IsNotFighterWingRole(WingRole.INTERCEPTOR)),
            };
        shipButtons =
            new Renderable[] {
                new ShipFilterButton(this, "viewFilterFrigates", new IsNotFrigate()),
                new ShipFilterButton(this, "viewFilterDestroyers", new IsNotDestroyer()),
                new ShipFilterButton(this, "viewFilterCruisers", new IsNotCruiser()),
                new ShipFilterButton(this, "viewFilterCapitals", new IsNotCapital()),
                new Spacer(20f),
                new ShipFilterButton(this, "viewFilterWarships", new IsNotWarship()),
                new ShipFilterButton(this, "viewFilterCarriers", new IsNotCarrier()),
                new ShipFilterButton(this, "viewFilterCivilians", new IsNotCivilian()),
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
