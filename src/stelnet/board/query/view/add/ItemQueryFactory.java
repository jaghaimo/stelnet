package stelnet.board.query.view.add;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.ItemQueryProvider;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.board.query.view.dialog.ItemPickerDialog;
import stelnet.board.query.view.dialog.PickerDialog;
import stelnet.filter.CargoStackNotKnownModspec;
import stelnet.filter.Filter;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.Button;
import uilib.Renderable;

public class ItemQueryFactory extends QueryFactory {

    @Getter
    private transient ItemQueryProvider provider = new ItemQueryProvider();

    private final FilteringButton[] itemTypes = ItemButtonUtils.createItemTypes();
    private final FilteringButton[] designTypes = ItemButtonUtils.createManufacturers(provider);
    private final FilteringButton[] weaponDamageTypes = ItemButtonUtils.createDamageType();
    private final FilteringButton[] weaponMountSizes = ItemButtonUtils.createWeaponMountSize();
    private final FilteringButton[] weaponMountTypes = ItemButtonUtils.createWeaponMountType();
    private final FilteringButton[] wingRoles = ItemButtonUtils.createWingRole();

    public Object readResolve() {
        provider = new ItemQueryProvider();
        return this;
    }

    @Override
    public Set<Filter> getFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, itemTypes, L10n.get(QueryL10n.ITEM_TYPES));
        addSelectedOrNone(filters, designTypes, L10n.get(QueryL10n.MANUFACTURERS), hasWeapons() || hasFighterWings());
        addSelectedOrNone(filters, weaponDamageTypes, L10n.get(QueryL10n.DAMAGE_TYPE), hasWeapons());
        addSelectedOrNone(filters, weaponMountSizes, L10n.get(QueryL10n.MOUNT_SIZE), hasWeapons());
        addSelectedOrNone(filters, weaponMountTypes, L10n.get(QueryL10n.MOUNT_TYPE), hasWeapons());
        addSelectedOrNone(filters, wingRoles, L10n.get(QueryL10n.WING_ROLES), hasFighterWings());
        filters.add(new CargoStackNotKnownModspec());
        return filters;
    }

    @Override
    protected Button[] getFinalComponents() {
        Set<Filter> filters = getFilters();
        PickerDialog picker = new ItemPickerDialog(
            StelnetHelper.makeCargoFromStacks(provider.getMatching(filters)),
            this
        );
        return new Button[] {
            new FindMatchingButton(this, L10n.get(CommonL10n.ITEMS)),
            new FindSelectedButton(picker),
        };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.ITEM_TYPES, itemTypes, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), CommonL10n.WEAPONS, hasWeapons()));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.DAMAGE_TYPE, weaponDamageTypes, hasWeapons()));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_TYPE, weaponMountTypes, hasWeapons()));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_SIZE, weaponMountSizes, hasWeapons()));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), CommonL10n.FIGHTER_WINGS, hasFighterWings()));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.WING_ROLES, wingRoles, hasFighterWings()));
        elements.add(
            new SectionHeader(
                sizeHelper.getGroupAndTextWidth(),
                QueryL10n.MANUFACTURERS,
                hasWeapons() || hasFighterWings(),
                designTypes
            )
        );
        elements.add(new ButtonGroup(sizeHelper, designTypes, hasWeapons() || hasFighterWings()));
        return elements;
    }

    private boolean hasWeapons() {
        return itemTypes[0].isStateOn() || hasNothing();
    }

    private boolean hasFighterWings() {
        return itemTypes[1].isStateOn() || hasNothing();
    }

    private boolean hasNothing() {
        for (FilteringButton button : itemTypes) {
            if (button.isStateOn()) {
                return false;
            }
        }
        return true;
    }
}
