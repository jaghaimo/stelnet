package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.ShipQueryProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.ShipHullHasBays;
import stelnet.filter.ShipHullHasBuiltIn;
import stelnet.filter.ShipHullIsManufacturer;
import stelnet.filter.ShipHullIsSize;
import stelnet.filter.ShipHullNoBuiltIns;
import stelnet.filter.WeaponSlotIsSizeType;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;

public class ShipButtonUtils {

    public static FilteringButton[] getClassSizes() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(HullSize.FRIGATE), new ShipHullIsSize(HullSize.FRIGATE)),
            new FilteringButton(L10n.get(HullSize.DESTROYER), new ShipHullIsSize(HullSize.DESTROYER)),
            new FilteringButton(L10n.get(HullSize.CRUISER), new ShipHullIsSize(HullSize.CRUISER)),
            new FilteringButton(L10n.get(HullSize.CAPITAL_SHIP), new ShipHullIsSize(HullSize.CAPITAL_SHIP)),
        };
    }

    public static FilteringButton[] getManufacturers(ShipQueryProvider provider) {
        List<FilteringButton> manufacturers = new LinkedList<>();
        for (String manufacturer : provider.getManufacturers()) {
            manufacturers.add(new FilteringButton(manufacturer, new ShipHullIsManufacturer(manufacturer)));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }

    public static FilteringButton[] getMountBays(ShipQueryFactory factory) {
        List<FilteringButton> manufacturers = new LinkedList<>();
        manufacturers.add(new FilteringButton(CommonL10n.NONE, new ShipHullHasBays(0)));
        for (int i = 1; i < 7; i++) {
            manufacturers.add(new FighterBaysButton(factory, String.valueOf(i), new ShipHullHasBays(i)));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }

    public static FilteringButton[] getMountTypes(WeaponSize weaponSize) {
        return new FilteringButton[] {
            getMountTypeSize(weaponSize, WeaponType.BALLISTIC),
            getMountTypeSize(weaponSize, WeaponType.MISSILE),
            getMountTypeSize(weaponSize, WeaponType.ENERGY),
            getMountTypeSize(weaponSize, WeaponType.HYBRID),
            getMountTypeSize(weaponSize, WeaponType.SYNERGY),
            getMountTypeSize(weaponSize, WeaponType.COMPOSITE),
            getMountTypeSize(weaponSize, WeaponType.UNIVERSAL),
            getMountTypeSize(weaponSize, WeaponType.BUILT_IN),
        };
    }

    public static FilteringButton[] getBuiltIns(ShipQueryProvider provider) {
        List<FilteringButton> builtInList = new LinkedList<>();
        builtInList.add(
            new FilteringButton(L10n.get(CommonL10n.NONE), new ShipHullNoBuiltIns(L10n.get(CommonL10n.NONE)), "None")
        );
        for (HullModSpecAPI builtIn : provider.getBuiltIns()) {
            builtInList.add(
                new FilteringButton(
                    builtIn.getDisplayName(),
                    new ShipHullHasBuiltIn(builtIn.getId(), builtIn.getDisplayName()),
                    builtIn.getId()
                )
            );
        }
        return builtInList.toArray(new FilteringButton[] {});
    }

    private static FilteringButton getMountTypeSize(WeaponSize weaponSize, WeaponType weaponType) {
        return new FilteringButton(weaponType.getDisplayName(), new WeaponSlotIsSizeType(weaponSize, weaponType));
    }
}
