package stelnet.board.query;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SectorUtils;
import stelnet.util.SettingsUtils;
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

    private final QueryState state = new QueryState();

    @Override
    public void advance(float amount) {
        SectorUtils.removeTransientScript(this);
        log.debug("Cleanup resetting query cache");
        state.resetCache();
        state.setActiveTab(QueryBoardTab.LIST);
    }

    @Override
    public String getIcon() {
        return SettingsUtils.getSpriteName("query");
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        int queryCount = state.getQueryManager().numberOfQueries();
        return new BoardInfo(L10n.get(QueryL10n.TITLE), L10n.get(QueryL10n.DESCRIPTION, queryCount));
    }

    @Override
    protected RenderableState getRenderableState() {
        log.setLevel(Level.ALL);
        log.debug("Adding itself as a script for cleanup operation");
        SectorUtils.addTransientScript(this);
        return state;
    }

    @Override
    protected String getTag() {
        return ModConstants.TAG_MARKET;
    }
}
