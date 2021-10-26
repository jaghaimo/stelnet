package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.IntelManager;
import stelnet.util.L10n;
import stelnet.util.Settings;
import stelnet.util.Tagger;
import uilib.RenderableState;

@Setter
@Getter
public class CommodityBoard extends BaseBoard {

    private final CommodityState state = new CommodityState();

    public static CommodityBoard getInstance() {
        IntelInfoPlugin intel = IntelManager.getFirstIntel(CommodityBoard.class);
        if (intel == null) {
            BaseIntelPlugin board = new CommodityBoard();
            IntelManager.addIntel(board, true);
        }
        return (CommodityBoard) intel;
    }

    @Override
    public String getIcon() {
        return Settings.getSpriteName("commodity");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("commodityBoardTitle"), L10n.get("commodityBoardDescription"));
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return Tagger.COMMODITY;
    }
}
