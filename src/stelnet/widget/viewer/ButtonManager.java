package stelnet.widget.viewer;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.ShipHullSpecAPI.ShipTypeHints;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import stelnet.filter.*;
import stelnet.filter.CargoStackIsType.Type;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.Spacer;

@Getter
public class ButtonManager {

    private final Renderable[] itemButtons;
    private final Renderable[] shipButtons;

    private final Set<Filter> filters = new HashSet<>();

    public ButtonManager() {
        final Filter isWeapon = new CargoStackIsType(Type.WEAPON);
        final Filter isFighterWing = new CargoStackIsType(Type.FIGHTER);
        itemButtons =
            new Renderable[] {
                new Spacer(2),
                new ItemFilterButton(this, L10n.common("COMMODITIES"), new CargoStackIsType(Type.COMMODITY)),
                new ItemFilterButton(this, L10n.common("WEAPONS"), isWeapon),
                new ItemFilterButton(this, L10n.common("FIGHTER_WINGS"), isFighterWing),
                new ItemFilterButton(this, L10n.common("OTHERS"), new CargoStackIsType(Type.SPECIAL)),
                new Spacer(20),
                new ItemFilterButton(
                    this,
                    L10n.common("MOUNT_SMALL"),
                    new LogicalAnd(Arrays.asList(isWeapon, new WeaponIsSize(WeaponSize.SMALL)))
                ),
                new ItemFilterButton(
                    this,
                    L10n.common("MOUNT_MEDIUM"),
                    new LogicalAnd(Arrays.asList(isWeapon, new WeaponIsSize(WeaponSize.MEDIUM)))
                ),
                new ItemFilterButton(
                    this,
                    L10n.common("MOUNT_LARGE"),
                    new LogicalAnd(Arrays.asList(isWeapon, new WeaponIsSize(WeaponSize.LARGE)))
                ),
                new Spacer(20f),
                new ItemFilterButton(
                    this,
                    L10n.common("WING_FIGHTERS"),
                    new LogicalAnd(Arrays.asList(isFighterWing, new CargoStackWingIsRole(WingRole.FIGHTER)))
                ),
                new ItemFilterButton(
                    this,
                    L10n.common("WING_BOMBERS"),
                    new LogicalAnd(Arrays.asList(isFighterWing, new CargoStackWingIsRole(WingRole.BOMBER)))
                ),
                new ItemFilterButton(
                    this,
                    L10n.common("WING_INTERCEPTORS"),
                    new LogicalAnd(Arrays.asList(isFighterWing, new CargoStackWingIsRole(WingRole.INTERCEPTOR)))
                ),
            };
        shipButtons =
            new Renderable[] {
                new Spacer(2),
                new ShipFilterButton(this, L10n.common("FRIGATES"), new ShipHullIsSize(HullSize.FRIGATE)),
                new ShipFilterButton(this, L10n.common("DESTROYERS"), new ShipHullIsSize(HullSize.DESTROYER)),
                new ShipFilterButton(this, L10n.common("CRUISERS"), new ShipHullIsSize(HullSize.CRUISER)),
                new ShipFilterButton(this, L10n.common("CAPITALS"), new ShipHullIsSize(HullSize.CAPITAL_SHIP)),
                new Spacer(20),
                new ShipFilterButton(this, L10n.common("CARRIERS"), new ShipHullHasHint(ShipTypeHints.CARRIER)),
                new ShipFilterButton(this, L10n.common("CIVILIANS"), new ShipHullHasHint(ShipTypeHints.CIVILIAN)),
            };
    }

    public void add(final Filter filter) {
        filters.add(filter);
    }

    public Set<Filter> getFilters() {
        final Set<Filter> negatedFilters = new HashSet<>();
        for (final Filter filter : filters) {
            negatedFilters.add(new LogicalNot(filter));
        }
        return negatedFilters;
    }

    public void remove(final Filter filter) {
        filters.remove(filter);
    }
}
