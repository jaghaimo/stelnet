package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.QueryL10n;
import stelnet.board.viewer.ViewerBoard;
import stelnet.util.L10n;
import stelnet.util.SectorUtils;
import stelnet.widget.viewer.InMarketStrategy;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class ViewerButton extends Button {

    public ViewerButton(final MarketAPI market) {
        super(new Size(0, 22), L10n.get(QueryL10n.VIEWER), true);
        setSize(getSize().reduce(new Size(20, 0)));
        setCutStyle(CutStyle.C2_MENU);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    ViewerBoard board = ViewerBoard.getInstance(ViewerBoard.class);
                    board.getState().setDisplayStrategy(new InMarketStrategy(market));
                    SectorUtils.getCampaignUI().showCoreUITab(CoreUITabId.INTEL, board);
                }
            }
        );
    }
}
