package stelnet.board.storage;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.Settings;
import stelnet.util.StorageUtils;
import stelnet.util.Tagger;
import uilib.RenderableState;

@Getter
@Setter
public class StorageBoard extends BaseBoard {

    private final StorageState state = new StorageState();

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
