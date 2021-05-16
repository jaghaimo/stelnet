package stelnet.storage;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.StorageHelper;
import stelnet.storage.data.ItemsGridData;
import stelnet.storage.data.SharedData;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

public class StorageBoard extends BaseBoard {

    @Getter
    private final FilterManager filterManager;
    private SharedData gridData;

    public static StorageBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(StorageBoard.class);
        if (intel == null) {
            StorageBoard board = new StorageBoard();
            IntelHelper.addIntel(board, true);
        }
        return (StorageBoard) intel;
    }

    private StorageBoard() {
        ButtonManager buttonManager = new ButtonManager();
        filterManager = new FilterManager();
        gridData = new ItemsGridData(buttonManager, filterManager);
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("storage");
    }

    public void togglePane() {
        gridData = gridData.getNext();
    }

    public void toggleView() {
        gridData.changeDataProvider();
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.<Renderable>asList(gridData.getContentColumn(size), gridData.getControlColumn(size));
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("storageBoardTitle"), getDescription());
    }

    @Override
    protected String getTag() {
        return StorageIntel.TAG;
    }

    private String getDescription() {
        int itemCount = StorageHelper.getAllItemCount();
        int shipCount = StorageHelper.getAllShipCount();
        if (itemCount == 0 && shipCount == 0) {
            return L10n.get("storageBoardNoContent");
        }
        return L10n.get("storageBoardContent", itemCount, shipCount);
    }
}
