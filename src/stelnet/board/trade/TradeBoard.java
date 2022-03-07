package stelnet.board.trade;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SettingsUtils;

@Getter
public class TradeBoard extends BaseBoard {

    private final String icon = SettingsUtils.getSpriteName("trade");
    private final BoardInfo intelInfo = new BoardInfo(
        L10n.get(TradeL10n.BOARD_TITLE),
        L10n.get(TradeL10n.BOARD_DESCRIPTION)
    );
    private final TradeState renderableState = new TradeState();
    private final IntelSortTier sortTier = IntelSortTier.TIER_1;
    private final String tag = ModConstants.TAG_COMMODITY;
}
