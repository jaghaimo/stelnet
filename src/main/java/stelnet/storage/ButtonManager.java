package stelnet.storage;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;

import lombok.Getter;
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
import stelnet.storage.view.ItemFilterButton;
import stelnet.storage.view.ShipFilterButton;
import uilib.Renderable;
import uilib.Spacer;

@Getter
public class ButtonManager {

        private final FilterManager filterManager;
        private final Renderable[] itemButtons;
        private final Renderable[] shipButtons;

        public ButtonManager(FilterManager filterManager) {
                this.filterManager = filterManager;
                this.itemButtons = new Renderable[] {
                                new ItemFilterButton(filterManager, "storageFilterCommodities", new IsNotCommodity()),
                                new ItemFilterButton(filterManager, "storageFilterWeapons", new IsNotWeapon()),
                                new ItemFilterButton(filterManager, "storageFilterFighterWings",
                                                new IsNotFighterWing()),
                                new ItemFilterButton(filterManager, "storageFilterOthers", new IsNotOther()),
                                new Spacer(20f),
                                new ItemFilterButton(filterManager, "storageFilterSmallMount",
                                                new IsNotMountSize(WeaponSize.SMALL)),
                                new ItemFilterButton(filterManager, "storageFilterMediumMount",
                                                new IsNotMountSize(WeaponSize.MEDIUM)),
                                new ItemFilterButton(filterManager, "storageFilterLargeMount",
                                                new IsNotMountSize(WeaponSize.LARGE)),
                                new Spacer(20f),
                                new ItemFilterButton(filterManager, "storageFilterFighters",
                                                new IsNotFighterWingRole(WingRole.FIGHTER)),
                                new ItemFilterButton(filterManager, "storageFilterBombers",
                                                new IsNotFighterWingRole(WingRole.BOMBER)),
                                new ItemFilterButton(filterManager, "storageFilterInterceptors",
                                                new IsNotFighterWingRole(WingRole.INTERCEPTOR)) };
                this.shipButtons = new Renderable[] {
                                new ShipFilterButton(filterManager, "storageFilterFrigates", new IsNotFrigate()),
                                new ShipFilterButton(filterManager, "storageFilterDestroyers", new IsNotDestroyer()),
                                new ShipFilterButton(filterManager, "storageFilterCruisers", new IsNotCruiser()),
                                new ShipFilterButton(filterManager, "storageFilterCapitals", new IsNotCapital()),
                                new Spacer(20f),
                                new ShipFilterButton(filterManager, "storageFilterWarships", new IsNotWarship()),
                                new ShipFilterButton(filterManager, "storageFilterCarriers", new IsNotCarrier()),
                                new ShipFilterButton(filterManager, "storageFilterCivilians", new IsNotCivilian()) };
        }
}
