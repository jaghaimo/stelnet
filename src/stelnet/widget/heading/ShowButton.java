package stelnet.widget.heading;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.awt.event.KeyEvent;
import stelnet.board.IntelBasePlugin;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class ShowButton extends Button {

    public ShowButton(final IntelBasePlugin intel, final MarketAPI market) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT), L10n.get(CommonL10n.SHOW), true);
        setPadding(0);
        setCutStyle(CutStyle.C2_MENU);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    intel.setSectorEntityToken(market.getPrimaryEntity());
                    StelnetHelper.sendKey(KeyEvent.VK_S);
                    Global.getSector().addTransientScript(intel);
                }
            }
        );
    }
}
