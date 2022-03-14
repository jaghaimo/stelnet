package stelnet.board.storage;

import com.fs.starfarer.api.campaign.CargoAPI;
import java.util.Collections;
import java.util.Set;
import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntelInfo;

@Getter
public class StorageBoard extends BaseBoard {

    private final String icon = StelnetHelper.getSpriteName("storage");
    private final StorageState renderableState = new StorageState();
    private final String tag = ModConstants.TAG_STORAGE;

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(L10n.get(StorageL10n.BOARD_TITLE), getDescription());
    }

    private String getDescription() {
        int itemCount = getAllItemCount();
        int shipCount = getAllShipCount();
        if (itemCount == 0 && shipCount == 0) {
            return L10n.get(StorageL10n.BOARD_NO_CONTENT);
        }
        return L10n.get(StorageL10n.BOARD_CONTENT, itemCount, shipCount);
    }

    private int getAllItemCount() {
        CargoAPI cargo = StelnetHelper.getAllItems(getEmptySet());
        return StelnetHelper.calculateItemQuantity(cargo);
    }

    private int getAllShipCount() {
        return StelnetHelper.getAllShips(getEmptySet()).size();
    }

    private Set<Filter> getEmptySet() {
        return Collections.<Filter>emptySet();
    }
}
