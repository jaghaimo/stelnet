package stelnet.storage;

import java.util.Arrays;
import java.util.List;

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
import stelnet.storage.element.CargoFilterButton;
import stelnet.storage.element.FilteringButton;
import stelnet.storage.element.FleetFilterButton;

public class ButtonManager {

    private List<FilteringButton> cargoButtons;
    private List<FilteringButton> fleetButtons;

    public ButtonManager() {
        cargoButtons = Arrays.<FilteringButton>asList(//
                new CargoFilterButton("Commodities", new IsNotCommodity()), //
                new CargoFilterButton("Weapons", new IsNotWeapon()), //
                new CargoFilterButton("Fighter Wings", new IsNotFighterWing()), //
                new CargoFilterButton("Others", new IsNotOther()), //
                null, //
                new CargoFilterButton("Small Mount", new IsNotMountSize(WeaponSize.SMALL)), //
                new CargoFilterButton("Medium Mount", new IsNotMountSize(WeaponSize.MEDIUM)), //
                new CargoFilterButton("Large Mount", new IsNotMountSize(WeaponSize.LARGE)), //
                null, //
                new CargoFilterButton("Fighters", new IsNotFighterWingRole(WingRole.FIGHTER)), //
                new CargoFilterButton("Bombers", new IsNotFighterWingRole(WingRole.BOMBER)), //
                new CargoFilterButton("Interceptors", new IsNotFighterWingRole(WingRole.INTERCEPTOR))//
        );
        fleetButtons = Arrays.<FilteringButton>asList(//
                new FleetFilterButton("Frigates", new IsNotFrigate()), //
                new FleetFilterButton("Destroyers", new IsNotDestroyer()), //
                new FleetFilterButton("Cruisers", new IsNotCruiser()), //
                new FleetFilterButton("Capitals", new IsNotCapital()), //
                null, //
                new FleetFilterButton("Warships", new IsNotWarship()), //
                new FleetFilterButton("Carriers", new IsNotCarrier()), //
                new FleetFilterButton("Civilians", new IsNotCivilian())//
        );
    }

    public List<FilteringButton> getCargoButtons() {
        return cargoButtons;
    }

    public List<FilteringButton> getFleetButtons() {
        return fleetButtons;
    }
}
