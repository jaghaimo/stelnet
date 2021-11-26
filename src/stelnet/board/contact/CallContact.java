package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.CommonL10n;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class CallContact extends Button {

    public CallContact(final MarketAPI market, final PersonAPI person) {
        super(
            new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT),
            L10n.get(CommonL10n.CALL),
            true,
            person.getFaction().getBrightUIColor(),
            person.getFaction().getDarkUIColor()
        );
        setCutStyle(CutStyle.C2_MENU);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    ui.showDialog(market.getPrimaryEntity(), new ContactDialog(person));
                }
            }
        );
    }
}
