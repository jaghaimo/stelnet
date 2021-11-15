package stelnet.widget.viewer;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.ShipHullSpecAPI.ShipTypeHints;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import stelnet.CommonL10n;
import stelnet.filter.CargoStackIsType;
import stelnet.filter.CargoStackIsType.Type;
import stelnet.filter.CargoStackWingIsRole;
import stelnet.filter.Filter;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalNot;
import stelnet.filter.ShipHullHasHint;
import stelnet.filter.ShipHullIsSize;
import stelnet.filter.WeaponIsSize;
import uilib.Renderable;
import uilib.Spacer;

@Getter
public class FilteringButtons {

    private final Renderable[] itemButtons;
    private final Renderable[] shipButtons;

    private final Set<Filter> filters = new HashSet<>();

    public FilteringButtons() {
        Filter isWeapon = new CargoStackIsType(Type.WEAPON);
        Filter isFighterWing = new CargoStackIsType(Type.FIGHTER);
        itemButtons =
            new Renderable[] {
                new ItemFilterButton(this, CommonL10n.COMMODITIES, new CargoStackIsType(Type.COMMODITY)),
                new ItemFilterButton(this, CommonL10n.WEAPONS, isWeapon),
                new ItemFilterButton(this, CommonL10n.FIGHTER_WINGS, isFighterWing),
                new ItemFilterButton(this, CommonL10n.OTHERS, new CargoStackIsType(Type.SPECIAL)),
                new Spacer(20f),
                new ItemFilterButton(
                    this,
                    CommonL10n.MOUNT_SMALL,
                    new LogicalAnd(Arrays.<Filter>asList(isWeapon, new WeaponIsSize(WeaponSize.SMALL)))
                ),
                new ItemFilterButton(
                    this,
                    CommonL10n.MOUNT_MEDIUM,
                    new LogicalAnd(Arrays.<Filter>asList(isWeapon, new WeaponIsSize(WeaponSize.MEDIUM)))
                ),
                new ItemFilterButton(
                    this,
                    CommonL10n.MOUNT_LARGE,
                    new LogicalAnd(Arrays.<Filter>asList(isWeapon, new WeaponIsSize(WeaponSize.LARGE)))
                ),
                new Spacer(20f),
                new ItemFilterButton(
                    this,
                    CommonL10n.WING_FIGHTERS,
                    new LogicalAnd(Arrays.<Filter>asList(isFighterWing, new CargoStackWingIsRole(WingRole.FIGHTER)))
                ),
                new ItemFilterButton(
                    this,
                    CommonL10n.WING_BOMBERS,
                    new LogicalAnd(Arrays.<Filter>asList(isFighterWing, new CargoStackWingIsRole(WingRole.BOMBER)))
                ),
                new ItemFilterButton(
                    this,
                    CommonL10n.WING_INTERCEPTORS,
                    new LogicalAnd(Arrays.<Filter>asList(isFighterWing, new CargoStackWingIsRole(WingRole.INTERCEPTOR)))
                ),
            };
        shipButtons =
            new Renderable[] {
                new ShipFilterButton(this, CommonL10n.FRIGATES, new ShipHullIsSize(HullSize.FRIGATE)),
                new ShipFilterButton(this, CommonL10n.DESTROYERS, new ShipHullIsSize(HullSize.DESTROYER)),
                new ShipFilterButton(this, CommonL10n.CRUISERS, new ShipHullIsSize(HullSize.CRUISER)),
                new ShipFilterButton(this, CommonL10n.CAPITALS, new ShipHullIsSize(HullSize.CAPITAL_SHIP)),
                new Spacer(20f),
                new ShipFilterButton(this, CommonL10n.CARRIERS, new ShipHullHasHint(ShipTypeHints.CARRIER)),
                new ShipFilterButton(this, CommonL10n.CIVILIANS, new ShipHullHasHint(ShipTypeHints.CIVILIAN)),
            };
    }

    public void add(Filter filter) {
        filters.add(filter);
    }

    public Set<Filter> getFilters() {
        Set<Filter> negatedFilters = new HashSet<>();
        for (Filter filter : filters) {
            negatedFilters.add(new LogicalNot(filter));
        }
        return negatedFilters;
    }

    public void remove(Filter filter) {
        filters.remove(filter);
    }
}
