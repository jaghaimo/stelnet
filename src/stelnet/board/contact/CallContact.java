package stelnet.board.contact;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.MemoryHelper;
import stelnet.util.ModConstants;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class CallContact extends Button {

    public CallContact(String label, boolean isEnabled, Size size, final MarketAPI market, final PersonAPI person) {
        super(size, label, isEnabled, person.getFaction().getBrightUIColor(), person.getFaction().getDarkUIColor());
        setCutStyle(CutStyle.C2_MENU);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    MemoryHelper.set(ModConstants.MEMORY_IS_CALLING, true);
                    ui.showDialog(
                        market.getPrimaryEntity(),
                        new ContactDialog(ui, person, market.getSubmarket(Submarkets.SUBMARKET_STORAGE))
                    );
                }
            }
        );
    }
}
