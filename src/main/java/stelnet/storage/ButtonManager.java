package stelnet.storage;

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
import stelnet.storage.view.CargoFilterButton;
import stelnet.storage.view.DisplayGroupButton;
import stelnet.storage.view.DisplayModeButton;
import stelnet.storage.view.FleetFilterButton;
import stelnet.ui.Padding;
import stelnet.ui.Renderable;

public class ButtonManager {

    private Renderable[] commonButtons = { //
            new DisplayGroupButton(), //
            new DisplayModeButton(), //
            new Padding(20f), // vertical spacer
    };

    private Renderable[] cargoButtons = { //
            new CargoFilterButton("Commodities", new IsNotCommodity()), //
            new CargoFilterButton("Weapons", new IsNotWeapon()), //
            new CargoFilterButton("Fighter Wings", new IsNotFighterWing()), //
            new CargoFilterButton("Others", new IsNotOther()), //
            new Padding(20f), // vertical spacer
            new CargoFilterButton("Small Mount", new IsNotMountSize(WeaponSize.SMALL)), //
            new CargoFilterButton("Medium Mount", new IsNotMountSize(WeaponSize.MEDIUM)), //
            new CargoFilterButton("Large Mount", new IsNotMountSize(WeaponSize.LARGE)), //
            new Padding(20f), // vertical spacer
            new CargoFilterButton("Fighters", new IsNotFighterWingRole(WingRole.FIGHTER)), //
            new CargoFilterButton("Bombers", new IsNotFighterWingRole(WingRole.BOMBER)), //
            new CargoFilterButton("Interceptors", new IsNotFighterWingRole(WingRole.INTERCEPTOR))//
    };

    private Renderable[] fleetButtons = { //
            new FleetFilterButton("Frigates", new IsNotFrigate()), //
            new FleetFilterButton("Destroyers", new IsNotDestroyer()), //
            new FleetFilterButton("Cruisers", new IsNotCruiser()), //
            new FleetFilterButton("Capitals", new IsNotCapital()), //
            new Padding(20f), // vertical spacer
            new FleetFilterButton("Warships", new IsNotWarship()), //
            new FleetFilterButton("Carriers", new IsNotCarrier()), //
            new FleetFilterButton("Civilians", new IsNotCivilian())//
    };

    public Renderable[] getCommonButtons() {
        return commonButtons;
    }

    public Renderable[] getCargoButtons() {
        return cargoButtons;
    }

    public Renderable[] getFleetButtons() {
        return fleetButtons;
    }
}
