package stelnet.board.query;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;
import stelnet.util.TagConstants;
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
        int queryCount = state.getQueryManger().numberOfQueries();
        return new BoardInfo(L10n.get(QueryL10n.TITLE), L10n.get(QueryL10n.DESCRIPTION, queryCount));
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return TagConstants.MARKET;
    }
}
