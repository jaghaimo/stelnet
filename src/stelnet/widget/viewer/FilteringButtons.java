package stelnet.widget.viewer;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import stelnet.CommonL10n;
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
                new ItemFilterButton(this, CommonL10n.COMMODITIES, new CargoStackIsNotCommodity()),
                new ItemFilterButton(this, CommonL10n.WEAPONS, new CargoStackIsNotWeapon()),
                new ItemFilterButton(this, CommonL10n.FIGHTER_WINGS, new CargoStackIsNotFighterWing()),
                new ItemFilterButton(this, CommonL10n.OTHERS, new CargoStackIsNotOther()),
                new Spacer(20f),
                new ItemFilterButton(this, CommonL10n.MOUNT_SMALL, new CargoStackIsNotMountSize(WeaponSize.SMALL)),
                new ItemFilterButton(this, CommonL10n.MOUNT_MEDIUM, new CargoStackIsNotMountSize(WeaponSize.MEDIUM)),
                new ItemFilterButton(this, CommonL10n.MOUNT_LARGE, new CargoStackIsNotMountSize(WeaponSize.LARGE)),
                new Spacer(20f),
                new ItemFilterButton(
                    this,
                    CommonL10n.WING_FIGHTERS,
                    new CargoStackIsNotFighterWingRole(WingRole.FIGHTER)
                ),
                new ItemFilterButton(
                    this,
                    CommonL10n.WING_BOMBERS,
                    new CargoStackIsNotFighterWingRole(WingRole.BOMBER)
                ),
                new ItemFilterButton(
                    this,
                    CommonL10n.WING_INTERCEPTORS,
                    new CargoStackIsNotFighterWingRole(WingRole.INTERCEPTOR)
                ),
            };
        shipButtons =
            new Renderable[] {
                new ShipFilterButton(this, CommonL10n.FRIGATES, new FleetMemberIsNotFrigate()),
                new ShipFilterButton(this, CommonL10n.DESTROYERS, new FleetMemberIsNotDestroyer()),
                new ShipFilterButton(this, CommonL10n.CRUISERS, new FleetMemberIsNotCruiser()),
                new ShipFilterButton(this, CommonL10n.CAPITALS, new FleetMemberIsNotCapital()),
                new Spacer(20f),
                new ShipFilterButton(this, CommonL10n.WARSHIPS, new FleetMemberIsNotWarship()),
                new ShipFilterButton(this, CommonL10n.CARRIERS, new FleetMemberIsNotCarrier()),
                new ShipFilterButton(this, CommonL10n.CIVILIANS, new FleetMemberIsNotCivilian()),
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
