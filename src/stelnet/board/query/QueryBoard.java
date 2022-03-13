package stelnet.board.query;

import com.fs.starfarer.api.Global;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntelInfo;
import uilib.RenderableState;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
@Getter
@Log4j
public class QueryBoard extends BaseBoard {

    private final String icon = StelnetHelper.getSpriteName("query");
    private final QueryState state = new QueryState();
    private final String tag = ModConstants.TAG_QUERY;

    @Override
    public void advance(float amount) {
        Global.getSector().removeTransientScript(this);
        log.debug("Performing cleanup and resetting query cache");
        state.resetCache();
        state.setActiveTab(QueryBoardTab.LIST);
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        int queryCount = state.getQueryManager().numberOfQueries();
        return new BoardInfo(L10n.get(QueryL10n.TITLE), L10n.get(QueryL10n.DESCRIPTION, queryCount));
    }

    @Override
    protected RenderableState getRenderableState() {
        log.debug("Adding itself as a script for cleanup operations");
        Global.getSector().addTransientScript(this);
        return state;
    }
}
