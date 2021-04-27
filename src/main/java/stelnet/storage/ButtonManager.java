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
import stelnet.storage.view.DisplayPaneButton;
import stelnet.storage.view.DisplayViewButton;
import stelnet.storage.view.ItemFilterButton;
import stelnet.storage.view.ShipFilterButton;
import stelnet.ui.Renderable;
import stelnet.ui.Spacer;

public class ButtonManager {

    @Getter
    private Renderable[] commonButtons = { //
            new DisplayViewButton(), //
            new DisplayPaneButton(), //
            new Spacer(20f), // vertical spacer
    };

    @Getter
    private Renderable[] itemButtons = { //
            new ItemFilterButton("Commodities", new IsNotCommodity()), //
            new ItemFilterButton("Weapons", new IsNotWeapon()), //
            new ItemFilterButton("Fighter Wings", new IsNotFighterWing()), //
            new ItemFilterButton("Others", new IsNotOther()), //
            new Spacer(20f), // vertical spacer
            new ItemFilterButton("Small Mount", new IsNotMountSize(WeaponSize.SMALL)), //
            new ItemFilterButton("Medium Mount", new IsNotMountSize(WeaponSize.MEDIUM)), //
            new ItemFilterButton("Large Mount", new IsNotMountSize(WeaponSize.LARGE)), //
            new Spacer(20f), // vertical spacer
            new ItemFilterButton("Fighters", new IsNotFighterWingRole(WingRole.FIGHTER)), //
            new ItemFilterButton("Bombers", new IsNotFighterWingRole(WingRole.BOMBER)), //
            new ItemFilterButton("Interceptors", new IsNotFighterWingRole(WingRole.INTERCEPTOR))//
    };

    @Getter
    private Renderable[] shipButtons = { //
            new ShipFilterButton("Frigates", new IsNotFrigate()), //
            new ShipFilterButton("Destroyers", new IsNotDestroyer()), //
            new ShipFilterButton("Cruisers", new IsNotCruiser()), //
            new ShipFilterButton("Capitals", new IsNotCapital()), //
            new Spacer(20f), // vertical spacer
            new ShipFilterButton("Warships", new IsNotWarship()), //
            new ShipFilterButton("Carriers", new IsNotCarrier()), //
            new ShipFilterButton("Civilians", new IsNotCivilian())//
    };
}
