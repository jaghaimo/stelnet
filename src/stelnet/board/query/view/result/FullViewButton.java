package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.viewer.ViewerBoard;
import stelnet.util.SectorUtils;
import stelnet.widget.viewer.InMarketStrategy;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class FullViewButton extends Button {

    public FullViewButton(final MarketAPI market) {
        super(new Size(0, 22), "View", true);
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
