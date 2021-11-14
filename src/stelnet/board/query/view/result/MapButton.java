package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.awt.event.KeyEvent;
import stelnet.board.query.ResultIntel;
import stelnet.util.AwtUtils;
import stelnet.util.SectorUtils;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class MapButton extends Button {

    public MapButton(final ResultIntel intel, final MarketAPI market) {
        super(new Size(0, 22), "Map", true);
        setSize(getSize().reduce(new Size(20, 0)));
        setCutStyle(CutStyle.C2_MENU);
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
