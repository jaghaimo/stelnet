package stelnet.board.storage;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SettingsUtils;
import stelnet.util.StorageUtils;
import uilib.RenderableIntelInfo;

@Getter
public class StorageBoard extends BaseBoard {

    private final String icon = SettingsUtils.getSpriteName("storage");
    private final StorageState renderableState = new StorageState();
    private final String tag = ModConstants.TAG_STORAGE;

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(L10n.get(StorageL10n.BOARD_TITLE), getDescription());
    }

    private String getDescription() {
        int itemCount = StorageUtils.getAllItemCount();
        int shipCount = StorageUtils.getAllShipCount();
        if (itemCount == 0 && shipCount == 0) {
            return L10n.get(StorageL10n.BOARD_NO_CONTENT);
        }
        return L10n.get(StorageL10n.BOARD_CONTENT, itemCount, shipCount);
    }
}
