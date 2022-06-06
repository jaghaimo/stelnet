package stelnet.widget.heading;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.viewer.ViewerBoard;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;
import stelnet.widget.viewer.InMarketStrategy;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class PeekButton extends Button {

    public PeekButton(final MarketAPI market) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT), L10n.get(CommonL10n.PEEK), true);
        setPadding(0);
        setCutStyle(CutStyle.C2_MENU);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    ViewerBoard board = ViewerBoard.getInstance(ViewerBoard.class);
                    board.getRenderableState().setDisplayStrategy(new InMarketStrategy(market));
                    Global.getSector().getCampaignUI().showCoreUITab(CoreUITabId.INTEL, board);
                }
            }
        );
    }
}
