package stelnet.storage;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.RenderableState;
import stelnet.util.IntelManager;
import stelnet.util.L10n;
import stelnet.util.Settings;
import stelnet.util.StorageUtils;
import stelnet.util.Tagger;

@Getter
@Setter
public class StorageBoard extends BaseBoard {

    private final StorageState state = new StorageState();

    public static StorageBoard getInstance() {
        IntelInfoPlugin intel = IntelManager.getFirstIntel(StorageBoard.class);
        if (intel == null) {
            StorageBoard board = new StorageBoard();
            IntelManager.addIntel(board, true);
        }
        return (StorageBoard) intel;
    }

    @Override
    public String getIcon() {
        return Settings.getSpriteName("storage");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("storageBoardTitle"), getDescription());
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return Tagger.STORAGE;
    }

    private String getDescription() {
        int itemCount = StorageUtils.getAllItemCount();
        int shipCount = StorageUtils.getAllShipCount();
        if (itemCount == 0 && shipCount == 0) {
            return L10n.get("storageBoardNoContent");
        }
        return L10n.get("storageBoardContent", itemCount, shipCount);
    }
}
