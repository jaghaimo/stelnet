package stelnet.widget.heading;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import lombok.extern.log4j.Log4j;
import stelnet.BaseIntel;
import stelnet.CommonL10n;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

@Log4j
public class ShowButton extends Button {

    public ShowButton(final BaseIntel intel, final MarketAPI market) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT), L10n.get(CommonL10n.SHOW), true);
        setPadding(0);
        setCutStyle(CutStyle.C2_MENU);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    intel.setSectorEntityToken(market.getPrimaryEntity());
                    send(KeyEvent.VK_S);
                    Global.getSector().addTransientScript(intel);
                }
            }
        );
    }

    private void send(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
            robot.keyRelease(key);
        } catch (AWTException exception) {
            log.warn("Something went wrong sending a key!", exception);
        }
    }
}
