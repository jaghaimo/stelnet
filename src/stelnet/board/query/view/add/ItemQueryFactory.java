package stelnet.board.query.view.add;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.ItemProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOrFilter;
import stelnet.filter.WeaponIsDamage;
import stelnet.filter.WeaponIsSize;
import stelnet.filter.WeaponIsType;
import uilib.Cargo;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.property.Size;

public class ItemQueryFactory extends QueryFactory {

    private transient ItemButton[] damageTypes = createDamageTypeButtons();
    private transient ItemButton[] mountSizes = createMountSizeButtons();
    private transient ItemButton[] mountTypes = createMountTypeButtons();

    public void readResolve() {
        damageTypes = createDamageTypeButtons();
        mountSizes = createMountSizeButtons();
        mountTypes = createMountTypeButtons();
    }

    @Override
    protected List<Renderable> getQueryBuilder() {
        List<Renderable> elements = new LinkedList<>();
        addSection(elements, CommonL10n.WEAPONS);
        addLabeledGroup(elements, QueryL10n.WEAPON_DAMAGE, Arrays.<Renderable>asList(damageTypes));
        addLabeledGroup(elements, QueryL10n.MOUNT_TYPE, Arrays.<Renderable>asList(mountTypes));
        addLabeledGroup(elements, QueryL10n.MOUNT_SIZE, Arrays.<Renderable>asList(mountSizes));
        addSection(elements, CommonL10n.FIGHTER_WINGS);
        return elements;
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        List<Filter> filters = getFilters();
        ItemProvider itemProvider = new ItemProvider();
        CargoAPI cargo = itemProvider.getWeapons(filters);
        cargo.addAll(itemProvider.getFighters(filters));
        cargo.addAll(itemProvider.getModspecs());
        return new Cargo(cargo, "No matching items found.", size);
    }

    private List<Filter> getFilters() {
        List<Filter> filters = new LinkedList<>();
        filters.add(new LogicalOrFilter(getFilters(damageTypes)));
        filters.add(new LogicalOrFilter(getFilters(mountSizes)));
        filters.add(new LogicalOrFilter(getFilters(mountTypes)));
        return filters;
    }

    private ItemButton[] createDamageTypeButtons() {
        return new ItemButton[] {
            new ItemButton(DamageType.KINETIC.getDisplayName(), new WeaponIsDamage(DamageType.KINETIC)),
            new ItemButton(DamageType.HIGH_EXPLOSIVE.getDisplayName(), new WeaponIsDamage(DamageType.HIGH_EXPLOSIVE)),
            new ItemButton(DamageType.ENERGY.getDisplayName(), new WeaponIsDamage(DamageType.ENERGY)),
        };
    }

    private ItemButton[] createMountSizeButtons() {
        return new ItemButton[] {
            new ItemButton(WeaponSize.SMALL.getDisplayName(), new WeaponIsSize(WeaponSize.SMALL)),
            new ItemButton(WeaponSize.MEDIUM.getDisplayName(), new WeaponIsSize(WeaponSize.MEDIUM)),
            new ItemButton(WeaponSize.LARGE.getDisplayName(), new WeaponIsSize(WeaponSize.LARGE)),
        };
    }

    private ItemButton[] createMountTypeButtons() {
        return new ItemButton[] {
            new ItemButton(WeaponType.BALLISTIC.getDisplayName(), new WeaponIsType(WeaponType.BALLISTIC)),
            new ItemButton(WeaponType.MISSILE.getDisplayName(), new WeaponIsType(WeaponType.MISSILE)),
            new ItemButton(WeaponType.ENERGY.getDisplayName(), new WeaponIsType(WeaponType.ENERGY)),
        };
    }
}
