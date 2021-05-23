package stelnet.storage;

import java.util.Arrays;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WingRole;

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
import stelnet.storage.view.DisplayViewButton;
import stelnet.storage.view.ItemFilterButton;
import stelnet.storage.view.ShipFilterButton;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Spacer;

public class ButtonManager {

    private final AbstractRenderable[] commonButtons = {
            new DisplayViewButton(StorageView.UNIFIED, true),
            new DisplayViewButton(StorageView.PER_LOCATION, false),
            new Spacer(20f),
    };

    private final AbstractRenderable[] itemButtons = {
            new ItemFilterButton("storageFilterCommodities", new IsNotCommodity()),
            new ItemFilterButton("storageFilterWeapons", new IsNotWeapon()),
            new ItemFilterButton("storageFilterFighterWings", new IsNotFighterWing()),
            new ItemFilterButton("storageFilterOthers", new IsNotOther()),
            new Spacer(20f),
            new ItemFilterButton("storageFilterSmallMount", new IsNotMountSize(WeaponSize.SMALL)),
            new ItemFilterButton("storageFilterMediumMount", new IsNotMountSize(WeaponSize.MEDIUM)),
            new ItemFilterButton("storageFilterLargeMount", new IsNotMountSize(WeaponSize.LARGE)),
            new Spacer(20f),
            new ItemFilterButton("storageFilterFighters", new IsNotFighterWingRole(WingRole.FIGHTER)),
            new ItemFilterButton("storageFilterBombers", new IsNotFighterWingRole(WingRole.BOMBER)),
            new ItemFilterButton("storageFilterInterceptors", new IsNotFighterWingRole(WingRole.INTERCEPTOR))
    };

    private final AbstractRenderable[] shipButtons = {
            new ShipFilterButton("storageFilterFrigates", new IsNotFrigate()),
            new ShipFilterButton("storageFilterDestroyers", new IsNotDestroyer()),
            new ShipFilterButton("storageFilterCruisers", new IsNotCruiser()),
            new ShipFilterButton("storageFilterCapitals", new IsNotCapital()),
            new Spacer(20f),
            new ShipFilterButton("storageFilterWarships", new IsNotWarship()),
            new ShipFilterButton("storageFilterCarriers", new IsNotCarrier()),
            new ShipFilterButton("storageFilterCivilians", new IsNotCivilian())
    };

    public AbstractRenderable[] getItemButtons() {
        return getCombined(commonButtons, itemButtons);
    }

    public AbstractRenderable[] getShipButtons() {
        return getCombined(commonButtons, shipButtons);
    }

    private AbstractRenderable[] getCombined(AbstractRenderable[] common, AbstractRenderable[] buttons) {
        AbstractRenderable[] all = Arrays.copyOf(common, common.length + buttons.length);
        System.arraycopy(buttons, 0, all, common.length, buttons.length);
        return all;
    }
}
