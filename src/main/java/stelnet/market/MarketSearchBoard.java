package stelnet.market;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.Tagger;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

/**
 * Information board for managing displayed information intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
public class MarketSearchBoard extends BaseBoard {

    public static MarketSearchBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(MarketSearchBoard.class);
        if (intel == null) {
            MarketSearchBoard board = new MarketSearchBoard();
            IntelHelper.addIntel(board, true);
        }
        return (MarketSearchBoard) intel;
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("market");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo("Todo", "Tooooooooooooodoooooooooooo");
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return null;
    }

    @Override
    protected String getTag() {
        return Tagger.MARKET;
    }
}
