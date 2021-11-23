package stelnet.board.storage;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;
import stelnet.util.StorageUtils;
import stelnet.util.TagConstants;
import uilib.RenderableIntelInfo;
import uilib.RenderableState;

@Getter
@Setter
public class StorageBoard extends BaseBoard {

    private final StorageState state = new StorageState();

    @Override
    public String getIcon() {
        return SettingsUtils.getSpriteName("storage");
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(L10n.get(StorageL10n.BOARD_TITLE), getDescription());
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return TagConstants.STORAGE;
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
