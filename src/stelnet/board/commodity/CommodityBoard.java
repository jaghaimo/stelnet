package stelnet.board.commodity;

import lombok.Getter;
import lombok.Setter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SettingsUtils;
import uilib.RenderableIntelInfo;
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
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(L10n.get(CommodityL10n.BOARD_TITLE), L10n.get(CommodityL10n.BOARD_DESCRIPTION));
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return ModConstants.TAG_COMMODITY;
    }
}
