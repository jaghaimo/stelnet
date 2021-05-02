package stelnet.storage;

import java.awt.Color;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.helper.CargoHelper;

public class StorageIntel extends BaseIntel {

    public final static String TAG = "stelnetStorage";

    private final SubmarketAPI storage;

    public StorageIntel(SubmarketAPI storage) {
        super(storage.getMarket().getFaction(), storage.getMarket().getPrimaryEntity());
        this.storage = storage;
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        Color baseColor = getFactionForUIColors().getBaseUIColor();
        Color darkColor = getFactionForUIColors().getDarkUIColor();
        info.addSectionHeading(getLocationName() + " Items", baseColor, darkColor, Alignment.MID, 5f);
        info.addSpacer(10);
        showItems(info);
        info.addSpacer(10);
        info.addSectionHeading(getLocationName() + " Ships", baseColor, darkColor, Alignment.MID, 5f);
        info.addSpacer(10);
        showShips(info);
    }

    @Override
    public String getIcon() {
        return getFaction().getCrest();
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(getLocationNameWithSystem(), "Content", getStorageContent(), "Faction",
                getFactionWithRel());
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

    private void showItems(TooltipMakerAPI info) {
        CargoAPI cargo = storage.getCargo().createCopy();
        cargo.sort();
        info.showCargo(cargo, cargo.getStacksCopy().size(), false, 5f);
    }

    private void showShips(TooltipMakerAPI info) {
        List<FleetMemberAPI> ships = storage.getCargo().getMothballedShips().getMembersListCopy();
        info.showShips(ships, ships.size(), false, 5f);
    }
}
