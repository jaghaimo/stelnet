package stelnet.storage;

import java.util.Set;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.BaseBoard;
import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.StorageHelper;
import stelnet.storage.data.ItemsGridData;
import stelnet.storage.data.SharedData;
import stelnet.ui.GridData;
import stelnet.ui.Size;

public class StorageBoard extends BaseBoard {

    private ButtonManager buttonManager;
    private FilterManager filterManager;
    private SharedData gridData;

    public static StorageBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(StorageBoard.class);
        if (intel == null) {
            StorageBoard board = new StorageBoard();
            IntelHelper.addIntel(board);
        }
        return (StorageBoard) intel;
    }

    private StorageBoard() {
        buttonManager = new ButtonManager();
        filterManager = new FilterManager();
        gridData = new ItemsGridData(buttonManager, filterManager);
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        int itemCount = StorageHelper.getAllItemCount();
        int shipCount = StorageHelper.getAllShipCount();
        info.addPara("Storage Contents", getTitleColor(mode), 0);
        info.addPara(getDescription(itemCount, shipCount), 1f, getBulletColorForMode(mode), Misc.getHighlightColor(),
                String.valueOf(itemCount), String.valueOf(shipCount));
        info.addPara("", 1f);
    }

    @Override
    public String getIcon() {
        return GlobalHelper.getSpriteName("storage");
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(StorageIntel.TAG);
        return tags;
    }

    @Override
    protected GridData getGridData(Size size) {
        // TODO use this again
        // float spacer = 20;
        // float controlWidth = 180;
        // float displayWidth = width - controlWidth - spacer;
        return gridData;
    }

    public FilterManager getFilterManager() {
        return filterManager;
    }

    public void togglePane() {
        gridData = gridData.getNext();
    }

    public void toggleView() {
        gridData.changeDataProvider();
    }

    private String getDescription(int cargoCount, int shipCount) {
        if (cargoCount == 0 && shipCount == 0) {
            return "You don't store anything in your storages.";
        }
        return getFormattedDescription(cargoCount, shipCount);
    }

    private String getFormattedDescription(int cargoCount, int shipCount) {
        String items = cargoCount != 1 ? "s " : " ";
        String ships = shipCount != 1 ? "s " : " ";
        return "You have %s item" + items + "and %s ship" + ships + "stored.";
    }
}
