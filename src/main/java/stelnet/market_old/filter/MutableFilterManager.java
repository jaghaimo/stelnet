package stelnet.market_old.filter;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.cargostack.IsType;
import stelnet.filter.cargostack.IsWeaponSize;
import stelnet.filter.cargostack.IsWeaponType;
import stelnet.filter.cargostack.IsWingType;
import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.filter.fleetmember.IsCarrier;
import stelnet.filter.fleetmember.IsCivilian;
import stelnet.filter.fleetmember.IsDamaged;
import stelnet.filter.fleetmember.IsSize;
import stelnet.filter.market.HasAdministrator;
import stelnet.filter.market.HasOfficer;
import stelnet.filter.market.MarketFilter;
import stelnet.market_old.dialog.DialogOption;

@Getter
public class MutableFilterManager implements FilterManager {

    private DialogOption cargoType = DialogOption.CARGO_TYPE_WEAPON;
    private DialogOption cargoWeaponSize = DialogOption.WEAPON_SIZE_ANY;
    private DialogOption cargoWeaponType = DialogOption.WEAPON_TYPE_ANY;
    private DialogOption cargoWingType = DialogOption.WING_TYPE_ANY;

    private DialogOption fleetShipSize = DialogOption.SHIP_SIZE_FRIGATE;
    private DialogOption fleetShipDamaged = DialogOption.SHIP_DAMAGED_NO;
    private DialogOption fleetShipCarrier = DialogOption.SHIP_CARRIER_NO;
    private DialogOption fleetShipCivilian = DialogOption.SHIP_CIVILIAN_NO;

    private DialogOption staffType = DialogOption.STAFF_TYPE_OFFICER;
    private DialogOption staffOfficer = DialogOption.OFFICER_STEADY;

    public List<CargoStackFilter> listCargoFilters() {
        List<CargoStackFilter> filters = new ArrayList<CargoStackFilter>();
        filters.add(new IsType(cargoType));

        if (cargoType == DialogOption.CARGO_TYPE_WEAPON) {
            filters.add(new IsWeaponSize(cargoWeaponSize));
            filters.add(new IsWeaponType(cargoWeaponType));
        } else if (cargoType == DialogOption.CARGO_TYPE_FIGHTER) {
            filters.add(new IsWingType(cargoWingType));
        }

        return filters;
    }

    public List<FleetMemberFilter> listFleetFilters() {
        List<FleetMemberFilter> filters = new ArrayList<FleetMemberFilter>();
        filters.add(new IsSize(fleetShipSize));
        filters.add(new IsDamaged(fleetShipDamaged));
        filters.add(new IsCarrier(fleetShipCarrier));
        filters.add(new IsCivilian(fleetShipCivilian));

        return filters;
    }

    public List<MarketFilter> listStaffFilters() {
        List<MarketFilter> filters = new ArrayList<MarketFilter>();

        if (staffType == DialogOption.STAFF_TYPE_ADMIN) {
            filters.add(new HasAdministrator());
        } else {
            filters.add(new HasOfficer(extractStaffOfficerPersonality()));
        }

        return filters;
    }

    public void setCargoType(DialogOption option) {
        if (option.isType("CARGO_TYPE")) {
            cargoType = option.getNext();
        }
    }

    public void setCargoWeaponSize(DialogOption option) {
        if (option.isType("WEAPON_SIZE")) {
            cargoWeaponSize = option.getNext();
        }
    }

    public void setCargoWeaponType(DialogOption option) {
        if (option.isType("WEAPON_TYPE")) {
            cargoWeaponType = option.getNext();
        }
    }

    public void setCargoWingType(DialogOption option) {
        if (option.isType("WING_TYPE")) {
            cargoWingType = option.getNext();
        }
    }

    public void setFleetShipSize(DialogOption option) {
        if (option.isType("SHIP_SIZE")) {
            fleetShipSize = option.getNext();
        }
    }

    public void setFleetShipDamaged(DialogOption option) {
        if (option.isType("SHIP_DAMAGED")) {
            fleetShipDamaged = option.getNext();
        }
    }

    public void setFleetShipCarrier(DialogOption option) {
        if (option.isType("SHIP_CARRIER")) {
            fleetShipCarrier = option.getNext();
        }
    }

    public void setFleetShipCivilian(DialogOption option) {
        if (option.isType("SHIP_CIVILIAN")) {
            fleetShipCivilian = option.getNext();
        }
    }

    public void setStaffType(DialogOption option) {
        if (option.isType("STAFF_TYPE")) {
            staffType = option.getNext();
        }
    }

    public void setStaffOfficer(DialogOption option) {
        if (option.isType("OFFICER")) {
            staffOfficer = option.getNext();
        }
    }

    public String extractStaffOfficerPersonality() {
        return staffOfficer.name().substring(8).toLowerCase();
    }
}
