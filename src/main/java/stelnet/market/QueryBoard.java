package stelnet.market;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.market.view.QueryTabViewFactory;
import stelnet.util.IntelManager;
import stelnet.util.L10n;
import stelnet.util.Settings;
import stelnet.util.Tagger;
import uilib.Renderable;
import uilib.property.Size;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
@Getter
public class QueryBoard extends BaseBoard {

    private final QueryState state = new QueryState();

    public static QueryBoard getInstance() {
        IntelInfoPlugin intel = IntelManager.getFirstIntel(QueryBoard.class);
        if (intel == null) {
            QueryBoard board = new QueryBoard();
            IntelManager.addIntel(board, true);
        }
        return (QueryBoard) intel;
    }

    @Override
    public String getIcon() {
        return Settings.getSpriteName("market");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        int queriesPresent = 0;
        return new BoardInfo(L10n.get("marketQueryTitle"), L10n.get("marketQueryDescription", queriesPresent));
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Collections.singletonList(new QueryTabViewFactory(state).createContainer(size));
    }

    @Override
    protected String getTag() {
        return Tagger.MARKET;
    }
}
