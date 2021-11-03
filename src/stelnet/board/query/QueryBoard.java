package stelnet.board.query;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;
import stelnet.util.TagContants;
import uilib.RenderableState;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
@Getter
public class QueryBoard extends BaseBoard {

    private final QueryState state = new QueryState();

    @Override
    public String getIcon() {
        return SettingsUtils.getSpriteName("market");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        int queriesPresent = 0; // TODO : Implement this
        return new BoardInfo(L10n.get("marketQueryTitle"), L10n.get("marketQueryDescription", queriesPresent));
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return TagContants.MARKET;
    }
}
