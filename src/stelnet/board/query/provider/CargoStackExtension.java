package stelnet.board.query.provider;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class CargoStackExtension {

    public static CargoStackAPI asCargoStack(Object object) {
        if (object instanceof FighterWingSpecAPI) {
            return asCargoStack((FighterWingSpecAPI) object);
        }
        if (object instanceof HullModSpecAPI) {
            return asCargoStack((HullModSpecAPI) object);
        }
        if (object instanceof WeaponSpecAPI) {
            return asCargoStack((WeaponSpecAPI) object);
        }
        return null;
    }

    public static CargoStackAPI asCargoStack(FighterWingSpecAPI fighterWing) {
        String fighterId = fighterWing.getId();
        CargoStackAPI stack = Global.getFactory().createCargoStack(CargoItemType.FIGHTER_CHIP, fighterId, null);
        return getValidStack(stack);
    }

    public static CargoStackAPI asCargoStack(HullModSpecAPI hullMod) {
        String hullModId = hullMod.getId();
        SpecialItemData specialItemData = new SpecialItemData(Items.TAG_MODSPEC, hullModId);
        CargoStackAPI stack = Global.getFactory().createCargoStack(CargoItemType.SPECIAL, specialItemData, null);
        return getValidStack(stack);
    }

    public static CargoStackAPI asCargoStack(WeaponSpecAPI weaponSpec) {
        String weaponId = weaponSpec.getWeaponId();
        CargoStackAPI stack = Global.getFactory().createCargoStack(CargoItemType.WEAPONS, weaponId, null);
        return getValidStack(stack);
    }

    public static String getManufacturer(Object object) {
        if (object instanceof FighterWingSpecAPI) {
            return getManufacturer((FighterWingSpecAPI) object);
        }
        if (object instanceof WeaponSpecAPI) {
            return getManufacturer((WeaponSpecAPI) object);
        }
        return null;
    }

    public static String getManufacturer(FighterWingSpecAPI fighterWing) {
        return fighterWing.getVariant().getHullSpec().getManufacturer();
    }

    public static String getManufacturer(WeaponSpecAPI weaponSpec) {
        return weaponSpec.getManufacturer();
    }

    private static CargoStackAPI getValidStack(CargoStackAPI stack) {
        stack.setSize(1);
        return stack;
    }
}
