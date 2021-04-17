package stelnet.storage;

import java.util.Set;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.BaseBoard;
import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.StorageHelper;
import stelnet.storage.data.ItemsGridData;
import stelnet.storage.data.SharedData;
import stelnet.ui.GridRenderer;
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
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        float spacer = 20;
        float controlWidth = 180;
        float displayWidth = width - controlWidth - spacer;
        Size size = new Size(width, height);
        GridRenderer renderer = new GridRenderer(size);
        renderer.setTopLeft(gridData.getTopLeft(new Size(displayWidth, height)));
        renderer.setTopRight(gridData.getTopRight(new Size(controlWidth, height)));
        renderer.render(panel);
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

    public FilterManager getFilterManager() {
        return filterManager;
    }

    public void togglePane() {
        gridData = gridData.getNext();
    }

    public void toggleView() {
        gridData.changeDataProvider();
    }

    private String getDescription(int itemCount, int shipCount) {
        if (itemCount == 0 && shipCount == 0) {
            return "You don't store anything in your storages.";
        }
        return getFormattedDescription(itemCount, shipCount);
    }

    private String getFormattedDescription(int itemCount, int shipCount) {
        String items = itemCount != 1 ? "s " : " ";
        String ships = shipCount != 1 ? "s " : " ";
        return "You have %s item" + items + "and %s ship" + ships + "stored.";
    }
}
