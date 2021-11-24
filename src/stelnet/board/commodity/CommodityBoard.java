package stelnet.board.commodity;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SettingsUtils;
import uilib.RenderableIntelInfo;

@Getter
public class CommodityBoard extends BaseBoard {

    private final String icon = SettingsUtils.getSpriteName("commodity");
    private final RenderableIntelInfo intelInfo = new BoardInfo(
        L10n.get(CommodityL10n.BOARD_TITLE),
        L10n.get(CommodityL10n.BOARD_DESCRIPTION)
    );
    private final CommodityState renderableState = new CommodityState();
    private final String tag = ModConstants.TAG_COMMODITY;
}
