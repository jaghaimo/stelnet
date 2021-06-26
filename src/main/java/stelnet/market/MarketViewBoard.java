package stelnet.market;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;

import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;

public class MarketViewBoard extends BaseBoard {

    public static MarketViewBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(MarketViewBoard.class);
        if (intel == null) {
            MarketViewBoard board = new MarketViewBoard();
            IntelHelper.addIntel(board, true);
        }
        return (MarketViewBoard) intel;
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("viewer");
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("marketViewTitle"), L10n.get("marketViewDescription"));
    }

    @Override
    protected String getTag() {
        return MarketQueryIntel.TAG;
    }
}
