package stelnet.board.commodity;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;
import stelnet.util.TagConstants;
import uilib.RenderableState;

@Setter
@Getter
public class CommodityBoard extends BaseBoard {

    private final CommodityState state = new CommodityState();

    @Override
    public String getIcon() {
        return SettingsUtils.getSpriteName("commodity");
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
        return TagConstants.COMMODITY;
    }
}
