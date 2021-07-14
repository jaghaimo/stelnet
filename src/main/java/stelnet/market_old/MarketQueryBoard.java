package stelnet.market_old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.market_old.intel.IntelQuery;
import stelnet.market_old.view.ControlRow;
import stelnet.market_old.view.EmptyRow;
import stelnet.market_old.view.Queries;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
public class MarketQueryBoard extends BaseBoard {

    private final List<IntelQuery> queries = new ArrayList<IntelQuery>();

    public static MarketQueryBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(MarketQueryBoard.class);
        if (intel == null) {
            MarketQueryBoard board = new MarketQueryBoard();
            IntelHelper.addIntel(board, true);
        }
        return (MarketQueryBoard) intel;
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("market");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        int queriesPresent = queries.size();
        return new BoardInfo(L10n.get("marketQueryTitle"), L10n.get("marketQueryDescription", queriesPresent));
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Arrays.asList(
                new ControlRow(size, queries),
                new EmptyRow(size, queries.isEmpty()),
                new Queries(size, queries)
        );
    }

    @Override
    protected String getTag() {
        return MarketQueryIntel.TAG;
    }
}
