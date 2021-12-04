package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.intel.contacts.ContactIntel;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.SectorUtils;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class ShowContactButton extends Button {

    public ShowContactButton(String label, Size size, final ContactIntel intel, FactionAPI faction) {
        super(size, label, true, faction.getBrightUIColor(), faction.getDarkUIColor());
        setCutStyle(CutStyle.BL_TR);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    SectorUtils.getCampaignUI().showCoreUITab(CoreUITabId.INTEL, intel);
                }
            }
        );
    }
}
