package stelnet.storage;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.L10n;
import stelnet.helper.CargoHelper;
import stelnet.ui.Cargo;
import stelnet.ui.Heading;
import stelnet.ui.Renderable;
import stelnet.ui.Ships;
import stelnet.ui.Size;
import stelnet.ui.Spacer;

public class StorageIntel extends BaseIntel {

    public final static String TAG = "stelnetStorage";

    private final SubmarketAPI storage;

    public StorageIntel(SubmarketAPI storage) {
        super(storage.getMarket().getFaction(), storage.getMarket().getPrimaryEntity());
        this.storage = storage;
    }

    @Override
    public String getIcon() {
        return getFaction().getCrest();
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(
                getLocationNameWithSystem(),
                L10n.get("intelLocation"),
                getStorageContent(),
                L10n.get("intelFaction"),
                getFactionWithRel()
        );
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        Color baseColor = getFactionForUIColors().getBaseUIColor();
        Color darkColor = getFactionForUIColors().getDarkUIColor();
        CargoAPI cargo = storage.getCargo();
        List<FleetMemberAPI> ships = storage.getCargo().getMothballedShips().getMembersListCopy();
        return Arrays.<Renderable>asList(
                new Heading(getLocationName() + " Items", baseColor, darkColor),
                new Spacer(10),
                new Cargo(cargo, "There are no items in this storage.", size),
                new Spacer(10),
                new Heading(getLocationName() + " Ships", baseColor, darkColor),
                new Spacer(10),
                new Ships(ships, "There are no ships in this storage.", size)
        );
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    private String getStorageContent() {
        CargoAPI cargo = storage.getCargo();
        int itemsCount = CargoHelper.calculateItemQuantity(cargo);
        int shipsCount = CargoHelper.calculateShipQuantity(cargo.getMothballedShips().getMembersListCopy());
        String items = itemsCount != 1 ? "s" : "";
        String ships = shipsCount != 1 ? "s" : "";
        return String.format("%d item%s & %d ship%s", itemsCount, items, shipsCount, ships);
    }
}
