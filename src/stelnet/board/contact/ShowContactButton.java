package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.MemoryHelper;
import stelnet.util.ModConstants;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class ShowContactButton extends Button {

    public ShowContactButton(final String label, final Size size, final ContactIntel intel, final FactionAPI faction) {
        super(size, label, true, faction.getBrightUIColor(), faction.getDarkUIColor());
        setEnabled(!MemoryHelper.getBoolean(ModConstants.MEMORY_IS_CALLING));
        setCutStyle(CutStyle.C2_MENU);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    Global.getSector().getCampaignUI().showCoreUITab(CoreUITabId.INTEL, intel);
                }
            }
        );
    }
}
