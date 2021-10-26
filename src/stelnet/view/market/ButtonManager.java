package stelnet.view.market;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;

import lombok.Getter;
import stelnet.board.storage.view.ItemFilterButton;
import stelnet.board.storage.view.ShipFilterButton;
import stelnet.filter.cargostack.IsNotCommodity;
import stelnet.filter.cargostack.IsNotFighterWing;
import stelnet.filter.cargostack.IsNotFighterWingRole;
import stelnet.filter.cargostack.IsNotMountSize;
import stelnet.filter.cargostack.IsNotOther;
import stelnet.filter.cargostack.IsNotWeapon;
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
public class ButtonManager {

    private final FilterManager filterManager;
    private final Renderable[] itemButtons;
    private final Renderable[] shipButtons;

    public ButtonManager(FilterManager filterManager) {
        this.filterManager = filterManager;
        this.itemButtons =
            new Renderable[] {
                new ItemFilterButton(filterManager, "viewFilterCommodities", new IsNotCommodity()),
                new ItemFilterButton(filterManager, "viewFilterWeapons", new IsNotWeapon()),
                new ItemFilterButton(filterManager, "viewFilterFighterWings", new IsNotFighterWing()),
                new ItemFilterButton(filterManager, "viewFilterOthers", new IsNotOther()),
                new Spacer(20f),
                new ItemFilterButton(filterManager, "viewFilterSmallMount", new IsNotMountSize(WeaponSize.SMALL)),
                new ItemFilterButton(filterManager, "viewFilterMediumMount", new IsNotMountSize(WeaponSize.MEDIUM)),
                new ItemFilterButton(filterManager, "viewFilterLargeMount", new IsNotMountSize(WeaponSize.LARGE)),
                new Spacer(20f),
                new ItemFilterButton(filterManager, "viewFilterFighters", new IsNotFighterWingRole(WingRole.FIGHTER)),
                new ItemFilterButton(filterManager, "viewFilterBombers", new IsNotFighterWingRole(WingRole.BOMBER)),
                new ItemFilterButton(
                    filterManager,
                    "viewFilterInterceptors",
                    new IsNotFighterWingRole(WingRole.INTERCEPTOR)
                ),
            };
        this.shipButtons =
            new Renderable[] {
                new ShipFilterButton(filterManager, "viewFilterFrigates", new IsNotFrigate()),
                new ShipFilterButton(filterManager, "viewFilterDestroyers", new IsNotDestroyer()),
                new ShipFilterButton(filterManager, "viewFilterCruisers", new IsNotCruiser()),
                new ShipFilterButton(filterManager, "viewFilterCapitals", new IsNotCapital()),
                new Spacer(20f),
                new ShipFilterButton(filterManager, "viewFilterWarships", new IsNotWarship()),
                new ShipFilterButton(filterManager, "viewFilterCarriers", new IsNotCarrier()),
                new ShipFilterButton(filterManager, "viewFilterCivilians", new IsNotCivilian()),
            };
    }
}
