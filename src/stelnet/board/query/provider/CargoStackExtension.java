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

    public static CargoStackAPI asCargoStack(final Object object) {
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

    public static CargoStackAPI asCargoStack(final FighterWingSpecAPI fighterWing) {
        final String fighterId = fighterWing.getId();
        final CargoStackAPI stack = Global.getFactory().createCargoStack(CargoItemType.FIGHTER_CHIP, fighterId, null);
        return getValidStack(stack);
    }

    public static CargoStackAPI asCargoStack(final HullModSpecAPI hullMod) {
        final String hullModId = hullMod.getId();
        final SpecialItemData specialItemData = new SpecialItemData(Items.MODSPEC, hullModId);
        final CargoStackAPI stack = Global.getFactory().createCargoStack(CargoItemType.SPECIAL, specialItemData, null);
        return getValidStack(stack);
    }

    public static CargoStackAPI asCargoStack(final WeaponSpecAPI weaponSpec) {
        final String weaponId = weaponSpec.getWeaponId();
        final CargoStackAPI stack = Global.getFactory().createCargoStack(CargoItemType.WEAPONS, weaponId, null);
        return getValidStack(stack);
    }

    public static String getManufacturer(final Object object) {
        if (object instanceof FighterWingSpecAPI) {
            return getManufacturer((FighterWingSpecAPI) object);
        }
        if (object instanceof WeaponSpecAPI) {
            return getManufacturer((WeaponSpecAPI) object);
        }
        return null;
    }

    public static String getManufacturer(final FighterWingSpecAPI fighterWing) {
        return fighterWing.getVariant().getHullSpec().getManufacturer();
    }

    public static String getManufacturer(final WeaponSpecAPI weaponSpec) {
        return weaponSpec.getManufacturer();
    }

    private static CargoStackAPI getValidStack(final CargoStackAPI stack) {
        stack.setSize(1);
        return stack;
    }
}
