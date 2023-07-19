package stelnet.board.query;

import com.fs.starfarer.api.Global;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.board.BoardBasePlugin;
import stelnet.board.BoardRenderableInfo;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.settings.Modules;
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
public class QueryBoard extends BoardBasePlugin {

    private final String icon = StelnetHelper.getSpriteName("query");
    private final QueryState state = new QueryState();
    private final String tag = ModConstants.TAG_QUERY;

    @Override
    public void advance(final float amount) {
        Global.getSector().removeTransientScript(this);
        log.debug("Performing cleanup and resetting query cache");
        state.resetCache();
        state.setActiveTab(QueryBoardTab.LIST);
    }

    @Override
    public boolean isHidden() {
        return Modules.MARKET.isHidden();
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        final int queryCount = state.getQueryManager().numberOfQueries();
        return new BoardRenderableInfo(L10n.query("TITLE"), L10n.query("DESCRIPTION", queryCount));
    }

    @Override
    protected RenderableState getRenderableState() {
        log.debug("Adding itself as a script for cleanup operations");
        Global.getSector().addTransientScript(this);
        return state;
    }
}
