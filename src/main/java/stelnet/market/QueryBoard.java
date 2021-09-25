package stelnet.market;

import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.Tagger;
import stelnet.market.view.QueriesTabViewFactory;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
@Setter
@Getter
public class QueryBoard extends BaseBoard {

    private QueryTab activeTab = QueryTab.LIST;

    public static QueryBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(QueryBoard.class);
        if (intel == null) {
            QueryBoard board = new QueryBoard();
            IntelHelper.addIntel(board, true);
        }
        return (QueryBoard) intel;
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("market");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        int queriesPresent = 0;
        return new BoardInfo(L10n.get("marketQueryTitle"), L10n.get("marketQueryDescription", queriesPresent));
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Collections.singletonList(new QueriesTabViewFactory(activeTab).createContainer(size));
    }

    @Override
    protected String getTag() {
        return Tagger.MARKET;
    }
}
