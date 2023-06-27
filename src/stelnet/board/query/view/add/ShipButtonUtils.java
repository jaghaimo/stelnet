package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.provider.ShipQueryProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.Filter;
import stelnet.filter.ShipHullHasBays;
import stelnet.filter.ShipHullHasBuiltIn;
import stelnet.filter.ShipHullIsManufacturer;
import stelnet.filter.ShipHullIsSize;
import stelnet.filter.ShipHullNoBuiltIns;
import stelnet.filter.WeaponSlotIsSize;
import stelnet.filter.WeaponSlotIsSizeType;
import stelnet.filter.WeaponSlotIsType;
import stelnet.util.L10n;

@RequiredArgsConstructor
public enum ShipButtonUtils {
    HULL_SIZE_FRIGATE(HullSize.FRIGATE),
    HULL_SIZE_DESTROYER(HullSize.DESTROYER),
    HULL_SIZE_CRUISER(HullSize.CRUISER),
    HULL_SIZE_CAPITAL(HullSize.CAPITAL_SHIP),
    WEAPON_TYPE_BALLISTIC(WeaponType.BALLISTIC),
    WEAPON_TYPE_MISSILE(WeaponType.MISSILE),
    WEAPON_TYPE_ENERGY(WeaponType.ENERGY),
    WEAPON_TYPE_HYBRID(WeaponType.HYBRID),
    WEAPON_TYPE_SYNERGY(WeaponType.SYNERGY),
    WEAPON_TYPE_COMPOSITE(WeaponType.COMPOSITE),
    WEAPON_TYPE_UNIVERSAL(WeaponType.UNIVERSAL),
    WEAPON_TYPE_BUILT_IN(WeaponType.BUILT_IN);

    public static FilteringButton[] getClassSizes() {
        return new FilteringButton[] {
            HULL_SIZE_FRIGATE.getButton(),
            HULL_SIZE_DESTROYER.getButton(),
            HULL_SIZE_CRUISER.getButton(),
            HULL_SIZE_CAPITAL.getButton(),
        };
    }

    public static FilteringButton[] getManufacturers(final ShipQueryProvider provider) {
        final List<FilteringButton> manufacturers = new LinkedList<>();
        for (final String manufacturer : provider.getManufacturers()) {
            manufacturers.add(new FilteringButton(manufacturer, new ShipHullIsManufacturer(manufacturer)));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }

    public static FilteringButton[] getMountBays(final ShipQueryFactory factory) {
        final List<FilteringButton> manufacturers = new LinkedList<>();
        manufacturers.add(new FilteringButton(L10n.common("NONE"), new ShipHullHasBays(0)));
        for (int i = 1; i < 7; i++) {
            manufacturers.add(new FighterBaysButton(factory, String.valueOf(i), new ShipHullHasBays(i)));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }

    public static FilteringButton[] getMountTypes(final WeaponSize weaponSize) {
        return new FilteringButton[] {
            WEAPON_TYPE_BALLISTIC.getButton(weaponSize),
            WEAPON_TYPE_MISSILE.getButton(weaponSize),
            WEAPON_TYPE_ENERGY.getButton(weaponSize),
            WEAPON_TYPE_HYBRID.getButton(weaponSize),
            WEAPON_TYPE_SYNERGY.getButton(weaponSize),
            WEAPON_TYPE_COMPOSITE.getButton(weaponSize),
            WEAPON_TYPE_UNIVERSAL.getButton(weaponSize),
            WEAPON_TYPE_BUILT_IN.getButton(weaponSize),
        };
    }

    public static FilteringButton[] getBuiltIns(final ShipQueryProvider provider) {
        final List<FilteringButton> builtInList = new LinkedList<>();
        builtInList.add(new FilteringButton(L10n.common("NONE"), new ShipHullNoBuiltIns(L10n.common("NONE")), "None"));
        for (final HullModSpecAPI builtIn : provider.getBuiltIns()) {
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

    private final String title;

    private final Filter filter;

    private ShipButtonUtils(final HullSize hullSize) {
        this(L10n.common("HULL_" + hullSize.name()), new ShipHullIsSize(hullSize));
    }

    private ShipButtonUtils(final WeaponType weaponType) {
        this(L10n.common(weaponType.getDisplayName()), new WeaponSlotIsType(weaponType));
    }

    public FilteringButton getButton() {
        return new FilteringButton(title, filter);
    }

    public FilteringButton getButton(final WeaponSize weaponSize) {
        final Filter weaponSizeFilter = new WeaponSlotIsSize(weaponSize);
        return new FilteringButton(title, new WeaponSlotIsSizeType(weaponSizeFilter, filter));
    }
}
