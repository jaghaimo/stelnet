package stelnet.board.market;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.RenderableState;
import stelnet.util.IntelManager;
import stelnet.util.L10n;
import stelnet.util.Settings;
import stelnet.util.Tagger;

@Getter
public class ViewerBoard extends BaseBoard {

    private final ViewerState state = new ViewerState();

    public static ViewerBoard getInstance() {
        IntelInfoPlugin intel = IntelManager.getFirstIntel(ViewerBoard.class);
        if (intel == null) {
            ViewerBoard board = new ViewerBoard();
            IntelManager.addIntel(board, true);
        }
        return (ViewerBoard) intel;
    }

    @Override
    public String getIcon() {
        return Settings.getSpriteName("viewer");
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_1;
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("marketViewTitle"), L10n.get("marketViewDescription"));
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return Tagger.MARKET;
    }
}
