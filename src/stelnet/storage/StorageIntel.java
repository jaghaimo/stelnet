package stelnet.storage;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.util.CargoUtils;
import stelnet.util.L10n;
import stelnet.util.Tagger;
import uilib.Cargo;
import uilib.Heading;
import uilib.Renderable;
import uilib.Ships;
import uilib.Spacer;
import uilib.property.Size;

public class StorageIntel extends BaseIntel {

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
        CargoAPI cargo = storage.getCargo().createCopy();
        List<FleetMemberAPI> ships = storage.getCargo().getMothballedShips().getMembersListCopy();
        return Arrays.<Renderable>asList(
            new Heading(L10n.get("storageIntelHeaderItems", getLocationName()), baseColor, darkColor),
            new Spacer(10),
            new Cargo(cargo, L10n.get("storageIntelNoCargo"), size),
            new Spacer(10),
            new Heading(L10n.get("storageIntelHeaderShips", getLocationName()), baseColor, darkColor),
            new Spacer(10),
            new Ships(ships, L10n.get("storageIntelNoShips"), size)
        );
    }

    @Override
    protected String getTag() {
        return Tagger.STORAGE;
    }

    private String getStorageContent() {
        CargoAPI cargo = storage.getCargo();
        int itemCount = CargoUtils.calculateItemQuantity(cargo.createCopy());
        int shipCount = CargoUtils.calculateShipQuantity(cargo.getMothballedShips().getMembersListCopy());
        return L10n.get("storageIntelContent", itemCount, shipCount);
    }
}
