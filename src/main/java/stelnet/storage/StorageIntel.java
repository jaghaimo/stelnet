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
                new Heading(L10n.get("storageIntelHeaderItems", getLocationName()), baseColor, darkColor),
                new Spacer(10),
                new Cargo(cargo, "storageIntelNoCargo", size),
                new Spacer(10),
                new Heading(L10n.get("storageIntelHeaderShips", getLocationName()), baseColor, darkColor),
                new Spacer(10),
                new Ships(ships, "storageIntelNoShips", size)
        );
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    private String getStorageContent() {
        CargoAPI cargo = storage.getCargo();
        int itemCount = CargoHelper.calculateItemQuantity(cargo);
        int shipCount = CargoHelper.calculateShipQuantity(cargo.getMothballedShips().getMembersListCopy());
        return L10n.get("storageIntelContent", itemCount, shipCount);
    }
}
