package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.provider.ShipProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.ShipHullHasBays;
import stelnet.filter.ShipHullHasBuiltIn;
import stelnet.filter.ShipHullIsManufacturer;
import stelnet.filter.ShipHullIsSize;
import stelnet.filter.ShipHullNoBuiltIns;
import stelnet.filter.WeaponSlotIsSize;
import stelnet.filter.WeaponSlotIsType;
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

    public static FilteringButton[] getManufacturers(ShipProvider provider) {
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

    public static FilteringButton[] getMountSizes() {
        return new FilteringButton[] {
            new FilteringButton(WeaponSize.SMALL.getDisplayName(), new WeaponSlotIsSize(WeaponSize.SMALL)),
            new FilteringButton(WeaponSize.MEDIUM.getDisplayName(), new WeaponSlotIsSize(WeaponSize.MEDIUM)),
            new FilteringButton(WeaponSize.LARGE.getDisplayName(), new WeaponSlotIsSize(WeaponSize.LARGE)),
        };
    }

    public static FilteringButton[] getMountTypes() {
        return new FilteringButton[] {
            new FilteringButton(WeaponType.BALLISTIC.getDisplayName(), new WeaponSlotIsType(WeaponType.BALLISTIC)),
            new FilteringButton(WeaponType.MISSILE.getDisplayName(), new WeaponSlotIsType(WeaponType.MISSILE)),
            new FilteringButton(WeaponType.ENERGY.getDisplayName(), new WeaponSlotIsType(WeaponType.ENERGY)),
            new FilteringButton(WeaponType.HYBRID.getDisplayName(), new WeaponSlotIsType(WeaponType.HYBRID)),
            new FilteringButton(WeaponType.SYNERGY.getDisplayName(), new WeaponSlotIsType(WeaponType.SYNERGY)),
            new FilteringButton(WeaponType.COMPOSITE.getDisplayName(), new WeaponSlotIsType(WeaponType.COMPOSITE)),
            new FilteringButton(WeaponType.UNIVERSAL.getDisplayName(), new WeaponSlotIsType(WeaponType.UNIVERSAL)),
            new FilteringButton(WeaponType.BUILT_IN.getDisplayName(), new WeaponSlotIsType(WeaponType.BUILT_IN)),
        };
    }

    public static FilteringButton[] getBuiltIns(ShipProvider provider) {
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
}
