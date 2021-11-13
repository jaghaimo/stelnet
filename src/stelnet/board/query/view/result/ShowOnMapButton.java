package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.awt.event.KeyEvent;
import stelnet.board.query.ResultIntel;
import stelnet.util.AwtUtils;
import stelnet.util.SectorUtils;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class ShowOnMapButton extends Button {

    public ShowOnMapButton(final ResultIntel intel, final MarketAPI market) {
        super(new Size(0, 22), "Map", true);
        FactionAPI faction = market.getFaction();
        setTextColor(faction.getBaseUIColor());
        setBackgroundColor(faction.getDarkUIColor());
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    intel.setSectorEntityToken(market.getPrimaryEntity());
                    AwtUtils.send(KeyEvent.VK_S);
                    SectorUtils.addScript(intel);
                }
            }
        );
    }
}
